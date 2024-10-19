package net.riser876.easychannels.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;
import net.riser876.easychannels.config.adapters.PermissionDataAdapter;
import net.riser876.easychannels.config.data.ConfigData;
import net.riser876.easychannels.config.data.PermissionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("easychannels.json");
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(PermissionData.class, new PermissionDataAdapter())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();
    private static ConfigData configData = null;

    public static void load() {
        if (Files.notExists(CONFIG_PATH)) {
            loadDefaultConfig();
            return;
        }

        try {
            String json = Files.readString(CONFIG_PATH);
            configData = gson.fromJson(json, ConfigData.class);
            saveConfig();
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void loadDefaultConfig() {
        configData = new ConfigData();
        saveConfig();
    }

    private static void saveConfig() {
        try {
            String json = gson.toJson(configData);
            Files.write(CONFIG_PATH, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
