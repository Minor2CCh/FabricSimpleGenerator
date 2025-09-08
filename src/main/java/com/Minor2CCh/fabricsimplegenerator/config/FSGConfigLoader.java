package com.Minor2CCh.fabricsimplegenerator.config;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FSGConfigLoader {
    private static final File DIR = FabricLoader.getInstance().getConfigDir().toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = FabricSimpleGenerator.MOD_ID +".json";
    private static final Path CONFIG_PATH = Path.of(new File(DIR,FILENAME).getPath());
    public static final Long[] defaultCapacity = {100000L, 80000L, 200000L, 400000L, 800000L, 1600000L, 400000L, 200000L};
    public static final Long[] defaultExtract = {50L, 25L, 100L, 200L, 400L, 800L, 80L, 50L};
    public static final Long[] defaultGenerate = {10L, 5L, 20L, 40L, 80L, 160L, 20L, 10L};
    public static final int defaultBioTime = 1000;
    private static FSGConfig modConfig;
    public static void load(){
        System.out.println(CONFIG_PATH);
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                modConfig = GSON.fromJson(reader, FSGConfig.class);
            } catch (Exception e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new FSGConfig();
            }

            try{
                modConfig.fillDefaults();
            } catch (Exception e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new FSGConfig();
            }
             // ここで新フィールド補完

        } else {
            modConfig = new FSGConfig();
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
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
    public static FSGConfig getConfig() {
        return modConfig;
    }
    public static long getConfigCapacity(FSGConfig.constantTier tier){
        return FSGConfigLoader.getConfig().capacity.getOrDefault(tier, FSGConfigLoader.defaultCapacity[tier.ordinal()]);
    }
    public static long getConfigExtract(FSGConfig.constantTier tier){
        return FSGConfigLoader.getConfig().extract.getOrDefault(tier, FSGConfigLoader.defaultCapacity[tier.ordinal()]);
    }
    public static long getConfigGenerate(FSGConfig.constantTier tier){
        return FSGConfigLoader.getConfig().generate.getOrDefault(tier, FSGConfigLoader.defaultGenerate[tier.ordinal()]);
    }
}
