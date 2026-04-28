package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("all")
public final class FSGCreativeModeTabs {
    private FSGCreativeModeTabs(){}
    public static final ResourceKey<CreativeModeTab> FSG_ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), FabricSimpleGenerator.of("fsg_item_group"));
    public static final CreativeModeTab FSG_ITEM_GROUP = register("fsg_item_group", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(FSGItems.IRON_GENERATOR))
            .title(Component.translatable(String.format("itemGroup.%s.%s", FabricSimpleGenerator.MOD_ID, "fsg_item_group")))
            .displayItems((parameters, output) -> {
                output.accept(FSGItems.IRON_GENERATOR);
                output.accept(FSGItems.COPPER_GENERATOR);
                output.accept(FSGItems.GOLD_GENERATOR);
                output.accept(FSGItems.DIAMOND_GENERATOR);
                output.accept(FSGItems.EMERALD_GENERATOR);
                output.accept(FSGItems.NETHERITE_GENERATOR);
                output.accept(FSGItems.SCULK_GENERATOR);
                output.accept(FSGItems.BIOMASS_GENERATOR);
                output.accept(FSGItems.ENCHANTMENT_GENERATOR);
                output.accept(FSGItems.IRON_GENERATOR_8X);
                output.accept(FSGItems.COPPER_GENERATOR_8X);
                output.accept(FSGItems.GOLD_GENERATOR_8X);
                output.accept(FSGItems.EMERALD_GENERATOR_8X);
                output.accept(FSGItems.DIAMOND_GENERATOR_8X);
                output.accept(FSGItems.NETHERITE_GENERATOR_8X);
                output.accept(FSGItems.SCULK_GENERATOR_8X);
                output.accept(FSGItems.BIOMASS_GENERATOR_8X);
                output.accept(FSGItems.ENCHANTMENT_GENERATOR_8X);
                output.accept(FSGItems.NETHERITE_GENERATOR_64X);
                output.accept(FSGItems.SCULK_GENERATOR_64X);
                output.accept(FSGItems.BIOMASS_GENERATOR_64X);
                output.accept(FSGItems.ENCHANTMENT_GENERATOR_64X);
            })
            .build());
    private static CreativeModeTab register(String name, CreativeModeTab tab){
        ResourceKey<CreativeModeTab> key = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), FabricSimpleGenerator.of(name));
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
    }

    public static void init(){

    }
}
