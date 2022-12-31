package com.slekupuh.mts.nutcraker.godfather;


import com.slekupuh.mts.nutcraker.core.command.CommandHandler;
import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StartPerformanceCommandHandler extends CommandHandler<StartPerformanceCommand> {
    @Override
    @JmsListener(destination = "godfatherCommand", containerFactory = "commandFactory")
    public void handle(StartPerformanceCommand command) {
        // todo:next
    }
}
