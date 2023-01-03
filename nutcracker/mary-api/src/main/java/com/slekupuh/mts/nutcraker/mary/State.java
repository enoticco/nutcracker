package com.slekupuh.mts.nutcraker.mary;

public record State(Pose state, String coordinates) {
    public enum Pose { SIT_DOWN }

}
