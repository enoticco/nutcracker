package com.slekupuh.mts.nutcraker.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


public interface CommandSender {
    public void send(Command command, CommandQueue commandQueue);
}
