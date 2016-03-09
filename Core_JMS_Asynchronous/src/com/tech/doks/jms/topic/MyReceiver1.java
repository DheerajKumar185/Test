package com.tech.doks.jms.topic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
public class MyReceiver1 {
	public static void main(String[] args) throws JMSException{
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			//Create a ConncetionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://MSPL-08-09-D158:61616");
			//Create a Connection
			connection = connectionFactory.createConnection();
			connection.start();
			//Create a session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//Create the destination (Topic or Queue)
			Destination destination = session.createTopic("HELLOWORLD.TESTTOPIC");
			//Create a MessageProducer from the Session to the Topic or Queue
			consumer = session.createConsumer(destination);
			while (true) {
			    Message m = consumer.receive(1); 
			    if (m != null) { 
			        if (m instanceof TextMessage) { 
			        	TextMessage message = (TextMessage) m; 
			            System.out.println("Received message: " + message.getText()); 
			        } else { 
			            break; 
			        } 
			    }
			}
			
		} catch(Exception e) {
			System.out.println("Error Occure : " + e);
			e.printStackTrace();
		} finally {
			consumer.close();
			session.close();
			connection.close();
		}
	}
}