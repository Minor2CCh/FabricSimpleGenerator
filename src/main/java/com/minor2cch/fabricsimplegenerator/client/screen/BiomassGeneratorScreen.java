package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.screen.BiomassGeneratorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class BiomassGeneratorScreen extends AbstractGeneratorScreen<BiomassGeneratorMenu> {

    private static final Identifier BURN_PROGRESS = Identifier.withDefaultNamespace("container/furnace/lit_progress");
    public BiomassGeneratorScreen(BiomassGeneratorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS;
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
