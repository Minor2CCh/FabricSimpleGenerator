package com.Minor2CCh.fabricsimplegenerator.config;

import java.util.LinkedHashMap;

public class FSGConfig {
    public enum constantTier {IRON, COPPER, GOLD, EMERALD, DIAMOND, NETHERITE, SCULK, BIOMASS}
    public LinkedHashMap<constantTier, Long> capacity = mapSet(FSGConfigLoader.defaultCapacity);
    public LinkedHashMap<constantTier, Long> extract = mapSet(FSGConfigLoader.defaultExtract);
    public LinkedHashMap<constantTier, Long> generate = mapSet(FSGConfigLoader.defaultGenerate);
    public Integer bioTime = FSGConfigLoader.defaultBioTime;
    public void fillDefaults() {
        if (capacity == null)
            capacity = mapSet(FSGConfigLoader.defaultCapacity);
        if (extract == null)
            extract = mapSet(FSGConfigLoader.defaultExtract);
        if(generate == null)
            generate = mapSet(FSGConfigLoader.defaultGenerate);
        if(bioTime == null)
            bioTime = FSGConfigLoader.defaultBioTime;
        for(constantTier tier : constantTier.values()){
            capacity.computeIfAbsent(tier, t -> FSGConfigLoader.defaultCapacity[t.ordinal()]);
            extract.computeIfAbsent(tier, t -> FSGConfigLoader.defaultExtract[t.ordinal()]);
            generate.computeIfAbsent(tier, t -> FSGConfigLoader.defaultGenerate[t.ordinal()]);
        }
        capacity = sortMap(capacity, FSGConfigLoader.defaultCapacity);
        extract = sortMap(extract, FSGConfigLoader.defaultExtract);
        generate = sortMap(generate, FSGConfigLoader.defaultGenerate);
    }
    public LinkedHashMap<constantTier, Long> mapSet(Long[] baseArray){
        LinkedHashMap<constantTier, Long> map = new LinkedHashMap<>();
        for(constantTier tier : constantTier.values()){
            map.put(tier, baseArray[tier.ordinal()]);
        }
        return map;
    }
    public LinkedHashMap<constantTier, Long> sortMap(LinkedHashMap<constantTier, Long> baseMap, Long[] baseArray){
        LinkedHashMap<constantTier, Long> newMap = new LinkedHashMap<>();
        for(constantTier fixTier : constantTier.values()){
            newMap.put(fixTier, baseMap.getOrDefault(fixTier, baseArray[fixTier.ordinal()]));
        }
        return newMap;

    }
}
