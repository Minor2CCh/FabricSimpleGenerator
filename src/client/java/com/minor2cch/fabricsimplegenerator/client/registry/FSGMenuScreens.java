package com.minor2cch.fabricsimplegenerator.client.registry;

import com.minor2cch.fabricsimplegenerator.client.screen.*;
import com.minor2cch.fabricsimplegenerator.registry.FSGMenus;
import net.minecraft.client.gui.screens.MenuScreens;

public final class FSGMenuScreens {
    private FSGMenuScreens(){}
    public static void init(){
        MenuScreens.register(FSGMenus.FUEL_GENERATOR_SCREEN_HANDLER, FuelGeneratorScreen::new);
        MenuScreens.register(FSGMenus.SCULK_GENERATOR_SCREEN_HANDLER, SculkGeneratorScreen::new);
        MenuScreens.register(FSGMenus.BIOMASS_GENERATOR_SCREEN_HANDLER, BiomassGeneratorScreen::new);
        MenuScreens.register(FSGMenus.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, EnchantmentGeneratorScreen::new);
    }
}
