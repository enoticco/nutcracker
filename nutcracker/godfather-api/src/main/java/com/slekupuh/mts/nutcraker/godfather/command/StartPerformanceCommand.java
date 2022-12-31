package com.slekupuh.mts.nutcraker.godfather.command;

import com.slekupuh.mts.nutcraker.core.command.Command;

import java.util.UUID;

public class StartPerformanceCommand extends Command {
    public StartPerformanceCommand(UUID processId) {
        super(processId);
    }
}
