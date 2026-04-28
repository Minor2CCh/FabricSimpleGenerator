package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

@SuppressWarnings("all")
public final class FSGStats {
    private FSGStats() {}
    public static final Identifier INTERACT_WITH_FUEL_GENERATOR = register("interact_with_fuel_generator", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_SCULK_GENERATOR = register("interact_with_sculk_generator", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_BIOMASS_GENERATOR = register("interact_with_biomass_generator", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_ENCHANTMENT_GENERATOR = register("interact_with_ehcnantment_generator", StatFormatter.DEFAULT);

    public static void init(){
    }
    private static Identifier register(String id, StatFormatter formatter) {
        Identifier rl = FabricSimpleGenerator.of(id);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, rl);
        Stats.CUSTOM.get(rl, formatter);
        return rl;
    }
}
