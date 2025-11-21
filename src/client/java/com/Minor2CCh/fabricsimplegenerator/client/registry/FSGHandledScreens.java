package com.Minor2CCh.fabricsimplegenerator.client.registry;

import com.Minor2CCh.fabricsimplegenerator.client.screen.*;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FSGHandledScreens {
    public static void init(){
        HandledScreens.register(FSGScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER, FuelGeneratorScreen::new);
        HandledScreens.register(FSGScreenHandlers.SCULK_GENERATOR_SCREEN_HANDLER, SculkGeneratorScreen::new);
        HandledScreens.register(FSGScreenHandlers.BIOMASS_GENERATOR_SCREEN_HANDLER, BiomassGeneratorScreen::new);
        HandledScreens.register(FSGScreenHandlers.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, EnchantmentGeneratorScreen::new);
    }
}
