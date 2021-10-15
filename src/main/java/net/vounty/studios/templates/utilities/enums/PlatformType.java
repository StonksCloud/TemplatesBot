package net.vounty.studios.templates.utilities.enums;

public enum PlatformType {

    DISCORD("discord"),
    TEAMSPEAK("teamspeak");

    private final String name;

    PlatformType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
