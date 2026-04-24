package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.screen.SculkGeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SculkGeneratorScreen extends AbstractGeneratorScreen<SculkGeneratorMenu> {

    private static final ResourceLocation BURN_PROGRESS_SOUL = FabricSimpleGenerator.of("container/generator/lit_progress_soul");
    public SculkGeneratorScreen(SculkGeneratorMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    @Override
    protected ResourceLocation getBurningTexture(){
        return BURN_PROGRESS_SOUL;
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
