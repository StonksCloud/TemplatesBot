package net.vounty.studios.templates.manager;

import com.google.gson.Gson;
import net.vounty.studios.templates.Templates;
import net.vounty.studios.templates.utilities.enums.PathType;
import net.vounty.studios.templates.utilities.objects.PlatformStorage;
import net.vounty.studios.templates.utilities.objects.config.StoragesConfig;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProtocolManager {

    private final Templates templates;

    public ProtocolManager(Templates templates) {
        this.templates = templates;
    }

    public Optional<List<PlatformStorage>> getPlatformStorages() {
        List<PlatformStorage> array = new LinkedList<>();
        StoragesConfig storagesConfig = this.getTemplates().getConfigManager().readConfig(PathType.VOUNTYSTUDIOS_TEMPLATES_LIST.getURL(), StoragesConfig.class);
        if (storagesConfig != null) array.addAll(storagesConfig.getTemplates());
        return array.size() == 0 ? Optional.empty() : Optional.of(array);
    }

    public Optional<PlatformStorage> getPlatformStorage(String uniqueId) {
        PlatformStorage platformStorage = this.getTemplates().getConfigManager().readConfig(PathType.VOUNTYSTUDIOS_TEMPLATES_GET.getURL().replace("%s", uniqueId), PlatformStorage.class);
        if (platformStorage != null) return Optional.of(platformStorage);
        return Optional.empty();
    }

    public boolean createNewStorageElement(PlatformStorage platformStorage) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PathType.VOUNTYSTUDIOS_TEMPLATES_ADD.getURL()))
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(new Gson().toJson(platformStorage)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isBackendOnline() {
        return this.isWebsiteOnline(PathType.VOUNTYSTUDIOS_API.getURL());
    }

    public boolean isWebsiteOnline(String targetUrl) {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception exception) {
            return false;
        }
    }

    public Templates getTemplates() {
        return templates;
    }

}
