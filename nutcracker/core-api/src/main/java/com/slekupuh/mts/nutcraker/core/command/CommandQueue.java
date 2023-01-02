package com.slekupuh.mts.nutcraker.core.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandQueue {
    MARY("maryCommand"), GODFATHER("godfatherCommand");
    private String queue;
}
