package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.core.event.SpeechDomainEvent;
import com.slekupuh.mts.nutcraker.godfather.action.Action;
import com.slekupuh.mts.nutcraker.core.event.DomainEventSenderImpl;
import com.slekupuh.mts.nutcraker.godfather.event.GodfatherChangedStatusDomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Service
@AllArgsConstructor
public class GodfatherImpl implements Godfather {
    private static final String SPEECH ="— Не слушай их, моя маленькая Мари! (set State =\"dont listen anybody\")\n" +
            " Тебе Бог дал больше, чем всем нам! Ты, как моя\n" +
            "Пирлипатхен в сказке, родилась принцессой (defaultState = \"Princess\") и умеешь править в чудесном, прекрасном королевстве, \n" +
            "что же касается твоего Щелкунчика (Nutchreker Object), то тебе придется перенести из-за него немало горя: \n" +
            "мышиный король преследует его везде; не я, а ты одна можешь его спасти, будь только стойкой и преданной!";

    @Autowired
    private final DomainEventSenderImpl domainEventSender;
    private final Set<Action> actions = new HashSet<>();

    public void maryInteraction(UUID processId){
        actions.add(Action.PAT_ON_THE_HEAD);
        domainEventSender.send(new GodfatherChangedStatusDomainEvent(processId, Action.PAT_ON_THE_HEAD));
        actions.add(Action.SPEAK);
        domainEventSender.send(new GodfatherChangedStatusDomainEvent(processId, Action.SPEAK));
        domainEventSender.send(new SpeechDomainEvent(processId, SPEECH));
    }

    public void silence(UUID processId){
        actions.remove(Action.SPEAK);
    }

}
