package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
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

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JMSTestConfig.class, StartPerformanceCommandHandlerTest.Config.class})
public class StartPerformanceCommandHandlerTest {

    @Autowired
    private StartPerformanceCommandHandler handler;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void handleTest() throws JMSException, InterruptedException {
        StartPerformanceCommand command = new StartPerformanceCommand(UUID.randomUUID());
        verify(handler, times(0)).commandHandle(any(StartPerformanceCommand.class));
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
        public StartPerformanceCommandHandler handler() {
            return spy(new StartPerformanceCommandHandler());
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
