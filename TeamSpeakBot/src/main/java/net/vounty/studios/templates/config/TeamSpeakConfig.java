package net.vounty.studios.templates.config;

public class TeamSpeakConfig {

    private final Boolean enabled;
    private final String host;
    private final Integer port;
    private final String queryName, queryPassword;

    public TeamSpeakConfig(Boolean enabled, String host, Integer port, String queryName, String queryPassword) {
        this.enabled = enabled;
        this.host = host;
        this.port = port;
        this.queryName = queryName;
        this.queryPassword = queryPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getQueryName() {
        return queryName;
    }

    public String getQueryPassword() {
        return queryPassword;
    }

}
