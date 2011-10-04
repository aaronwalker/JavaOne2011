package com.base2.javaone.demo.jms;

import org.hornetq.api.jms.HornetQJMSClient;

import javax.inject.Inject;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * Receive a message via JMS
 *
 * @author aaronwalker
 */
public class JMSReceiver {

    private static final int NO_DELAY = 10;
    private static final int DEFAULT_DELAY = 30000;

    @Inject @JMS
    private ConnectionFactory connectionFactory;

    private Connection connection;
    private Session session;

    public Message receiveNoWait(String queue) {
        return receiveMessage(queue, NO_DELAY);
    }

    public Message receiveMessage(String queue) {
        return receiveMessage(queue,DEFAULT_DELAY);
    }

    public Message receiveMessage(String queue, int delay) {
        try {
        	connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(getQueue(queue));
            Message m = consumer.receive(delay);
            consumer.close();
            return m;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally{
        	close();
        }
    }

    protected Destination getQueue(String queue) throws NamingException {
        return (Destination) HornetQJMSClient.createQueue(queue);
    }

    public void close() {
        try {
            if(session != null) {
                session.close();
            }
            if(connection != null) {
                connection.stop();
                connection.close();
            }
        }
        catch(JMSException ex) {}
    }

}
