package net.vounty.studios.templates;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import net.vounty.studios.templates.bot.Bot;
import net.vounty.studios.templates.config.TeamSpeakConfig;
import net.vounty.studios.templates.event.TeamSpeakEvents;
import net.vounty.studios.templates.utilities.enums.PlatformType;

public class TeamSpeakBot extends Bot<TeamSpeakBot> {

    private Templates templates;

    private final TS3Config config = new TS3Config();
    private TS3Query query;
    private TS3Api api;

    private TeamSpeakConfig teamSpeakConfig;

    @Override
    public void initialize() {
        this.templates = new Templates(PlatformType.TEAMSPEAK, this);
        this.getTemplates().getConfigManager().saveConfig("teamspeak-config.json", new TeamSpeakConfig(false, "127.0.0.1", 9987, "templates", "*PASTE_PASSWORD_HERE*"), false);
        this.teamSpeakConfig = this.getTemplates().getConfigManager().readConfig("teamspeak-config.json", TeamSpeakConfig.class);
        if (this.teamSpeakConfig != null && !this.teamSpeakConfig.getEnabled()) this.terminate();

        this.getConfig().setHost(this.getTeamSpeakConfig().getHost());

        this.query = new TS3Query(this.getConfig());
        this.getQuery().connect();

        this.api = this.getQuery().getApi();

        this.getAPI().login(this.getTeamSpeakConfig().getQueryName(), this.getTeamSpeakConfig().getQueryPassword());
        this.getAPI().selectVirtualServerById(1);
        this.getAPI().setNickname("Templates");
        this.getAPI().registerAllEvents();
        this.getAPI().addTS3Listeners(new TeamSpeakEvents(this));

        Runtime.getRuntime().addShutdownHook(this.getShutdownHook());
    }

    @Override
    public void terminate() {
        this.getQuery().exit();
        Runtime.getRuntime().removeShutdownHook(this.getShutdownHook());
    }

    public Templates getTemplates() {
        return templates;
    }

    public TS3Config getConfig() {
        return config;
    }

    public TS3Query getQuery() {
        return query;
    }

    public TS3Api getAPI() {
        return api;
    }

    public TeamSpeakConfig getTeamSpeakConfig() {
        return teamSpeakConfig;
    }

}
