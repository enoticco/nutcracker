package com.slekupuh.mts.nutcraker.godfather.event;

import com.slekupuh.mts.nutcraker.core.event.DomainEvent;
import com.slekupuh.mts.nutcraker.core.event.SpeechDomainEvent;
import com.slekupuh.mts.nutcraker.godfather.Godfather;
import com.slekupuh.mts.nutcraker.mary.event.MaryChangedStatusDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import static com.slekupuh.mts.nutcraker.mary.State.Pose.SIT_DOWN;
public class GodfatherDomainEventHandler {
    @Autowired
    private Godfather godfather;
    @JmsListener(destination = "common-domain-event")
    public void eventHandle(DomainEvent command) {
        switch (command) {
            case MaryChangedStatusDomainEvent maryStatus &&  (maryStatus.getState().state() ==  SIT_DOWN)
                    -> godfather.maryInteraction(command.getProcessId());

            case SpeechDomainEvent gfStatus -> godfather.silence(command.getProcessId());

            default -> throw new IllegalStateException("Unexpected value: " + command);
        }

    }

}
