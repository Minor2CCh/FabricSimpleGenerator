package com.minor2cch.fabricsimplegenerator.config;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FSGConfigLoader {
    private FSGConfigLoader(){}
    private static final File DIR = FabricLoader.getInstance().getConfigDir().toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = FabricSimpleGenerator.MOD_ID +".json";
    private static final Path CONFIG_PATH = Path.of(new File(DIR,FILENAME).getPath());
    private static FSGConfig modConfig;
    public static void load(){
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                modConfig = GSON.fromJson(reader, FSGConfig.class);
            } catch (Exception e) {
                FabricSimpleGenerator.LOGGER.error("Can not load config file: {}", e.getMessage());
                modConfig = FSGConfig.defaultFSGConfig();
            }

            try{
                modConfig.fillDefaults();
            } catch (Exception e) {
                FabricSimpleGenerator.LOGGER.error("Failed to write to the config: {}", e.getMessage());
                modConfig = FSGConfig.defaultFSGConfig();
            }
             // ここで新フィールド補完

        } else {
            modConfig = FSGConfig.defaultFSGConfig();
        }
        save(); // 初回生成

    }
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(modConfig, writer);
            }
        } catch (IOException e) {
            FabricSimpleGenerator.LOGGER.error("Failed to save config: {}", e.getMessage());
        }
    }
    public static FSGConfig getConfig() {
        return modConfig;
    }
}
