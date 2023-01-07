package com.slekupuh.mts.nutcraker.mary.event;

import com.slekupuh.mts.nutcraker.core.transport.event.DomainEvent;
import com.slekupuh.mts.nutcraker.mary.State;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MaryChangedStatusDomainEvent extends DomainEvent {
    private final State state;

    public MaryChangedStatusDomainEvent(UUID processId, State state) {
        super(processId);
        this.state = state;
    }
}
