package com.base2.javaone.demo.jms;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

/**
 * JNDI Context and JMS Connection Factory producer
 *
 * @author aaronwalker
 */
public class ConnectionFactoryProducer {

    @Produces @JMS
    public ConnectionFactory createConnectionFactory() throws NamingException {
        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName());
        ConnectionFactory cf = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
        return cf;
    }
}
