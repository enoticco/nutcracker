package com.slekupuh.mts.nutcraker.core.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class Command {
    private final UUID processId;
}
