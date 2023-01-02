package com.slekupuh.mts.nutcraker.core.command;

import lombok.extern.slf4j.Slf4j;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Slf4j
public abstract class CommandHandler<T extends Command> {
    public abstract void commandHandle(T command);

    public final void handle(ObjectMessage command) {
        try {
            commandHandle((T) command.getObject());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
