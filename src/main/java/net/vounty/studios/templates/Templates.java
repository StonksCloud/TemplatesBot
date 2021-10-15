package net.vounty.studios.templates;

import net.vounty.studios.templates.bot.Bot;
import net.vounty.studios.templates.manager.ConfigManager;
import net.vounty.studios.templates.manager.ProtocolManager;
import net.vounty.studios.templates.utilities.enums.PlatformType;

public class Templates {

    private final PlatformType platformType;
    private final Bot<?> bot;

    private final ConfigManager configManager;
    private final ProtocolManager protocolManager;

    public Templates(PlatformType platformType, Bot<?> bot) {
        this.bot = bot;
        this.platformType = platformType;
        this.configManager = new ConfigManager();
        this.protocolManager = new ProtocolManager(this);
    }

    public Bot<?> getBot() {
        return bot;
    }

    public PlatformType getPlatform() {
        return platformType;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

}
