package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.screen.BiomassGeneratorMenu;
import com.minor2cch.fabricsimplegenerator.screen.EnchantmentGeneratorMenu;
import com.minor2cch.fabricsimplegenerator.screen.FuelGeneratorMenu;
import com.minor2cch.fabricsimplegenerator.screen.SculkGeneratorMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class FSGMenus {
    private FSGMenus() {}
    public static final MenuType<FuelGeneratorMenu> FUEL_GENERATOR_SCREEN_HANDLER = register("fuel_generator_screen_handler", new MenuType<>(FuelGeneratorMenu::new, FeatureFlagSet.of()));
    public static final MenuType<SculkGeneratorMenu> SCULK_GENERATOR_SCREEN_HANDLER = register("sculk_generator_screen_handler", new MenuType<>(SculkGeneratorMenu::new, FeatureFlagSet.of()));
    public static final MenuType<BiomassGeneratorMenu> BIOMASS_GENERATOR_SCREEN_HANDLER = register("biomass_generator_screen_handler", new MenuType<>(BiomassGeneratorMenu::new, FeatureFlagSet.of()));
    public static final MenuType<EnchantmentGeneratorMenu> ENCHANTMENT_GENERATOR_SCREEN_HANDLER = register("enchantment_generator_screen_handler", new MenuType<>(EnchantmentGeneratorMenu::new, FeatureFlagSet.of()));
    public static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType<T> handler){
        ResourceLocation id = FabricSimpleGenerator.of(name);
        return Registry.register(BuiltInRegistries.MENU, id, handler);
    }
    public static void init() {
    }
}
