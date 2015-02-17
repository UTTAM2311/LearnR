package com.examples.quixey;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.examples.quixey.endpoints.GeoLocEndpoint;
import com.examples.quixey.endpoints.SearchEndpoint;

public class QuixeyMain {
	
	public static void main(String[] args) throws Exception {

		final CamelContext cc = new DefaultCamelContext();
		
		// Add routes
		cc.addRoutes(new GeoLocEndpoint());
		//cc.addRoutes(new SearchEndpoint());
		

		cc.start();

		// Handle shutdown
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					cc.stop();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		waitForStop();

	}

	static void waitForStop() {
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
