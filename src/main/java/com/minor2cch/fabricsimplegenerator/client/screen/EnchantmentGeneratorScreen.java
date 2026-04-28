package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.screen.EnchantmentGeneratorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class EnchantmentGeneratorScreen extends AbstractGeneratorScreen<EnchantmentGeneratorMenu> {

    private static final Identifier BURN_PROGRESS_ENCHANTMENT = FabricSimpleGenerator.of("container/generator/lit_progress_enchantment");
    public EnchantmentGeneratorScreen(EnchantmentGeneratorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS_ENCHANTMENT;
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
    }

    @Override
    public void extractContents(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractContents(graphics, mouseX, mouseY, a);
    }

    @Override
    protected void init() {
        super.init();
    }
}
