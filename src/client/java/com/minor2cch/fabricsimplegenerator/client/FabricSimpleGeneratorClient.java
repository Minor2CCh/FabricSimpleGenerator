package com.minor2cch.fabricsimplegenerator.client;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.block.*;
import com.minor2cch.fabricsimplegenerator.client.registry.FSGMenuScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class FabricSimpleGeneratorClient implements ClientModInitializer {
    public static final String TOOLTIP_HIDE = "item."+ FabricSimpleGenerator.MOD_ID +".hide_tooltip.desc";
    public static final String MAX_CAPACITY_FORMAT = String.format("item.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "capacity");
    public static final String MAX_EXTRACT_FORMAT = String.format("item.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "extract");
    public static final String TICK_GENERATE_FORMAT = String.format("item.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "tick_generate");
    public static final String TICK_GENERATE_FORMAT_ENCHANTMENT_GENERATOR = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "tick_generate", "enchanting");
    public static final String INGREDIENT_FORMAT = String.format("item.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient");
    public static final String INGREDIENT_FUEL_FORMAT = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient", "fuel");
    public static final String INGREDIENT_SCULK_FORMAT = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient", "sculk");
    public static final String INGREDIENT_BIO_FORMAT = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient", "bio");
    public static final String INGREDIENT_ENCHANTMENT_FORMAT = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient", "enchanting");
    public static final String INGREDIENT_UNDEFINED_FORMAT = String.format("item.%s.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "ingredient", "undefined");
    public static final String AMOUNT_FORMAT = String.format("item.%s.%s.%s", FabricSimpleGenerator.MOD_ID, "generator", "amount");
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((ItemStack stack, Item.TooltipContext context, TooltipFlag flag, List<Component> lines) -> {
            boolean hasShiftDown = Screen.hasShiftDown();
            if (stack.getItem() instanceof BlockItem blockItem) {
                if(blockItem.getBlock() instanceof AbstractGeneratorBlock generatorBlock){
                     if (hasShiftDown) {
                        lines.add(Component.translatable(MAX_CAPACITY_FORMAT, adjustEnergyUnit(generatorBlock.getCapacity())).withStyle(ChatFormatting.GOLD));
                         lines.add(Component.translatable(MAX_EXTRACT_FORMAT, adjustEnergyUnit(generatorBlock.getExtract())).withStyle(ChatFormatting.GOLD));
                         if(generatorBlock instanceof EnchantmentGeneratorBlock){
                             lines.add(Component.translatable(TICK_GENERATE_FORMAT, Component.translatable(TICK_GENERATE_FORMAT_ENCHANTMENT_GENERATOR)).withStyle(ChatFormatting.GOLD));
                         }else{
                             lines.add(Component.translatable(TICK_GENERATE_FORMAT, (adjustEnergyUnit(generatorBlock.getTickGenerate())+"/t")).withStyle(ChatFormatting.GOLD));
                         }
                         lines.add(Component.translatable(INGREDIENT_FORMAT, Component.translatable(getIngredientText(generatorBlock))).withStyle(ChatFormatting.GOLD));
                    } else {
                        lines.add(Component.translatable(TOOLTIP_HIDE));
                    }
                }
            }
        });
        FSGMenuScreens.init();
    }
    public static String adjustEnergyUnit(long capacity){
        String[] capUnit = new String[]{"E", "kE", "ME", "GE", "TE", "PE"};
        long[] capAdjust = new long[capUnit.length+1];//{(long)Math.pow(10, 0), (long)Math.pow(10, 3), (long)Math.pow(10, 6), (long)Math.pow(10, 9), (long)Math.pow(10, 12), (long)Math.pow(10, 15)};
        for(int i=0;i < capAdjust.length;i++){
            capAdjust[i] = (long)Math.pow(10, i*3);
        }
        for(int i=0;i < capUnit.length;i++){
            if(capacity < capAdjust[i+1]){
                if(i==0){
                    return capacity / capAdjust[i] +capUnit[i];
                }
                else{
                    return (capacity / (capAdjust[i] / 10) / 10.0) +capUnit[i];
                }
            }
        }
        return capacity+"E";
    }
    private static String getIngredientText(Block block){
        if(block instanceof FuelGeneratorBlock)
            return INGREDIENT_FUEL_FORMAT;
        if(block instanceof SculkGeneratorBlock)
            return INGREDIENT_SCULK_FORMAT;
        if(block instanceof BiomassGeneratorBlock)
            return INGREDIENT_BIO_FORMAT;
        if(block instanceof EnchantmentGeneratorBlock)
            return INGREDIENT_ENCHANTMENT_FORMAT;
        return INGREDIENT_UNDEFINED_FORMAT;
    }
}
