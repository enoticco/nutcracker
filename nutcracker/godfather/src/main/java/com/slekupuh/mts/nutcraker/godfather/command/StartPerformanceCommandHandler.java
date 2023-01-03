package com.slekupuh.mts.nutcraker.godfather.command;

import com.slekupuh.mts.nutcraker.core.command.CommandQueue;
import com.slekupuh.mts.nutcraker.core.command.Command;
import com.slekupuh.mts.nutcraker.core.command.CommandSender;
import com.slekupuh.mts.nutcraker.mary.State;
import com.slekupuh.mts.nutcraker.mary.command.ChangeStateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartPerformanceCommandHandler {

    @Autowired
    private final CommandSender commandSender;
    @JmsListener(destination = "godfatherCommand")
    public void commandHandle(Command command){
        switch (command){
            case StartPerformanceCommand st -> {
                ChangeStateCommand maryCommand = new ChangeStateCommand(UUID.randomUUID(),
                        new State(State.Pose.SIT_DOWN,"On godfather lap"));
                log.debug(" command {} processId {}", command, st.getProcessId());
                commandSender.send(maryCommand, CommandQueue.MARY);
            }
            default ->  log.debug(" command {} processId {}", command, command.getProcessId());
        }


    }
}
