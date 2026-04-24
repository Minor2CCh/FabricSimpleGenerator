package com.minor2cch.fabricsimplegenerator.config;

import java.util.LinkedHashMap;

public class FSGConfig {
    public enum constantTier {IRON, COPPER, GOLD, EMERALD, DIAMOND, NETHERITE, SCULK, BIOMASS, ENCHANTMENT}
    private LinkedHashMap<constantTier, Long> capacity;
    private LinkedHashMap<constantTier, Long> extract;
    private LinkedHashMap<constantTier, Long> generate;
    private Integer bioTime;
    private Boolean canCurseRemoval;
    private static final Long[] DEFAULT_CAPACITY = {100000L, 80000L, 200000L, 400000L, 800000L, 1600000L, 400000L, 200000L, 1200000L};
    private static final Long[] DEFAULT_EXTRACT = {50L, 25L, 100L, 200L, 400L, 800L, 80L, 50L, 100L};
    private static final Long[] DEFAULT_GENERATE = {10L, 5L, 20L, 40L, 80L, 160L, 20L, 10L, 20L};
    private static final int DEFAULT_BIO_TIME = 1000;
    private static final boolean DEFAULT_CAN_CURSE_REMOVAL = true;
    private FSGConfig() {

    }
    public static FSGConfig defaultFSGConfig() {
        FSGConfig config = new FSGConfig();
        config.capacity = mapSet(DEFAULT_CAPACITY);
        config.extract = mapSet(DEFAULT_EXTRACT);
        config.generate = mapSet(DEFAULT_GENERATE);
        config.bioTime = DEFAULT_BIO_TIME;
        config.canCurseRemoval = DEFAULT_CAN_CURSE_REMOVAL;
        return config;
    }
    public void fillDefaults() {
        if (capacity == null){
            capacity = mapSet(DEFAULT_CAPACITY);
        }
        if (extract == null){
            extract = mapSet(DEFAULT_EXTRACT);
        }
        if(generate == null){
            generate = mapSet(DEFAULT_GENERATE);
        }
        if(bioTime == null){
            bioTime = DEFAULT_BIO_TIME;
        }
        if(canCurseRemoval == null){
            canCurseRemoval = DEFAULT_CAN_CURSE_REMOVAL;
        }
        for(constantTier tier : constantTier.values()){
            capacity.computeIfAbsent(tier, t -> DEFAULT_CAPACITY[t.ordinal()]);
            extract.computeIfAbsent(tier, t -> DEFAULT_EXTRACT[t.ordinal()]);
            generate.computeIfAbsent(tier, t -> DEFAULT_GENERATE[t.ordinal()]);
        }
        capacity = sortMap(capacity, DEFAULT_CAPACITY);
        extract = sortMap(extract, DEFAULT_EXTRACT);
        generate = sortMap(generate, DEFAULT_GENERATE);
    }
    public static LinkedHashMap<constantTier, Long> mapSet(Long[] baseArray){
        LinkedHashMap<constantTier, Long> map = new LinkedHashMap<>();
        for(constantTier tier : constantTier.values()){
            map.put(tier, baseArray[tier.ordinal()]);
        }
        return map;
    }
    public static LinkedHashMap<constantTier, Long> sortMap(LinkedHashMap<constantTier, Long> baseMap, Long[] baseArray){
        LinkedHashMap<constantTier, Long> newMap = new LinkedHashMap<>();
        for(constantTier fixTier : constantTier.values()){
            newMap.put(fixTier, baseMap.getOrDefault(fixTier, baseArray[fixTier.ordinal()]));
        }
        return newMap;
    }
    public long getConfigCapacity(FSGConfig.constantTier tier){
        return capacity.getOrDefault(tier, FSGConfig.DEFAULT_CAPACITY[tier.ordinal()]);
    }
    public long getConfigExtract(FSGConfig.constantTier tier){
        return extract.getOrDefault(tier, FSGConfig.DEFAULT_EXTRACT[tier.ordinal()]);
    }
    public long getConfigGenerate(FSGConfig.constantTier tier){
        return generate.getOrDefault(tier, FSGConfig.DEFAULT_GENERATE[tier.ordinal()]);
    }
    public long getBioTime(){
        return bioTime;
    }
    public boolean getCanCurseRemoval(){
        return canCurseRemoval;
    }
}
