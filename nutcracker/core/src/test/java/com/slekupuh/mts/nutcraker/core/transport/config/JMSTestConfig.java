package com.slekupuh.mts.nutcraker.core.transport.config;

import com.slekupuh.mts.nutcraker.core.transport.DefaultJmsErrorHandler;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
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
import java.net.URISyntaxException;

@TestConfiguration
@EnableJms
public class JMSTestConfig {

    private @Value("${broker.port:61616}") String port;
    private @Value("${broker.topic:false}") boolean isTopic;

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("brokerName_" + port);
        broker.addConnector("tcp://localhost:" + port);

        broker.setPersistent(false);
        return broker;
    }

    @Bean
    public ConnectionFactory connectionFactory() throws JMSException, URISyntaxException {
        ActiveMQConnection connection = ActiveMQConnection.makeConnection("failover://tcp://localhost:" + port);
        connection.setTrustAllPackages(true); // todo:warn
        return new SingleConnectionFactory(connection);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public DefaultJmsErrorHandler defaultJmsErrorHandler() {
        return new DefaultJmsErrorHandler();
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                      MessageConverter messageConverter,
                                                                      DefaultJmsErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter);
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        factory.setPubSubDomain(isTopic);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(isTopic);
        return jmsTemplate;
    }

}
