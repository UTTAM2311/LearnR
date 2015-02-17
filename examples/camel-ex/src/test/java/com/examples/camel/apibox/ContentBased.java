package com.examples.camel.apibox;

import org.apache.camel.builder.RouteBuilder;

public class ContentBased extends RouteBuilder {
	public static Object value;
	
	@Override
	public void configure() throws Exception {

		from("jetty:http://0.0.0.0:8080/content")
		.choice()
			.when(header("service").isEqualTo("customer"))
				.to("http://localhost:5000/customer?bridgeEndpoint=true&throwExceptionOnFailure=false")
			.otherwise()
				.to("http://localhost:5000/order?bridgeEndpoint=true&throwExceptionOnFailure=false");
		
		
	}

}
