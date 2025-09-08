package com.Minor2CCh.fabricsimplegenerator.client.screen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.client.FabricSimpleGeneratorClient;
import com.Minor2CCh.fabricsimplegenerator.screen.AbstractGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public abstract class AbstractGeneratorScreen<T extends AbstractGeneratorScreenHandler> extends HandledScreen<T> {
    protected static final Identifier TEXTURE = Identifier.of(FabricSimpleGenerator.MOD_ID,"textures/gui/container/fuel_generator.png");
    public AbstractGeneratorScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    protected abstract Identifier getBurningTexture();

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        if (this.handler.isBurning()) {
            int k = 14;
            int l = MathHelper.ceil(this.handler.getFuelProgress() * 13.0F) + 1;
            context.drawGuiTexture(getBurningTexture(), 14, 14, 0, 14 - l, x+56+25, y + 55+(14-l), 14, l);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        boolean hasShiftDown = Screen.hasShiftDown();
        if(hasShiftDown){
            Text text1 = Text.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, ((String.format("%,d", this.handler.amount())+"E")).formatted(Formatting.BLACK));
            context.drawText(this.textRenderer, text1, this.x+5, this.y+20, Colors.BLACK, false);
        }
        else{
            Text text = Text.translatable(FabricSimpleGeneratorClient.MAX_CAPACITY_FORMAT, (FabricSimpleGeneratorClient.adjustEnergyUnit(this.handler.getMaxCapacity()))).formatted(Formatting.BLACK);
            context.drawText(this.textRenderer, text, this.x+80, this.y+20, Colors.BLACK, false);
            Text text1 = Text.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, (FabricSimpleGeneratorClient.adjustEnergyUnit(this.handler.amount()))).formatted(Formatting.BLACK);
            context.drawText(this.textRenderer, text1, this.x+5, this.y+20, Colors.BLACK, false);
        }
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
