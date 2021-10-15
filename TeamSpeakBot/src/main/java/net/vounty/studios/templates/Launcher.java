package net.vounty.studios.templates;

public class Launcher {

    private static final Launcher launcher = new Launcher();
    private final TeamSpeakBot teamSpeakBot = new TeamSpeakBot();

    public static void main(String[] args) {
        getLauncher().getTeamSpeakBot().initialize();
    }

    public TeamSpeakBot getTeamSpeakBot() {
        return teamSpeakBot;
    }

    public static Launcher getLauncher() {
        return launcher;
    }

}
