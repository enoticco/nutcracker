package com.slekupuh.mts.nutcraker.godfather.event;

import com.slekupuh.mts.nutcraker.core.event.DomainEvent;
import com.slekupuh.mts.nutcraker.godfather.action.Action;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GodfatherChangedStatusDomainEvent extends DomainEvent {
    private final Action action;
    public GodfatherChangedStatusDomainEvent(UUID processId, Action action) {
        super(processId);
        this.action = action;
    }
}
