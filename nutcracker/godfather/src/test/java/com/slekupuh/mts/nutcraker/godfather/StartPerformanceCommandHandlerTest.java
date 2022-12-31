package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import com.slekupuh.mts.nutcraker.godfather.config.JMSTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
//@RunWith(SpringRunner.class)
@SpringBootTest
@Import(JMSTestConfig.class)
public class StartPerformanceCommandHandlerTest {
    private StartPerformanceCommandHandler handler;

    @Test
    public void handleTest(){
        StartPerformanceCommand command = new StartPerformanceCommand(UUID.randomUUID());
        handler.handle(command);
    }
}
