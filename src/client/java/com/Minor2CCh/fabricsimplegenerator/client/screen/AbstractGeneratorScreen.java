package com.Minor2CCh.fabricsimplegenerator.client.screen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.client.FabricSimpleGeneratorClient;
import com.Minor2CCh.fabricsimplegenerator.screen.AbstractGeneratorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public abstract class AbstractGeneratorScreen<T extends AbstractGeneratorScreenHandler> extends HandledScreen<T> {
    protected static final Identifier TEXTURE = Identifier.of(FabricSimpleGenerator.MOD_ID,"textures/gui/container/fuel_generator.png");
    protected static final Identifier ENERGY_TEXTURE = Identifier.of(FabricSimpleGenerator.MOD_ID,"container/generator/energy_gui");
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
            context.drawGuiTexture(getBurningTexture(), k, k, 0, k - l, x+56+25, y + 55+(k-l), k, l);
        }
        int energyX = x+25;
        int energyY = y+20;
        context.drawGuiTexture(ENERGY_TEXTURE, 128, 128, 0, 0, energyX, energyY, 14, 50);
        int p = MathHelper.ceil(this.handler.getEnergyPercentage() * 48.0F);
        context.drawGuiTexture(ENERGY_TEXTURE, 128, 128, 14, 49-p, energyX, energyY + (49-p), 14, p);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        int xGUI = mouseX - x;
        int yGUI = mouseY - y;
        if(xGUI >= 25 &&  xGUI <= 25+14 && yGUI >= 20 && yGUI <= 20+50){
            tooltipRenderer(context, mouseX, mouseY);

        }
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
    protected void tooltipRenderer(DrawContext context, int x, int y){
        java.util.List<Text> lines = new ArrayList<>();
        if(Screen.hasShiftDown()){
            lines.add(Text.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, getDefaultEnergyString(this.handler.amount()), getDefaultEnergyString(this.handler.getMaxCapacity())).formatted(Formatting.GOLD));
            lines.add(Text.translatable(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, getDefaultEnergyString(this.handler.getTickGenerate()), getDefaultEnergyString(this.handler.getMaxCapacity())).formatted(Formatting.GOLD));

        }else{
            lines.add(Text.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, getRoundingEnergyString(this.handler.amount()), getRoundingEnergyString(this.handler.getMaxCapacity())).formatted(Formatting.GOLD));
            lines.add(Text.translatable(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, getDefaultEnergyString(this.handler.getTickGenerate()), getDefaultEnergyString(this.handler.getMaxCapacity())).formatted(Formatting.GOLD));

        }
        context.drawTooltip(textRenderer, lines, x, y);
    }
    private static String getRoundingEnergyString(long amount){
        return (FabricSimpleGeneratorClient.adjustEnergyUnit(amount));
    }
    private static String getDefaultEnergyString(long amount){
        return (String.format("%,d", amount)+"E");
    }

}
