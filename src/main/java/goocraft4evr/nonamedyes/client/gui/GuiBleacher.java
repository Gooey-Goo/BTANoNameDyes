package goocraft4evr.nonamedyes.client.gui;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import goocraft4evr.nonamedyes.player.inventory.ContainerBleacher;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.block.entity.TileEntityBlastFurnace;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.crafting.LookupFuelFurnaceBlast;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.*;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import net.minecraft.core.player.inventory.slot.SlotCreative;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

public class GuiBleacher extends GuiContainer {
    private final TileEntityBleacher bleacher;

    public GuiBleacher(InventoryPlayer inventoryplayer, TileEntityBleacher tileentitybleacher) {
        super(new ContainerBleacher(inventoryplayer, tileentitybleacher));
        ySize = 148;
        bleacher = tileentitybleacher;
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString("Bleaching Basin", 40, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, ySize - 112 + 18, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int guiTexture = mc.renderEngine.getTexture(String.format("/assets/%s/textures/gui/bleaching.png", NoNameDyes.MOD_ID));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(guiTexture);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
        if (bleacher.currentWaterTime > 0) {
			int len = bleacher.getWaterProgressScaled(16);
            drawTexturedModalRect(j + 18, k + 32 - len, 176, 16-len, 3, len);
            drawTexturedModalRect(j + 33, k + 32 - len, 179, 16-len, 3, len);
        }
        if (bleacher.currentFuelTime > 0) {
            int len = bleacher.getFuelRemainingScaled(16);
            drawTexturedModalRect(j + 21, k + 32 - len, 176, 32 - len, 12, len + 2);
        }
        if (bleacher.isFuelled()) {
            int len = bleacher.getBleachProgressScaled(24);
            drawTexturedModalRect(j + 91, k + 25, 176, 32, len + 1, 16);
        }
    }
}
