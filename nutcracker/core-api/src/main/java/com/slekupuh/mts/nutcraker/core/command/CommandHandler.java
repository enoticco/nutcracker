package com.slekupuh.mts.nutcraker.core.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CommandHandler<T extends Command> {
    public abstract void handle(T command);

}
