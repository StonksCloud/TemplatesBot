package net.vounty.studios.templates.utilities.objects;

import net.vounty.studios.templates.utilities.enums.PlatformType;

public class PlatformStorage {

    private final String uniqueId;
    private final PlatformType platform;
    private Boolean isInEditMode;
    private String[] keywords;

    public PlatformStorage(String uniqueId, PlatformType platform, Boolean isInEditMode, String... keywords) {
        this.uniqueId = uniqueId;
        this.platform = platform;
        this.isInEditMode = isInEditMode;
        this.keywords = keywords;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public PlatformType getPlatform() {
        return platform;
    }

    public Boolean isEditMode() {
        return isInEditMode;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setEditMode(Boolean inEditMode) {
        isInEditMode = inEditMode;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

}
