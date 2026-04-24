package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGItemTags;
import com.minor2cch.fabricsimplegenerator.registry.FSGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class FSGItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public FSGItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }
    private static final String DEEPER_DARKER_MOD_ID = "deeperdarker";
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_BIT)
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_tendrils"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_vines"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_stone"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "cobbled_sculk_stone"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_LOW)
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "soul_dust"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_gleam"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_bone"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "gloomy_sculk"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_MIDDLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_jaw"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "gloomy_geyser"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_SPECIAL);
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_HIGH)
                .addOptional(ResourceLocation.fromNamespaceAndPath("apothic_ehchanting", "warden_tendril"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "warden_carapace"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "heart_of_the_deep"));
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_EXTRA)
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "soul_crystal"));
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_ULTIMATE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "shattered_head"));
        getOrCreateTagBuilder(FSGItemTags.AZALEAS)
                .add(Items.AZALEA)
                .add(Items.FLOWERING_AZALEA);
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(FSGItems.GOLD_GENERATOR)
                .add(FSGItems.GOLD_GENERATOR_8X);
    }
}
