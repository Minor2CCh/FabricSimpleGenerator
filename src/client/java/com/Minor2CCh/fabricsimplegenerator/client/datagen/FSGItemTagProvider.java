package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import com.Minor2CCh.fabricsimplegenerator.registry.FSGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class FSGItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public FSGItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    private static final String DEEPER_DARKER_MOD_ID = "deeperdarker";
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_BIT)
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_tendrils"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_vines"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_stone"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "cobbled_sculk_stone"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_LOW)
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "soul_dust"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_gleam"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_bone"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "gloomy_sculk"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_MIDDLE)
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "sculk_jaw"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "gloomy_geyser"))
        ;
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_SPECIAL);
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_HIGH)
                .addOptional(Identifier.of("apothic_ehchanting", "warden_tendril"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "warden_carapace"))
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "heart_of_the_deep"));
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_EXTRA)
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "soul_crystal"));
        getOrCreateTagBuilder(FSGItemTags.SCULK_INGREDIENT_ULTIMATE)
                .addOptional(Identifier.of(DEEPER_DARKER_MOD_ID, "shattered_head"));
        getOrCreateTagBuilder(FSGItemTags.AZALEAS)
                .add(Items.AZALEA)
                .add(Items.FLOWERING_AZALEA);
    }
}
