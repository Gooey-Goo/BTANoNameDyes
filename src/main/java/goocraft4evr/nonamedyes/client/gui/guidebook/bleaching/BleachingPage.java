package goocraft4evr.nonamedyes.client.gui.guidebook.bleaching;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.crafting.RecipeEntryBleacher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiRenderItem;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.client.gui.guidebook.*;
import net.minecraft.client.gui.guidebook.search.SearchPage;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotGuidebook;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BleachingPage extends RecipePage<RecipeEntryBleacher> {
	public static final int RECIPES_PER_PAGE = 6;
	public List<SlotGuidebook> slots;
	public Map<RecipeEntryBleacher, List<SlotGuidebook>> map;
	private final GuiTooltip guiTooltip;
	private final GuiRenderItem guiRenderItem;
	private static final Minecraft mc = Minecraft.getMinecraft(GuidebookPage.class);
	private static EntityPlayer player;
	private static long ticks;

	public BleachingPage(GuidebookSection section, ArrayList<RecipeEntryBleacher> recipes) {
		super(section);
		this.recipes = recipes;
		this.slots = new ArrayList<>();
		this.map = new HashMap<>();
		player = BleachingPage.mc.thePlayer;
		player.addStat(AchievementList.OPEN_GUIDEBOOK, 1);
		this.guiTooltip = new GuiTooltip(mc);
		this.guiRenderItem = new GuiRenderItem(mc);
		for (RecipeEntryBleacher recipe : recipes) {
			ArrayList<SlotGuidebook> recipeSlots = new ArrayList<>();
			recipeSlots.add(new SlotGuidebook(0, 47, 32 * (this.map.size() + 1) - 16, recipe.getInput(), false, recipe));
			recipeSlots.add(new SlotGuidebook(1, 103, 32 * (this.map.size() + 1) - 16, new RecipeSymbol(recipe.getOutput()), false, recipe));
			this.map.put(recipe, recipeSlots);
			this.slots.addAll(recipeSlots);
		}
	}

	@Override
	protected void renderForeground(RenderEngine re, FontRenderer fr, int x, int y, int mouseX, int mouseY, float partialTicks) {
		if (this.recipes.isEmpty()) {
			this.drawStringCenteredNoShadow(fr, "No recipes found :(", x + 79, y + 110, -8355712);
		}
		SlotGuidebook mouseOverSlot = null;
		++ticks;
		for (SlotGuidebook slot : this.slots) {
			if (ticks > 150L) {
				slot.showRandomItem();
				if (this.slots.get(this.slots.size() - 1) == slot) {
					ticks = 0L;
				}
			}
			this.drawSlot(re, x + slot.xDisplayPosition - 1, y + slot.yDisplayPosition - 1, -1);
			if (this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) {
				mouseOverSlot = slot;
			}
			this.guiRenderItem.render(slot.getStack(), x + slot.xDisplayPosition, y + slot.yDisplayPosition, mouseOverSlot == slot, slot);
		}
	}

	public boolean getIsMouseOverSlot(Slot slot, int x, int y, int mouseX, int mouseY) {
		return mouseX >= x + slot.xDisplayPosition - 1 && mouseX < x + slot.xDisplayPosition + 16 + 1 && mouseY >= y + slot.yDisplayPosition - 1 && mouseY < y + slot.yDisplayPosition + 16 + 1;
	}

	@Override
	public void keyTyped(char c, int key, int x, int y, int mouseX, int mouseY) {
		super.keyTyped(c, key, x, y, mouseX, mouseY);
		if (BleachingPage.mc.gameSettings.keyShowRecipe.isKeyboardKey(key)) {
			SlotGuidebook hoveringSlot = null;
			for (SlotGuidebook slot : this.slots) {
				if (!this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) continue;
				hoveringSlot = slot;
			}
			if (hoveringSlot != null && hoveringSlot.hasStack()) {
				String query = "r:" + hoveringSlot.getStack().getDisplayName() + "!";
				PageManager.searchQuery = SearchQuery.resolve(query);
				SearchPage.searchField.setText(query);
				GuiGuidebook.getPageManager().updatePages();
				GuiGuidebook.getPageManager().setCurrentPage(GuiGuidebook.getPageManager().getSectionIndex(GuidebookSections.CRAFTING), true);
			}
		} else if (BleachingPage.mc.gameSettings.keyShowUsage.isKeyboardKey(key)) {
			SlotGuidebook hoveringSlot = null;
			for (SlotGuidebook slot : this.slots) {
				if (!this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) continue;
				hoveringSlot = slot;
			}
			if (hoveringSlot != null && hoveringSlot.hasStack()) {
				String query = "u:" + hoveringSlot.getStack().getDisplayName() + "!";
				PageManager.searchQuery = SearchQuery.resolve(query);
				SearchPage.searchField.setText(query);
				GuiGuidebook.getPageManager().updatePages();
				GuiGuidebook.getPageManager().setCurrentPage(GuiGuidebook.getPageManager().getSectionIndex(GuidebookSections.CRAFTING), true);
			}
		}
	}

	@Override
	public void render(RenderEngine re, FontRenderer fr, int x, int y, int mouseX, int mouseY, float partialTicks) {
		super.render(re, fr, x, y, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBackground(RenderEngine re, int x, int y) {
		super.renderBackground(re, x, y);
		re.bindTexture(re.getTexture("/gui/crafting.png"));
		for (int i = 1; i <= this.recipes.size(); ++i) {
			RecipeEntryBleacher recipe = this.recipes.get(i - 1);
			List<SlotGuidebook> list = this.map.get(recipe);
			this.drawTexturedModalRect(x + list.get((int)(list.size() - 1)).xDisplayPosition - 32, y + list.get((int)(list.size() - 1)).yDisplayPosition, 90, 35, 22, 15);
		}
	}

	@Override
	protected void renderOverlay(RenderEngine re, FontRenderer fr, int x, int y, int mouseX, int mouseY, float partialTicks) {
		super.renderOverlay(re, fr, x, y, mouseX, mouseY, partialTicks);
		SlotGuidebook mouseOverSlot = null;
		for (SlotGuidebook slot : this.slots) {
			boolean showDescription;
			if (this.getIsMouseOverSlot(slot, x, y, mouseX, mouseY)) {
				mouseOverSlot = slot;
			}
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			if (mouseOverSlot == null || !mouseOverSlot.hasStack()) continue;
			boolean bl = showDescription = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157) || BleachingPage.mc.gameSettings.alwaysShowDescriptions.value;
			String str = this.guiTooltip.getTooltipText(mouseOverSlot.getStack(), showDescription, mouseOverSlot);
			if (str.isEmpty()) continue;
			this.guiTooltip.render(str, mouseX, mouseY, 8, -8);
		}
	}

	static {
		ticks = 0L;
	}
}
