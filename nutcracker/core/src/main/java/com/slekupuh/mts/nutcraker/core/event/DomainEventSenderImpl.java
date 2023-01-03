package com.slekupuh.mts.nutcraker.core.event;

import com.slekupuh.mts.nutcraker.core.event.DomainEvent;
import com.slekupuh.mts.nutcraker.core.event.DomainEventChannel;
import com.slekupuh.mts.nutcraker.core.event.DomainEventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class DomainEventSenderImpl  implements DomainEventSender {
     @Autowired
    private JmsTemplate jmsTemplate;

    public void send(DomainEvent domainEvent){

        this.jmsTemplate.send(DomainEventChannel.COMMON_TOPIC.getChannel(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(domainEvent);
            }
        });
    }
}
