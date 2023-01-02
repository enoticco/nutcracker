package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.core.command.CommandHandler;
import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartPerformanceCommandHandler extends CommandHandler<StartPerformanceCommand> {
    @Override
    public void commandHandle(StartPerformanceCommand command) {
        log.debug(" command {} processId {}", command, command.getProcessId());
        // todo:next
    }
}
