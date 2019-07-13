package me.WCSkywars.Storage;

import me.WCSkywars.MClass;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileManager {

    static File configYaml, arenaYaml, mainFolder = MClass.plugin.getDataFolder();
    static FileConfiguration config, arena;

    public FileManager() {
        if (!mainFolder.exists()) {
            mainFolder.mkdirs();
        }

        configYaml = new File(mainFolder.getAbsolutePath(), "config.yml");
        arenaYaml = new File(mainFolder.getAbsolutePath(), "arenas.yml");

        if (!configYaml.exists()) {
            InputStream inputStream = MClass.plugin.getResource(configYaml.getName());
            try {
                Files.copy(inputStream, configYaml.toPath());
            } catch (IOException ignored) {
            }

        }
        if (!arenaYaml.exists()) {
            InputStream inputStream = MClass.plugin.getResource(arenaYaml.getName());
            try {
                Files.copy(inputStream, arenaYaml.toPath());
            } catch (IOException ignored) {
            }

        }

    }

    public static void reloadConfig() {
        config = null;
        getConfig();
        arena = null;
        getArenas();
    }

    public static void saveConfig() {
        if (!(config == null) && !(configYaml == null)) {
            try {
                config.save(configYaml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!(arena == null) && !(arenaYaml == null)) {
            try {
                arena.save(arenaYaml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static FileConfiguration getConfig() {
        if (config != null) return config;

        config = new YamlConfiguration();
        try {
            config.load(configYaml);
        } catch (IOException | InvalidConfigurationException ignored) {
        }

        return config;
    }
    public static FileConfiguration getArenas() {
        if (arena != null) return arena;

        arena = new YamlConfiguration();
        try {
            arena.load(arenaYaml);
        } catch (IOException | InvalidConfigurationException ignored) {
        }

        return arena;
    }

}
