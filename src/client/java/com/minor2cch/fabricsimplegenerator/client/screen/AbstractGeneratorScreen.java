package com.minor2cch.fabricsimplegenerator.client.screen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.client.FabricSimpleGeneratorClient;
import com.minor2cch.fabricsimplegenerator.screen.AbstractGeneratorMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGeneratorScreen<T extends AbstractGeneratorMenu> extends AbstractContainerScreen<T> {
    protected static final ResourceLocation TEXTURE = FabricSimpleGenerator.of("textures/gui/container/fuel_generator.png");
    protected static final ResourceLocation ENERGY_TEXTURE = FabricSimpleGenerator.of("container/generator/energy_gui");
    public AbstractGeneratorScreen(T handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }
    protected abstract ResourceLocation getBurningTexture();

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if (this.menu.isBurning()) {
            int k = 14;
            int l = Mth.ceil(this.menu.getFuelProgress() * 13.0F) + 1;
            guiGraphics.blitSprite(getBurningTexture(), k, k, 0, k - l, leftPos+56+25, topPos + 55+(k-l), k, l);
        }
        int energyX = leftPos+25;
        int energyY = topPos+20;
        guiGraphics.blitSprite(ENERGY_TEXTURE, 128, 128, 0, 0, energyX, energyY, 14, 50);
        int p = Mth.ceil(this.menu.getEnergyPercentage() * 48.0F);
        guiGraphics.blitSprite(ENERGY_TEXTURE, 128, 128, 14, 49-p, energyX, energyY + (49-p), 14, p);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
        int xGUI = mouseX - leftPos;
        int yGUI = mouseY - topPos;
        if(xGUI >= 25 &&  xGUI <= 25+14 && yGUI >= 20 && yGUI <= 20+50){
            customTooltipRenderer(guiGraphics, mouseX, mouseY);

        }
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }
    protected void customTooltipRenderer(GuiGraphics guiGraphics, int x, int y){
        List<Component> lines = new ArrayList<>();
        if(Screen.hasShiftDown()){
            lines.add(Component.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, getDefaultEnergyString(this.menu.amount()), getDefaultEnergyString(this.menu.getMaxCapacity())).withStyle(ChatFormatting.GOLD));
            lines.add(Component.translatable(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, getDefaultEnergyString(this.menu.getTickGenerate())+"/t", getDefaultEnergyString(this.menu.getMaxCapacity())).withStyle(ChatFormatting.GOLD));

        }else{
            lines.add(Component.translatable(FabricSimpleGeneratorClient.AMOUNT_FORMAT, getRoundingEnergyString(this.menu.amount()), getRoundingEnergyString(this.menu.getMaxCapacity())).withStyle(ChatFormatting.GOLD));
            lines.add(Component.translatable(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, getDefaultEnergyString(this.menu.getTickGenerate())+"/t", getDefaultEnergyString(this.menu.getMaxCapacity())).withStyle(ChatFormatting.GOLD));

        }
        guiGraphics.renderComponentTooltip(font, lines, x, y);
    }
    private static String getRoundingEnergyString(long amount){
        return (FabricSimpleGeneratorClient.adjustEnergyUnit(amount));
    }
    private static String getDefaultEnergyString(long amount){
        return (String.format("%,d", amount)+"E");
    }

}
