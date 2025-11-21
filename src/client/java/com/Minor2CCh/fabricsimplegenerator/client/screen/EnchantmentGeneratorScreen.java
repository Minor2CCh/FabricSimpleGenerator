package com.Minor2CCh.fabricsimplegenerator.client.screen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.screen.EnchantmentGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnchantmentGeneratorScreen extends AbstractGeneratorScreen<EnchantmentGeneratorScreenHandler> {

    private static final Identifier BURN_PROGRESS_ENCHANTMENT = Identifier.of( FabricSimpleGenerator.MOD_ID,"container/generator/lit_progress_enchantment");
    public EnchantmentGeneratorScreen(EnchantmentGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Override
    protected Identifier getBurningTexture(){
        return BURN_PROGRESS_ENCHANTMENT;
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
