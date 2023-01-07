package com.slekupuh.mts.nutcraker.core.transport.event;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
@RequiredArgsConstructor
public class DomainEventSenderImpl implements DomainEventSender {
    private final JmsTemplate jmsTemplate;

    public void send(DomainEvent domainEvent) {
        this.jmsTemplate.send(DomainEventChannel.COMMON_TOPIC.getChannel(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(domainEvent);
            }
        });
    }
}
