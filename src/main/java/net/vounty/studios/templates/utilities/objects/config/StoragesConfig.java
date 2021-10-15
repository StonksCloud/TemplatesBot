package net.vounty.studios.templates.utilities.objects.config;

import net.vounty.studios.templates.utilities.objects.PlatformStorage;

import java.util.List;

public class StoragesConfig {

    private final List<PlatformStorage> templates;

    public StoragesConfig(List<PlatformStorage> templates) {
        this.templates = templates;
    }

    public List<PlatformStorage> getTemplates() {
        return templates;
    }

}
