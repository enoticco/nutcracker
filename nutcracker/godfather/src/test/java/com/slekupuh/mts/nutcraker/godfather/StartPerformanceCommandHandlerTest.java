package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.core.command.Command;
import com.slekupuh.mts.nutcraker.core.command.CommandSender;
import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommandHandler;
import com.slekupuh.mts.nutcraker.godfather.config.JMSTestConfig;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {JMSTestConfig.class, StartPerformanceCommandHandlerTest.Config.class})
public class StartPerformanceCommandHandlerTest {

    @Autowired
    private StartPerformanceCommandHandler handler;

    @Autowired
    private CommandSender commandSender;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void startPerformanceCommandHandleTest() throws JMSException, InterruptedException {
        StartPerformanceCommand command = new StartPerformanceCommand(UUID.randomUUID());
        verify(handler, times(0)).commandHandle(any(Command.class));
        this.jmsTemplate.send("godfatherCommand", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(command);
            }
        });
        Thread.sleep(1000);
        verify(handler, times(1)).commandHandle(argThat(new MessageMatcher(command)));
    }

    public static class Config {

        @Bean
        public CommandSender commandSender() {
            return mock(CommandSender.class);
        }

        @Bean
        public StartPerformanceCommandHandler handler(CommandSender commandSender) {
            return spy(new StartPerformanceCommandHandler(commandSender));
        }
    }

    @AllArgsConstructor
    private class MessageMatcher implements ArgumentMatcher<StartPerformanceCommand> {
        private StartPerformanceCommand left;

        @Override
        public boolean matches(StartPerformanceCommand om) {
            return om.getProcessId().equals(left.getProcessId());
        }
    }

}
