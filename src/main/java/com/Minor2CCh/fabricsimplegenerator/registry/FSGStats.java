package com.Minor2CCh.fabricsimplegenerator.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
@SuppressWarnings("all")
public class FSGStats {
    public static final Identifier INTERACT_WITH_FUEL_GENERATOR = register("interact_with_fuel_generator", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_SCULK_GENERATOR = register("interact_with_sculk_generator", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_BIOMASS_GENERATOR = register("interact_with_biomass_generator", StatFormatter.DEFAULT);

    public static void init(){
    }
    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.ofVanilla(id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }
}
