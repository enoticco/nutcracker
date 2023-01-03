package com.slekupuh.mts.nutcraker.mary.command;

import com.slekupuh.mts.nutcraker.mary.State;
import lombok.Getter;
import com.slekupuh.mts.nutcraker.core.command.Command;


import java.util.UUID;

@Getter
public class ChangeStateCommand extends Command {
    private final State state;

    public ChangeStateCommand(UUID processId, State state) {
        super(processId);
        this.state = state;
    }
}
