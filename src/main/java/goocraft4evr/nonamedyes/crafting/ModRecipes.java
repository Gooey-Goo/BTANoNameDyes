package goocraft4evr.nonamedyes.crafting;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.client.gui.guidebook.bleaching.BleachingSection;
import goocraft4evr.nonamedyes.crafting.recipe.RecipeEntryCinnamon;
import goocraft4evr.nonamedyes.crafting.recipe.RecipeEntryLabelModDye;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.client.gui.guidebook.GuidebookSections;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.*;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryTrommel;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static net.minecraft.core.data.registry.Registries.RECIPE_TYPES;

public class ModRecipes implements RecipeEntrypoint {
	public static final RecipeNamespace RN = new RecipeNamespace();
	public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
	public static final RecipeGroup<RecipeEntryFurnace> FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.furnaceStoneActive)));
	public static final RecipeGroup<RecipeEntryBlastFurnace> BLAST_FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.furnaceBlastActive)));
	public static final RecipeGroup<RecipeEntryBleacher> BLEACHER = new RecipeGroup<>(new RecipeSymbol(new ItemStack(ModBlocks.bleacher)));

	@Override
	public void onRecipesReady() {
		WORKBENCH.register("label_dye", new RecipeEntryLabelModDye());
		WORKBENCH.register("cinnamon", new RecipeEntryCinnamon());

		RECIPE_TYPES.register("nonamedyes:crafting/label_dye", RecipeEntryLabelModDye.class);
		RECIPE_TYPES.register("nonamedyes:crafting/cinnamon", RecipeEntryCinnamon.class);

		craftingRecipes();
		furnaceRecipes();
		trommelRecipes();
		bleachingRecipes();
	}

	@Override
	public void initNamespaces() {
		Registries.RECIPES.register(NoNameDyes.MOD_ID, RN);
		RN.register("furnace", FURNACE);
		RN.register("blast_furnace", BLAST_FURNACE);
		RN.register("workbench", WORKBENCH);
		RN.register("bleacher", BLEACHER);
		RECIPE_TYPES.register("nonamedyes:bleaching",RecipeEntryBleacher.class);

		Registries.ITEM_GROUPS.register("nonamedyes:ceramics", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:ceramic_tiles", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:ores_malachite", Registries.stackListOf(ModBlocks.oreMalachiteStone, ModBlocks.oreMalachiteBasalt, ModBlocks.oreMalachiteGranite, ModBlocks.oreMalachiteLimestone));

		for (int i = 0; i < ItemDye.dyeColors.length; i++) {
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramics").add(new ItemStack(ModBlocks.blockCeramicPainted, 1, i^15));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_tiles").add(new ItemStack(ModBlocks.tileCeramicPainted, 1, i^15));
		}
		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramics").add(new ItemStack(ModBlocks.blockCeramicPainted, 1, i^15));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_tiles").add(new ItemStack(ModBlocks.tileCeramicPainted, 1, i^15));
			Registries.ITEM_GROUPS.getItem("minecraft:planks").add(new ItemStack(ModBlocks.planksOakPainted.id, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:wools").add(new ItemStack(ModBlocks.wool.id, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:lamps").add(new ItemStack(ModBlocks.lampIdle.id, 1, i));
		}
		Registries.ITEM_GROUPS.getItem("minecraft:logs").add(ModBlocks.logCinnamon.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:logs").add(ModBlocks.logEbony.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:logs").add(ModBlocks.logPalm.getDefaultStack());

		Registries.ITEM_GROUPS.getItem("minecraft:leaves").add(ModBlocks.leavesCinnamon.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:leaves").add(ModBlocks.leavesEbony.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:leaves").add(ModBlocks.leavesPalm.getDefaultStack());

		Registries.ITEM_GROUPS.getItem("minecraft:chests").add(ModBlocks.chestPlanksOakPainted.getDefaultStack());

		GuidebookSections.register(new BleachingSection("guidebook.section.bleacher", new ItemStack(ModBlocks.bleacher), 0x808000, 0xFFFF00));
	}

	private void bleachingRecipes() {
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/bleacher.json", NoNameDyes.MOD_ID));
	}

	private void craftingRecipes(){
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/workbench.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/stairs.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/slabs.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/fences.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/fencegates.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/chests.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/planks.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/wools.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/lamps.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/plasters.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/ceramics.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/ceramic_tiles.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/ceramic_tiles2.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/dyes.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/workbench/vanilla_overrides.json", NoNameDyes.MOD_ID));
	}
	private void furnaceRecipes(){
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.planksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.slabPlanksOakPainted.id, 150);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.fencePlanksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.fencegatePlanksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.logPalm.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.saplingPalm.id, 100);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.logCinnamon.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.saplingCinnamon.id, 100);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.logEbony.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.saplingEbony.id, 100);

		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/furnace.json", NoNameDyes.MOD_ID));
		DataLoader.loadRecipesFromFile(String.format("/assets/%s/recipes/blast_furnace.json", NoNameDyes.MOD_ID));
	}
	private void trommelRecipes(){
		RecipeGroup<RecipeEntryTrommel> trommel = Registries.RECIPES.TROMMEL;
		trommel.getItem("rich_dirt").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 4), 4,8), 12.0);
		trommel.getItem("sand").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 4), 4,8), 10.0);
		trommel.getItem("rich_dirt").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 6), 2,4), 18.0);
		trommel.getItem("gravel").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 6), 2,4), 2.0);
	}
}
