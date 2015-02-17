package com.examples.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerRouteBuilder extends RouteBuilder {

	private static final Logger logger = LoggerFactory.getLogger(TimerRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("timer://timer1?period=1000")
        .process(new Processor() {
            public void process(Exchange msg) {
            	logger.info("Processing {}", msg);
            }
        });

	}

}
