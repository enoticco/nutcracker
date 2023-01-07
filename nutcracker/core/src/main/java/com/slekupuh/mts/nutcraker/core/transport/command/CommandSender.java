package com.slekupuh.mts.nutcraker.core.transport.command;

public interface CommandSender {
    public void send(Command command, CommandQueue commandQueue);
}
