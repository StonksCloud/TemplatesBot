package net.vounty.studios.templates.config;

public class DiscordConfig {

    private final Boolean enabled;
    private final String token;

    public DiscordConfig(Boolean enabled, String token) {
        this.enabled = enabled;
        this.token = token;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getToken() {
        return token;
    }

}
