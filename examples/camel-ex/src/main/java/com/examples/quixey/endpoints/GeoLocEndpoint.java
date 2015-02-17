package com.examples.quixey.endpoints;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;

public class GeoLocEndpoint extends RouteBuilder {

	static final String ENDPOINT_URL = "http://localhost:8080/sdk/android/v1/geolocate";
	static final String MTS_URL = "http://geo-service-i-5a91fe99.us-west-1c.stage.quixey.com:10106/geodata";

	@Override
	public void configure() throws Exception {

		from("jetty:" + ENDPOINT_URL)
		.process(new GeoEndpointProcessor())
			.to(MTS_URL + "?bridgeEndpoint=true&throwExceptionOnFailure=false")
			.process(new GeoJsonProcessor());

	}

	/* --- Utilities --- */

	class GeoEndpointProcessor implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			Object client_ip = exchange.getIn().getHeader("client_ip");
			exchange.getIn().setHeader(Exchange.HTTP_QUERY, "ip_address=" + client_ip);
		}

	}

	class GeoJsonProcessor implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JSONObject in = new JSONObject(exchange.getIn().getBody(String.class));

			// prepare out
			JSONObject out = new JSONObject();
			out.put("country", in.getJSONObject("edge").getString("country"));
			out.put("city", in.getJSONObject("edge").getString("city"));

			exchange.getOut().setBody(out);
		}
	
	}
	
}
