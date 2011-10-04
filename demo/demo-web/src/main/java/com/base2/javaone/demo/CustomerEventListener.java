package com.base2.javaone.demo;

import com.base2.javaone.demo.domain.Customer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.jms.*;

/**
 * Listens for customer events and sends them to JMS
 *
 * @author aaronwalker
 */
@Stateless
public class CustomerEventListener {

    @Resource(mappedName = "java:/ConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/test")
    Queue queue;

    public void customerCreateEvent(@Observes Customer customer) {

        System.out.println("Customer:" + customer.getId() + " created");

        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            producer.send(session.createTextMessage("Customer:" + customer.getId() + " created"));

        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }

    }
}
