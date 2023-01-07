package com.slekupuh.mts.nutcraker.core.transport.event;


import com.slekupuh.mts.nutcraker.core.transport.command.CommandQueue;
import com.slekupuh.mts.nutcraker.core.transport.config.JMSTestConfig;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DomainEventSenderImplTest.Config.class, JMSTestConfig.class},
        properties = {"broker.port=61617", "broker.topic=true"})
public class DomainEventSenderImplTest implements CommandQueue {

    @Autowired
    private TestDomainEventHandlerA handlerA;

    @Autowired
    private TestDomainEventHandlerB handlerB;

    @Autowired
    private DomainEventSender sender;

    @Test
    public void commandSenderTest() throws InterruptedException, JMSException {
        UUID processId = UUID.randomUUID();
        sender.send(new TestDomainEvent(processId, "Test Domain Event"));
        Thread.sleep(1000);
        ObjectMessage actualA = handlerA.getObjectMessage();
        ObjectMessage actualB = handlerB.getObjectMessage();
        assertEquals(processId, ((TestDomainEvent) actualA.getObject()).getProcessId());
        assertEquals(processId, ((TestDomainEvent) actualB.getObject()).getProcessId());
    }

    @Override
    public String getQueueName() {
        return "testQueue";
    }

    @Configuration
    static class Config {

        @Bean
        public TestDomainEventHandlerA testDomainEventHandlerA() {
            return new TestDomainEventHandlerA();
        }

        @Bean
        public TestDomainEventHandlerB testDomainEventHandlerB() {
            return new TestDomainEventHandlerB();
        }

        @Bean
        public DomainEventSender domainEventSender(JmsTemplate jmsTemplate) {
            return new DomainEventSenderImpl(jmsTemplate);
        }

    }

    @Getter
    static class TestDomainEvent extends DomainEvent {
        private final String text;

        public TestDomainEvent(UUID processId, String text) {
            super(processId);
            this.text = text;
        }
    }

    @Getter
    @Setter
    static class TestDomainEventHandlerA {

        private ObjectMessage objectMessage;

        @JmsListener(destination = "common-domain-event")
        public void handle(ObjectMessage objectMessage) {
            this.objectMessage = objectMessage;
        }
    }

    @Getter
    @Setter
    static class TestDomainEventHandlerB {

        private ObjectMessage objectMessage;

        @JmsListener(destination = "common-domain-event")
        public void handle(ObjectMessage objectMessage) {
            this.objectMessage = objectMessage;
        }
    }
}
