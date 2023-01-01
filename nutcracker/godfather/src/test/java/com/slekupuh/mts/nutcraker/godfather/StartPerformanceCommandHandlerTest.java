package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import com.slekupuh.mts.nutcraker.godfather.config.JMSTestConfig;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
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
    public void handleTest() throws JMSException {
        ActiveMQObjectMessage message = new ActiveMQObjectMessage();
        StartPerformanceCommand command = new StartPerformanceCommand(UUID.randomUUID());
        message.setObject(command);

        verify(handler, times(0)).handle(command);

        jmsTemplate.convertAndSend("godfatherCommand", command);

        verify(handler, times(1)).handle(command);
    }

    public static class Config {
        @Bean
        public StartPerformanceCommandHandler handler() {
            return spy(new StartPerformanceCommandHandler());
        }
    }

}
