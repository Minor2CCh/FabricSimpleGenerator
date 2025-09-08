package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
@SuppressWarnings("all")
public class FSGItemGroups {
    public static final RegistryKey<ItemGroup> FSG_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FabricSimpleGenerator.MOD_ID, "fsg_item_group"));
    public static final ItemGroup FSG_ITEM_GROUP = register("fsg_item_group", FabricItemGroup.builder()
            .icon(() -> new ItemStack(FSGItems.IRON_GENERATOR))
            .displayName(Text.translatable(String.format("itemGroup.%s.%s", FabricSimpleGenerator.MOD_ID, "fsg_item_group")))
            .entries((parameters, output) -> {
                output.add(FSGItems.IRON_GENERATOR);
                output.add(FSGItems.COPPER_GENERATOR);
                output.add(FSGItems.GOLD_GENERATOR);
                output.add(FSGItems.DIAMOND_GENERATOR);
                output.add(FSGItems.EMERALD_GENERATOR);
                output.add(FSGItems.NETHERITE_GENERATOR);
                output.add(FSGItems.SCULK_GENERATOR);
                output.add(FSGItems.BIOMASS_GENERATOR);
                output.add(FSGItems.IRON_GENERATOR_8X);
                output.add(FSGItems.COPPER_GENERATOR_8X);
                output.add(FSGItems.GOLD_GENERATOR_8X);
                output.add(FSGItems.EMERALD_GENERATOR_8X);
                output.add(FSGItems.DIAMOND_GENERATOR_8X);
                output.add(FSGItems.NETHERITE_GENERATOR_8X);
                output.add(FSGItems.SCULK_GENERATOR_8X);
                output.add(FSGItems.BIOMASS_GENERATOR_8X);
                output.add(FSGItems.NETHERITE_GENERATOR_64X);
                output.add(FSGItems.SCULK_GENERATOR_64X);
                output.add(FSGItems.BIOMASS_GENERATOR_64X);
            })
            .build());
    private static ItemGroup register(String name, ItemGroup itemGroup){
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FabricSimpleGenerator.MOD_ID, name));
        return Registry.register(Registries.ITEM_GROUP, key, itemGroup);
    }

    public static void init(){

    }
}
