package com.slekupuh.mts.nutcraker.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class DomainEvent {
    private final UUID processId;
}
