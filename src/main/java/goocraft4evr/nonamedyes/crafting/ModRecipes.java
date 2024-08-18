package goocraft4evr.nonamedyes.crafting;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.crafting.recipe.RecipeEntryCinnamon;
import goocraft4evr.nonamedyes.crafting.recipe.RecipeEntryLabelModDye;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.*;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryTrommel;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.recipeBuilders.*;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static net.minecraft.core.data.registry.Registries.RECIPE_TYPES;

public class ModRecipes implements RecipeEntrypoint {
	public static RecipeNamespace RN;
	public static RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH;
	public static RecipeGroup<RecipeEntryFurnace> FURNACE;
	public static RecipeGroup<RecipeEntryBlastFurnace> BLAST_FURNACE;
	public static RecipeGroup<RecipeEntryBleacher> BLEACHER;

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
		RN = new RecipeNamespace();
		WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
		FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.furnaceStoneActive)));
		BLAST_FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.furnaceBlastActive)));
		BLEACHER = new RecipeGroup<>(new RecipeSymbol(new ItemStack(ModBlocks.bleacher)));

		Registries.RECIPES.register(NoNameDyes.MOD_ID, RN);
		RN.register("furnace", FURNACE);
		RN.register("blast_furnace", BLAST_FURNACE);
		RN.register("workbench", WORKBENCH);
		RN.register("bleacher", BLEACHER);
		RECIPE_TYPES.register("nonamedyes:bleaching",RecipeEntryBleacher.class);

		Registries.ITEM_GROUPS.register("minecraft:fences", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("minecraft:fencegates", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("minecraft:wooden_slabs", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("minecraft:wooden_stairs", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:plasters_mud", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:plasters_lime", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:ceramic_blocks", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:ceramic_tiles", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:painted_plasters", Registries.stackListOf());
		Registries.ITEM_GROUPS.register("nonamedyes:ores_malachite", Registries.stackListOf(ModBlocks.oreMalachiteStone, ModBlocks.oreMalachiteBasalt, ModBlocks.oreMalachiteGranite, ModBlocks.oreMalachiteLimestone));

		for (int i = 0; i < ItemDye.dyeColors.length; i++) {
			Registries.ITEM_GROUPS.getItem("minecraft:fences").add(new ItemStack(Block.fencePlanksOakPainted, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:fencegates").add(new ItemStack(Block.fencegatePlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:wooden_slabs").add(new ItemStack(Block.slabPlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:wooden_stairs").add(new ItemStack(Block.stairsPlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("nonamedyes:plasters_mud").add(new ItemStack(ModBlocks.plasterMud, 1, i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:plasters_lime").add(new ItemStack(ModBlocks.plasterLime, 1, i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_blocks").add(new ItemStack(ModBlocks.blockCeramicPainted, 1, i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_tiles").add(new ItemStack(ModBlocks.tileCeramicPainted, 1, i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:painted_plasters").add(new ItemStack(ModItems.paintedPlaster, 1, i));
		}
		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			Registries.ITEM_GROUPS.getItem("minecraft:planks").add(new ItemStack(ModBlocks.planksOakPainted, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:fences").add(new ItemStack(ModBlocks.fencePlanksOakPainted, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:fencegates").add(new ItemStack(ModBlocks.fencegatePlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:wooden_slabs").add(new ItemStack(ModBlocks.slabPlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:wooden_stairs").add(new ItemStack(ModBlocks.stairsPlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:chests").add(new ItemStack(ModBlocks.chestPlanksOakPainted, 1, i<<4));
			Registries.ITEM_GROUPS.getItem("minecraft:wools").add(new ItemStack(ModBlocks.wool, 1, i));
			Registries.ITEM_GROUPS.getItem("minecraft:lamps").add(new ItemStack(ModBlocks.lampIdle, 1, i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:plasters_mud").add(new ItemStack(ModBlocks.plasterMud, 1, 16+i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:plasters_lime").add(new ItemStack(ModBlocks.plasterLime, 1, 16+i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_blocks").add(new ItemStack(ModBlocks.blockCeramicPainted, 1, 16+i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:ceramic_tiles").add(new ItemStack(ModBlocks.tileCeramicPainted, 1, 16+i));
			Registries.ITEM_GROUPS.getItem("nonamedyes:painted_plasters").add(new ItemStack(ModItems.paintedPlaster, 1, 16+i));
		}

		Registries.ITEM_GROUPS.getItem("minecraft:logs").add(ModBlocks.logCinnamon.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:logs").add(ModBlocks.logEbony.getDefaultStack());

		Registries.ITEM_GROUPS.getItem("minecraft:leaves").add(ModBlocks.leavesCinnamon.getDefaultStack());
		Registries.ITEM_GROUPS.getItem("minecraft:leaves").add(ModBlocks.leavesEbony.getDefaultStack());

		Registries.ITEM_GROUPS.getItem("minecraft:chests").add(ModBlocks.chestPlanksOakPainted.getDefaultStack());
	}

	private void bleachingRecipes() {
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:planks")
			.create("painted_planks_to_planks",new ItemStack(Block.planksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:fences")
			.create("painted_fences_to_fences",new ItemStack(Block.fencePlanksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:fencegates")
			.create("painted_fencegates_to_fencegates",new ItemStack(Block.fencegatePlanksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:wooden_slabs")
			.create("painted_wooden_slabs_to_wooden_slabs",new ItemStack(Block.slabPlanksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:wooden_stairs")
			.create("painted_wooden_stairs_to_wooden_stairs",new ItemStack(Block.slabPlanksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:chests")
			.create("painted_chests_to_chests",new ItemStack(Block.chestPlanksOak));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:wools")
			.create("painted_wools_to_wools",new ItemStack(Block.wool));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("minecraft:lamps")
			.create("painted_lamps_to_lamps",new ItemStack(Block.lampIdle));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:plasters_mud")
			.create("painted_plastered_mud_to_plastered_mud",new ItemStack(ModBlocks.plasterMud));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:plasters_lime")
			.create("painted_plastered_lime_to_plastered_lime",new ItemStack(ModBlocks.plasterLime));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:ceramic_blocks")
			.create("painted_ceramic_blocks_to_ceramic_blocks",new ItemStack(ModBlocks.blockCeramicPainted));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:ceramic_tiles")
			.create("painted_ceramic_tiles_to_ceramic_tiles",new ItemStack(ModBlocks.tileCeramicPainted));
		new RecipeBuilderBleacher(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:painted_plasters")
			.create("painted_plasters_to_wet_plasters",new ItemStack(ModItems.wetPlaster));
	}

	private void craftingRecipes(){
		//mod dyes
		for (int i=0;i<ItemModDye.NUM_DYES;i++) {
			ItemStack dye = new ItemStack(ModItems.dye,1,i);
			String dyename = ItemModDye.dyeColors[i];
			//planks
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PPP",
						  "PDP",
						  "PPP")
				.addInput('P',"minecraft:planks")
				.addInput('D',dye)
				.create(String.format("wooden_planks_%s",dyename),
					new ItemStack(ModBlocks.planksOakPainted,8,i));
			//fences
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PSP",
						  "PSP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.addInput('S',Item.stick)
				.create(String.format("wooden_fence_%s",dyename),
					new ItemStack(ModBlocks.fencePlanksOakPainted,6,i));
			//fencegates
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("SPS",
						  "SPS")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.addInput('S',Item.stick)
				.create(String.format("wooden_fencegate_%s",dyename),
					new ItemStack(ModBlocks.fencegatePlanksOakPainted,3,i<<4));
			//slabs
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PPP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.create(String.format("wooden_slab_%s",dyename),
					new ItemStack(ModBlocks.slabPlanksOakPainted,6,i<<4));
			//stairs
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("P  ",
						  "PP ",
						  "PPP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.create(String.format("wooden_stairs_%s",dyename),
					new ItemStack(ModBlocks.stairsPlanksOakPainted,6,i<<4));
			//chests
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PPP",
						  "P P",
						  "PPP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.create(String.format("wooden_chest_%s",dyename),
					new ItemStack(ModBlocks.chestPlanksOakPainted,1,i<<4));
			//lamps
			new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
				.addInput("minecraft:lamps")
				.addInput(dye)
				.create(String.format("lamp_%s",dyename),
					new ItemStack(ModBlocks.lampIdle,1,i));
			//wools
			new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
				.addInput("minecraft:wools")
				.addInput(dye)
				.create(String.format("wool_%s",dyename),
					new ItemStack(ModBlocks.wool,1,i));
			//plasters
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PPP",
						  "PDP",
					 	  "PPP")
				.addInput('P',ModItems.wetPlaster)
				.addInput('D',dye)
				.create(String.format("plaster_%s",dyename),
					new ItemStack(ModItems.paintedPlaster,8,16+i));
			//ceramic blocks
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("CCC",
						  "CDC",
						  "CCC")
				.addInput('C',"nonamedyes:ceramic_blocks")
				.addInput('D',dye)
				.create(String.format("ceramic_block_%s",dyename),
					new ItemStack(ModBlocks.blockCeramicPainted,8,16+i));
			//ceramic tiles
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("C",
						  "C")
				.addInput('C',new ItemStack(ModBlocks.blockCeramicPainted,1,16+i))
				.create(String.format("ceramic_tile_%s",dyename),
					new ItemStack(ModBlocks.tileCeramicPainted,8,16+i));
			//trapdoors
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape(
					"PPP",
					"PPP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.create(String.format("%s_trapdoor",dyename),
					new ItemStack(ModBlocks.trapDoorPainted,6,i<<4));
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape(
					"PP",
					"PP",
					"PP")
				.addInput('P',new ItemStack(ModBlocks.planksOakPainted,1,i))
				.create(String.format("%s_door",dyename),
					new ItemStack(ModItems.doorOakPainted,2,i));
		}

		//vanilla dyes
		for (int i=0;i<ItemDye.dyeColors.length;i++) {
			ItemStack dye = new ItemStack(Item.dye,1,i^15);
			String dyename = ItemDye.dyeColors[i^15];
			//plasters
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("PPP",
					"PDP",
					"PPP")
				.addInput('P',ModItems.wetPlaster)
				.addInput('D',dye)
				.create(String.format("plaster_%s",dyename),
					new ItemStack(ModItems.paintedPlaster,8,i));
			//ceramic blocks
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("CCC",
					"CDC",
					"CCC")
				.addInput('C',"nonamedyes:ceramic_blocks")
				.addInput('D',dye)
				.create(String.format("ceramic_block_%s",dyename),
					new ItemStack(ModBlocks.blockCeramicPainted,8,i));
			//ceramic tiles
			new RecipeBuilderShaped(NoNameDyes.MOD_ID)
				.setShape("C",
					"C")
				.addInput('C',new ItemStack(ModBlocks.blockCeramicPainted,1,i))
				.create(String.format("ceramic_tile_%s",dyename),
					new ItemStack(ModBlocks.tileCeramicPainted,8,i));
		}

		//misc
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("##",
					  "##")
			.addInput('#',ModItems.dye,4)
			.create("ochre_block",new ItemStack(ModBlocks.ochreBlock));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("###", "###","###")
			.addInput('#',ModItems.dye,6)
			.create("block_malachite",new ItemStack(ModBlocks.blockMalachite));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.blockMalachite)
			.create("malachite_decompress",new ItemStack(ModItems.dye,9,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("##", "##")
			.addInput('#',ModItems.dye,6)
			.create("brick_malachite",new ItemStack(ModBlocks.brickMalachite,4));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(Item.sulphur)
			.addInput(Item.dye,15)
			.addInput(ModItems.limePowder)
			.create("bleaching_powder",new ItemStack(ModItems.bleachingPowder,3));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(Block.cobbleLimestone)
			.create("lime_powder",new ItemStack(ModItems.limePowder,4));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("C C", "S S", "SSS")
			.addInput('C',Block.cobbleStone)
			.addInput('S',Block.stonePolished)
			.create("bleacher",new ItemStack(ModBlocks.bleacher));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape(" S ", "WCW")
			.addInput('W',Item.wheat)
			.addInput('C',ModItems.dye,10)
			.addInput('S',Item.dustSugar)
			.create("snickerdoodle",new ItemStack(ModItems.foodSnickerdoodle,8));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.logCinnamon)
			.create("cinnamon_log_to_planks",new ItemStack(ModBlocks.planksOakPainted,4,10));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.logEbony)
			.create("ebony_log_to_planks",new ItemStack(Block.planksOakPainted,4,15));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("VVV", "VVV", "VVV")
			.addInput('V',ModItems.vileShard)
			.create("gallstone",new ItemStack(ModBlocks.gallstone));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("NV", "VN")
			.addInput('V',ModItems.vileShard)
			.addInput('N',Block.netherrack)
			.create("vile_netherrack",new ItemStack(ModBlocks.netherrackVile,2));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("VGV","G G","VGV")
			.addInput('V',ModItems.vileShard)
			.addInput('G',ModBlocks.gallstone)
			.create("vile_reactor",new ItemStack(ModBlocks.vileReactorIdle));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(Block.sand)
			.addInput(Item.clay)
			.addInput(Item.clay)
			.addInput(Item.clay)
			.addInput(Item.clay)
			.addInput(ModItems.limePowder)
			.addInput(ModItems.limePowder)
			.addInput(ModItems.limePowder)
			.addInput(ModItems.limePowder)
			.create("wet_plaster",new ItemStack(ModItems.wetPlaster,12));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("CC","CC")
			.addInput('C',ModItems.ceramic)
			.create("block_ceramic",new ItemStack(ModBlocks.blockCeramicPainted,4));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("SI")
			.addInput('S',Item.stick)
			.addInput('I',Item.ingotIron)
			.create("paint_scraper",new ItemStack(ModItems.paintScraper));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("##","##")
			.addInput('#',ModItems.brickOchre)
			.create("brick_ochre",new ItemStack(ModBlocks.brickOchre,4));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("###")
			.addInput('#',ModBlocks.brickOchre)
			.create("slab_brick_ochre",new ItemStack(ModBlocks.slabBrickOchre,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("#  ", "## ","###")
			.addInput('#',ModBlocks.brickOchre)
			.create("stairs_brick_ochre",new ItemStack(ModBlocks.stairsBrickOchre,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("###")
			.addInput('#',ModBlocks.brickMalachite)
			.create("slab_brick_malachite",new ItemStack(ModBlocks.slabBrickMalachite,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("#  ", "## ","###")
			.addInput('#',ModBlocks.brickMalachite)
			.create("stairs_brick_malachite",new ItemStack(ModBlocks.stairsBrickMalachite,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("###", "###","###")
			.addInput('#',ModItems.dye,13)
			.create("block_eskolaite",new ItemStack(ModBlocks.blockEskolaite));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.blockEskolaite)
			.create("eskolaite_decompress",new ItemStack(ModItems.dye,9,13));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("##", "##")
			.addInput('#',ModItems.dye,13)
			.create("brick_eskolaite",new ItemStack(ModBlocks.brickEskolaite,4));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("###")
			.addInput('#',ModBlocks.brickEskolaite)
			.create("slab_brick_eskolaite",new ItemStack(ModBlocks.slabBrickEskolaite,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("#  ", "## ","###")
			.addInput('#',ModBlocks.brickEskolaite)
			.create("stairs_brick_eskolaite",new ItemStack(ModBlocks.stairsBrickEskolaite,6));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("SPS","PSP","SPS")
			.addInput('S',Item.ammoSnowball)
			.addInput('P',Item.ammoPebble)
			.create("slush",new ItemStack(ModBlocks.slush,1));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("PI","IP")
			.addInput('I',ModItems.ammoIcecube)
			.addInput('P',Item.ammoPebble)
			.create("permafrost",new ItemStack(Block.permafrost,2));
		new RecipeBuilderShaped(NoNameDyes.MOD_ID)
			.setShape("III","III","III")
			.addInput('I',ModItems.ammoIcecube)
			.create("ice",new ItemStack(Block.ice,1));

		//the dyes
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.netherRoots)
			.create("dye_crimson",new ItemStack(ModItems.dye,2,0));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModItems.dye,0)
			.addInput(Item.dye,3)
			.create("dye_maroon",new ItemStack(ModItems.dye,2,1));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.mushroomInkCap)
			.create("dye_ash_grey",new ItemStack(ModItems.dye,2,2));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(Item.dye,2)
			.addInput(Item.dye,3)
			.create("dye_olive",new ItemStack(ModItems.dye,2,3));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModItems.dye,4)
			.addInput(Item.dye,15)
			.create("dye_buff",new ItemStack(ModItems.dye,2,5));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(Item.dye,15)
			.addInput(Item.dye,11)
			.create("dye_light_yellow",new ItemStack(ModItems.dye,2,7));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModBlocks.flowerIndigo)
			.create("dye_indigo",new ItemStack(ModItems.dye,2,8));
		new RecipeBuilderShapeless(NoNameDyes.MOD_ID)
			.addInput(ModItems.dye,8)
			.addInput(Item.dye,0)
			.create("dye_navy_blue",new ItemStack(ModItems.dye,2,11));
	}
	private void furnaceRecipes(){
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.planksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.slabPlanksOakPainted.id, 150);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.fencePlanksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.fencegatePlanksOakPainted.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.logCinnamon.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.saplingCinnamon.id, 100);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.logEbony.id, 300);
		LookupFuelFurnace.instance.addFuelEntry(ModBlocks.saplingEbony.id, 100);

		//furnace
		new RecipeBuilderFurnace(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:ores_malachite")
			.create("ore_malachite_to_malachite",new ItemStack(ModItems.dye,1,6));
		new RecipeBuilderFurnace(NoNameDyes.MOD_ID)
			.setInput(ModBlocks.netherrackVile)
			.create("netherrack_vile_to_vile_shard",new ItemStack(ModItems.vileShard));
		new RecipeBuilderFurnace(NoNameDyes.MOD_ID)
			.setInput(ModItems.vileShard)
			.create("vile_shard_to_dye",new ItemStack(ModItems.dye,1,9));
		new RecipeBuilderFurnace(NoNameDyes.MOD_ID)
			.setInput(ModItems.dye,4)
			.create("ochre_to_ochre_brick",new ItemStack(ModItems.brickOchre));

		//blast furnace
		new RecipeBuilderBlastFurnace(NoNameDyes.MOD_ID)
			.setInput(Item.clay)
			.create("clay_to_ceramic",new ItemStack(ModItems.ceramic));
		new RecipeBuilderBlastFurnace(NoNameDyes.MOD_ID)
			.setInput(ModBlocks.netherrackVile)
			.create("netherrack_vile_to_dye",new ItemStack(ModItems.dye,1,9));
		new RecipeBuilderBlastFurnace(NoNameDyes.MOD_ID)
			.setInput("nonamedyes:ores_malachite")
			.create("ore_malachite_to_malachite",new ItemStack(ModItems.dye,1,6));
		new RecipeBuilderBlastFurnace(NoNameDyes.MOD_ID)
			.setInput(ModItems.vileShard)
			.create("vile_shard_to_dye",new ItemStack(ModItems.dye,1,9));
		new RecipeBuilderBlastFurnace(NoNameDyes.MOD_ID)
			.setInput(ModItems.dye,4)
			.create("ochre_to_ochre_brick",new ItemStack(ModItems.brickOchre));
	}
	private void trommelRecipes(){
		RecipeGroup<RecipeEntryTrommel> trommel = Registries.RECIPES.TROMMEL;
		trommel.getItem("rich_dirt").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 4), 4,8), 12.0);
		trommel.getItem("sand").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 4), 4,8), 10.0);
		trommel.getItem("rich_dirt").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 6), 2,4), 18.0);
		trommel.getItem("gravel").getOutput().addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye, 1, 6), 2,4), 2.0);
		new RecipeBuilderTrommel(NoNameDyes.MOD_ID)
			.setInput(new ItemStack(ModBlocks.slush))
			.addEntry(new WeightedRandomLootObject(new ItemStack(Item.ammoSnowball), 1,2), 25.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(Item.ammoPebble), 1,4), 25.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(Item.flint), 1,2), 20.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.ammoIcecube), 1,4), 20.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(Item.coal,1,1), 1,3), 15.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(Item.oreRawGold), 1,2), 3.0)
			.addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.dye,1,13), 2,6), 3.0)
			.create("slush");
	}
}
