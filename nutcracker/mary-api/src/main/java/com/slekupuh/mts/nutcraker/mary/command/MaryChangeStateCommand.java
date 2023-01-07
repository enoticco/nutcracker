package com.slekupuh.mts.nutcraker.mary.command;

import com.slekupuh.mts.nutcraker.mary.State;
import lombok.Getter;
import com.slekupuh.mts.nutcraker.core.transport.command.Command;


import java.util.UUID;

@Getter
public class MaryChangeStateCommand extends Command {
    private final State state;

    public MaryChangeStateCommand(UUID processId, State state) {
        super(processId);
        this.state = state;
    }
}
