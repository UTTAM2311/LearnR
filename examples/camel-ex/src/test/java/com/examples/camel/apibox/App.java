package com.examples.camel.apibox;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 *
 */
public class App {
	
	
	public static void main(String[] args) throws Exception {

		final CamelContext cc = new DefaultCamelContext();
		
		// Add routes
		cc.addRoutes(new Proxy());
		cc.addRoutes(new ContentBased());
		cc.addRoutes(new APIBoxAggregator());

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
