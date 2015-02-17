package com.examples.camel.apibox;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Reference: http://stackoverflow.com/questions/10610820/apache-camel-to-aggregate-multiple-rest-service-responses
 */
public class APIBoxAggregator extends RouteBuilder {
	
	static final String CUSTOMER_URL = "http://localhost:5000/customer";
	static final String ORDER_URL = "http://localhost:5000/order";
	
	@Override
	public void configure() throws Exception {

		from("jetty:http://0.0.0.0:8080/aggr")
			.enrich("direct:service_aggregate").setBody(property(Exchange.GROUPED_EXCHANGE));

		from("direct:service_aggregate").multicast(new SimpleAggrStrategy()).parallelProcessing()
			.enrich(CUSTOMER_URL + "?bridgeEndpoint=true&throwExceptionOnFailure=false")
			.enrich(ORDER_URL + "?bridgeEndpoint=true&throwExceptionOnFailure=false")
			.end();

	}
	
	

	/* --- Utilities --- */
	
	static final Map<String, String> keys;
    static {
        Map<String, String> uMap = new HashMap<>();
        uMap.put(CUSTOMER_URL, "customer");
        uMap.put(ORDER_URL, "order");
        keys = Collections.unmodifiableMap(uMap);
    }

	class SimpleAggrStrategy extends GroupedExchangeAggregationStrategy {

		@Override
		public void onCompletion(Exchange exchange) {

			@SuppressWarnings("unchecked")
			List<Exchange> list = (List<Exchange>) exchange.getProperty(Exchange.GROUPED_EXCHANGE);
			try {
				JSONObject out = new JSONObject();
				for (int i = 0; i < list.size(); i++) {
					// Prepare Key
					Exchange inEx = list.get(i).getIn().getExchange();
					String toEndPoint = inEx.getProperty("CamelToEndpoint").toString();
					String key = toEndPoint.startsWith(CUSTOMER_URL) ? keys.get(CUSTOMER_URL) : keys.get(ORDER_URL);
					
					// Prepare value
					String inStr = list.get(i).getIn().getBody(String.class);
					Object outObj = inStr.startsWith("[") ? new JSONArray(inStr) : new JSONObject(inStr);
					out.put(key, outObj);
				}
				exchange.setProperty(Exchange.GROUPED_EXCHANGE, out);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
