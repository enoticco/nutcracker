package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StartPerformanceCommandHandlerTest {
    private StartPerformanceCommandHandler handler;

    @Test
    public void handleTest(){
        StartPerformanceCommand command = new StartPerformanceCommand(UUID.randomUUID());
        handler.handle(command);
    }
}
