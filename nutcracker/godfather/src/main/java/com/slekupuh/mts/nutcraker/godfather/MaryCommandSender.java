package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.core.command.CommandQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import  com.slekupuh.mts.nutcraker.mary.command.ChangeStateCommand;
import  com.slekupuh.mts.nutcraker.mary.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
public class MaryCommandSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void putOnKnees() throws JMSException, InterruptedException {
        ChangeStateCommand command = new ChangeStateCommand(UUID.randomUUID(), new State(Pose));
        this.jmsTemplate.send(CommandQueue.MARY.getQueue(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(command);
            }
        });
    }
}
