package net.vounty.studios.templates;

public class Launcher {

    private static final Launcher launcher = new Launcher();
    private final DiscordBot discordBot = new DiscordBot();

    public static void main(String[] args) {
        getLauncher().getDiscordBot().initialize();
    }

    public static Launcher getLauncher() {
        return launcher;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

}
