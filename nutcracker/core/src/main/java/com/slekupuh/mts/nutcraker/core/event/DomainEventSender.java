package com.slekupuh.mts.nutcraker.core.event;

import com.slekupuh.mts.nutcraker.core.event.DomainEvent;

public interface DomainEventSender {

    public void send(DomainEvent domainEvent);
}
