package com.slekupuh.mts.nutcraker.godfather;

import com.slekupuh.mts.nutcraker.godfather.action.Action;

import java.util.UUID;

public interface Godfather {
    public void maryInteraction(UUID processId);
    public void silence(UUID processId);
}
