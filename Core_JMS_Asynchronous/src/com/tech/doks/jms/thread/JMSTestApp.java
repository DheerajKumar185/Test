package com.tech.doks.jms.thread;

public class JMSTestApp {

	public static void main(String[] args) throws Exception {
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldProducer(), false);
		Thread.sleep(1000);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldConsumer(), false);
		Thread.sleep(1000);
	}
	
	public static void thread(Runnable runnable, boolean daemon) {
		Thread newThread = new Thread(runnable);
		newThread.setDaemon(daemon);
		newThread.start();
	}
}