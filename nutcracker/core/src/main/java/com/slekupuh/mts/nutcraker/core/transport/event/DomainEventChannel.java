package com.slekupuh.mts.nutcraker.core.transport.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainEventChannel {
    COMMON_TOPIC("common-domain-event");
    private final String channel;
}
