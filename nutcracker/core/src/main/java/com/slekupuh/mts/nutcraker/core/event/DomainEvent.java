package com.slekupuh.mts.nutcraker.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class DomainEvent implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private final UUID processId;
}
