package net.vounty.studios.templates.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

public class ConfigManager {

    public void saveConfig(String filePath, Object object, Boolean override) {
        try {
            File currentFile = new File(filePath);
            if (override) {
                if (!currentFile.exists()) currentFile.createNewFile();
                Writer writer = new PrintWriter(currentFile, "UTF-8");
                this.generateBuilder().toJson(object, writer);
                writer.close();
            } else {
                if (!currentFile.exists()) {
                    currentFile.createNewFile();
                    Writer writer = new PrintWriter(currentFile, "UTF-8");
                    this.generateBuilder().toJson(object, writer);
                    writer.close();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public <T> T readConfig(String filePath, Class<T> type) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            return new Gson().fromJson(inputStreamReader, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Gson generateBuilder() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .serializeNulls()
                .serializeSpecialFloatingPointValues()
                .setLenient()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .create();
    }

}
