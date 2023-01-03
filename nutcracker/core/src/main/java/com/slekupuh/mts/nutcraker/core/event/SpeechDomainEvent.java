package com.slekupuh.mts.nutcraker.core.event;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SpeechDomainEvent extends DomainEvent{
   private final String text;
    public SpeechDomainEvent(UUID processId, String text) {
        super(processId);
        this.text = text;
    }
}
