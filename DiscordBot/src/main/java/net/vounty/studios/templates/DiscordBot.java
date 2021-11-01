package net.vounty.studios.templates;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.vounty.studios.templates.bot.Bot;
import net.vounty.studios.templates.config.DiscordConfig;
import net.vounty.studios.templates.events.DiscordMessageEvent;
import net.vounty.studios.templates.utilities.enums.PlatformType;

import java.util.Arrays;

public class DiscordBot extends Bot<DiscordBot> {

    private Templates templates;
    private JDA discordJda;

    private DiscordConfig discordConfig;

    @Override
    public void initialize() {
        this.templates = new Templates(PlatformType.DISCORD, this);
        this.getTemplates().getConfigManager().saveConfig("discord-config.json", new DiscordConfig(false, ""), false);
        this.discordConfig = this.getTemplates().getConfigManager().readConfig("discord-config.json", DiscordConfig.class);
        if (this.discordConfig != null && !this.discordConfig.getEnabled()) this.terminate();

        assert this.discordConfig != null;
        JDABuilder builder = JDABuilder.createLight(this.discordConfig.getToken(), Arrays.asList(GatewayIntent.values()));

        builder.enableIntents(Arrays.asList(GatewayIntent.values()));
        builder.enableCache(Arrays.asList(CacheFlag.values()));
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.playing("studios.vounty.net"));

        builder.addEventListeners(new DiscordMessageEvent(this));

        try {
            discordJda = builder.build();
            Runtime.getRuntime().addShutdownHook(this.getShutdownHook());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void terminate() {
        this.templates = null;
        this.discordJda.shutdownNow();
        Runtime.getRuntime().removeShutdownHook(this.getShutdownHook());
        System.exit(0);
    }

    public Templates getTemplates() {
        return templates;
    }

    public JDA getDiscordJDA() {
        return discordJda;
    }

    public DiscordConfig getDiscordConfig() {
        return discordConfig;
    }

}
