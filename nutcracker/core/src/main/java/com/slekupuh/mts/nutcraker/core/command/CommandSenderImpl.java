package com.slekupuh.mts.nutcraker.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class CommandSenderImpl implements CommandSender{
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Command command, CommandQueue commandQueue)  {
        this.jmsTemplate.send(commandQueue.getQueue(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(command);
            }
        });
    }
}
