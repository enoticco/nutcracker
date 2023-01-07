package com.slekupuh.mts.nutcraker.core.transport.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private final UUID processId;
}
