package com.Minor2CCh.fabricsimplegenerator.client.screen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.screen.BiomassGeneratorScreenHandler;
import com.Minor2CCh.fabricsimplegenerator.screen.SculkGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BiomassGeneratorScreen extends AbstractGeneratorScreen<BiomassGeneratorScreenHandler> {

    private static final Identifier BURN_PROGRESS = Identifier.ofVanilla( "container/furnace/lit_progress");
    public BiomassGeneratorScreen(BiomassGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS;
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
