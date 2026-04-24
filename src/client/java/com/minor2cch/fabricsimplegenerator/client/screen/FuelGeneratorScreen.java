package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.screen.FuelGeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FuelGeneratorScreen extends AbstractGeneratorScreen<FuelGeneratorMenu> {

    private static final ResourceLocation BURN_PROGRESS = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
    public FuelGeneratorScreen(FuelGeneratorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    @Override
    protected ResourceLocation getBurningTexture(){
        return BURN_PROGRESS;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, delta, mouseX, mouseY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    protected void init() {
        super.init();
    }
}
