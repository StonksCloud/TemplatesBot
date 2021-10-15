package net.vounty.studios.templates.utilities.enums;

public enum PathType {

    VOUNTYSTUDIOS_API("https://api.vounty.net/"),
    VOUNTYSTUDIOS_TEMPLATES_GET("https://api.vounty.net/route/templates/%s"),
    VOUNTYSTUDIOS_TEMPLATES_LIST("https://api.vounty.net/route/templates/"),
    VOUNTYSTUDIOS_TEMPLATES_ADD("https://api.vounty.net/route/templates/create");

    private final String url;

    PathType(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

}
