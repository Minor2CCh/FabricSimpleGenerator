package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.screen.BiomassGeneratorScreenHandler;
import com.Minor2CCh.fabricsimplegenerator.screen.FuelGeneratorScreenHandler;
import com.Minor2CCh.fabricsimplegenerator.screen.SculkGeneratorScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class FSGScreenHandlers {
    public static ScreenHandlerType<FuelGeneratorScreenHandler> FUEL_GENERATOR_SCREEN_HANDLER = register("fuel_generator_screen_handler", new ScreenHandlerType<>(FuelGeneratorScreenHandler::new, FeatureSet.empty()));
    public static ScreenHandlerType<SculkGeneratorScreenHandler> SCULK_GENERATOR_SCREEN_HANDLER = register("sculk_generator_screen_handler", new ScreenHandlerType<>(SculkGeneratorScreenHandler::new, FeatureSet.empty()));
    public static ScreenHandlerType<BiomassGeneratorScreenHandler> BIOMASS_GENERATOR_SCREEN_HANDLER = register("biomass_generator_screen_handler", new ScreenHandlerType<>(BiomassGeneratorScreenHandler::new, FeatureSet.empty()));
    public static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType<T> handler){
        Identifier id = Identifier.of(FabricSimpleGenerator.MOD_ID, name);
        return Registry.register(Registries.SCREEN_HANDLER, id, handler);
    }
    public static void init() {
    }
}
