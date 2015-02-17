package com.examples.quixey.endpoints;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

public class SearchEndpoint extends RouteBuilder {
	
	static final String HTTP_BRIDGE_OPTS = "?bridgeEndpoint=true&throwExceptionOnFailure=false";
	
	static final String ENDPOINT_URL = "http://localhost:8080/v2/search";
	
	static final String MTS_APP_SEARCH_URL = "http://search-be.stage.quixey.com:8080/app_search";
	static final String MTS_QUP_URL = "http://qup-i-1d6b52d5.us-west-1a.stage.quixey.com:8778/detectors";
	static final String MTS_RBSE_URL = "http://state-translation-i-b5b2c476.us-west-1c.stage.quixey.com:8778/parse";
	static final String MTS_SEARCH_INSIDE_URL = "http://search-inside-stage-i-dd93e51e.us-west-1c.stage.quixey.com:8778/search_inside";
	
	static final String MTS_SCORING_URL = "http://scoring-i-96b9ba5c.us-west-1c.stage.quixey.com:8778/score";
	

	@Override
	public void configure() throws Exception {
		
		// Background calls
		from("direct:bg_aggregate")
			.multicast(new GroupedExchangeAggregationStrategy()).parallelProcessing()
				.enrich(MTS_APP_SEARCH_URL + HTTP_BRIDGE_OPTS)
				.enrich(MTS_RBSE_URL + HTTP_BRIDGE_OPTS)
				.enrich(MTS_SEARCH_INSIDE_URL + HTTP_BRIDGE_OPTS)
			.end();
		
		// MTS : content service call
		from("direct:content_service")
			.to("");
		
		// Main Endpoint Route
		from("jetty:" + ENDPOINT_URL)
			.to(MTS_QUP_URL + HTTP_BRIDGE_OPTS)
			.process(new QUPProcessor())
			.enrich("direct:bg_aggregate").setBody(property(Exchange.GROUPED_EXCHANGE))
			.process(new BGcontentProcessor())
			.to("direct:content_service")
			.process(new V2SearchProcessor());
		
		
	}
	
	
	/* --- Utilities --- */
	
	class QUPProcessor implements Processor {
		
		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BGcontentProcessor implements Processor {
		
		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class V2SearchProcessor implements Processor {
		
		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
