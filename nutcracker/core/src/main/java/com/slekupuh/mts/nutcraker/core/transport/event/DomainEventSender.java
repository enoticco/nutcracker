package com.slekupuh.mts.nutcraker.core.transport.event;

public interface DomainEventSender {

    public void send(DomainEvent domainEvent);
}
