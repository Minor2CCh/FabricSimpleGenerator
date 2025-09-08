package com.Minor2CCh.fabricsimplegenerator.client.screen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.screen.FuelGeneratorScreenHandler;
import com.Minor2CCh.fabricsimplegenerator.screen.SculkGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SculkGeneratorScreen extends AbstractGeneratorScreen<SculkGeneratorScreenHandler> {

    private static final Identifier BURN_PROGRESS_SOUL = Identifier.of( FabricSimpleGenerator.MOD_ID,"container/generator/lit_progress_soul");
    public SculkGeneratorScreen(SculkGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS_SOUL;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void init() {
        super.init();
    }
}
