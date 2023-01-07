package com.slekupuh.mts.nutcraker.core.transport.command;


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

@SpringBootTest(classes = {CommandSenderImplTest.Config.class, JMSTestConfig.class},
        properties = {"broker.port=61618"})
public class CommandSenderImplTest implements CommandQueue {

    @Autowired
    private TestCommandHandler handler;
    @Autowired
    private CommandSender commandSender;

    @Test
    public void commandSenderTest() throws InterruptedException, JMSException {
        UUID processId = UUID.randomUUID();
        commandSender.send(new TestCommand(processId, "Command Sender Test"), this);
        Thread.sleep(1000);
        ObjectMessage actual = handler.getObjectMessage();
        assertEquals(processId, ((TestCommand) actual.getObject()).getProcessId());
    }

    @Override
    public String getQueueName() {
        return "testQueue";
    }

    @Configuration
    static class Config {

        @Bean
        public TestCommandHandler testCommandHandler() {
            return new TestCommandHandler();
        }

        @Bean
        public CommandSender commandSender(JmsTemplate jmsTemplate) {
            return new CommandSenderImpl(jmsTemplate);
        }

    }

    @Getter
    static class TestCommand extends Command {
        private final String text;

        public TestCommand(UUID processId, String text) {
            super(processId);
            this.text = text;
        }
    }

    @Getter
    @Setter
    static class TestCommandHandler {

        private ObjectMessage objectMessage;

        @JmsListener(destination = "testQueue")
        public void handle(ObjectMessage objectMessage) {
            this.objectMessage = objectMessage;
        }

    }
}
