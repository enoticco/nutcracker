package com.slekupuh.mts.nutcraker.core.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

// @ExtendWith(SpringExtension.class) ju5
@TestConfiguration
@EnableJms
public class JMSTestConfig {

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");

        broker.setPersistent(false);
        return broker;
    }

    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnection connection = ActiveMQConnection.makeConnection();
        connection.setTrustAllPackages(true); // todo:warn
        return new SingleConnectionFactory(connection);
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);// topic
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);  // topic
        return jmsTemplate;
    }
}
