package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.screen.SculkGeneratorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class SculkGeneratorScreen extends AbstractGeneratorScreen<SculkGeneratorMenu> {

    private static final Identifier BURN_PROGRESS_SOUL = FabricSimpleGenerator.of("container/generator/lit_progress_soul");
    public SculkGeneratorScreen(SculkGeneratorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS_SOUL;
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
