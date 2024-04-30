package goocraft4evr.nonamedyes.block;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.UtilIdRegistrar;
import goocraft4evr.nonamedyes.block.cinnamon.BlockLeavesCinnamon;
import goocraft4evr.nonamedyes.block.cinnamon.BlockSaplingCinnamon;
import goocraft4evr.nonamedyes.block.ebony.BlockLeavesEbony;
import goocraft4evr.nonamedyes.block.ebony.BlockSaplingEbony;
import goocraft4evr.nonamedyes.block.palm.BlockLeavesPalm;
import goocraft4evr.nonamedyes.block.palm.BlockSaplingPalm;
import goocraft4evr.nonamedyes.block.wood.*;
import goocraft4evr.nonamedyes.client.render.block.model.BlockModelNo3dRender;
import goocraft4evr.nonamedyes.item.ItemBlockDeprecated;
import goocraft4evr.nonamedyes.item.block.ItemModBlockPainted;
import goocraft4evr.nonamedyes.item.block.ItemBlockPlasterPainted;
import goocraft4evr.nonamedyes.item.block.ItemModBlockSlabPainted;
import net.minecraft.client.render.block.color.BlockColorLeaves;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

public abstract class ModBlocks {
    private static final BlockBuilder woodBlockBuilder = new BlockBuilder(NoNameDyes.MOD_ID)
            .setHardness(2.0f)
            .setResistance(5.0f)
            .setVisualUpdateOnMetadata()
            .setFlammability(5,20)
            .setBlockSound(BlockSounds.WOOD);


    public static final Block planksOakPainted = woodBlockBuilder
            .setItemBlock(block -> new ItemModBlockPainted(block,false))
            .build(new BlockModPlanksPainted("planks.oak.painted",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block fencePlanksOakPainted = woodBlockBuilder
            .setItemBlock(block -> new ItemModBlockPainted(block,false))
            .setBlockModel(new BlockModelRenderBlocks(11))
            .build(new BlockModFencePainted("fence.planks.oak.painted",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.CAN_HANG_OFF, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block fencegatePlanksOakPainted = woodBlockBuilder
            .setItemBlock(block -> new ItemModBlockPainted(block,true))
            .setBlockModel(new BlockModelRenderBlocks(18))
            .build(new BlockModFenceGatePainted("fencegate.planks.oak.painted",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block slabPlanksOakPainted = woodBlockBuilder
            .setItemBlock(ItemModBlockSlabPainted::new)
            .setUseInternalLight()
            .build(new BlockModSlabPainted(planksOakPainted,UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block stairsPlanksOakPainted = woodBlockBuilder
            .setItemBlock(block -> new ItemModBlockPainted(block,true))
            .setUseInternalLight()
            .setBlockModel(new BlockModelRenderBlocks(10))
            .build(new BlockModStairsPainted(planksOakPainted,UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block chestPlanksOakPainted = new BlockBuilder(NoNameDyes.MOD_ID)
            .setHardness(2.5f)
            .setResistance(5.0f)
            .setVisualUpdateOnMetadata()
            .setBlockSound(BlockSounds.WOOD)
            .setImmovable()
            .setLightOpacity(3)
            .setItemBlock(block -> new ItemModBlockPainted(block,true))
            .build(new BlockModChestPainted("chest.planks.oak.painted",UtilIdRegistrar.nextId(), Material.wood))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    //TODO: This used .setDisabledStats()
    public static final Block lampIdle = new BlockBuilder(NoNameDyes.MOD_ID)
            .setHardness(0.5f)
            .setVisualUpdateOnMetadata()
            .setBlockSound(BlockSounds.GLASS)
            .setItemBlock(ItemModBlockPainted::new)
            .build(new BlockModLamp("lamp.idle",UtilIdRegistrar.nextId(),false)
            .withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU))
            .withTexCoords(17, 31); // TODO individual textures


    //TODO: luminance was initially 0.9375f, this block used setDisabledStats()
    public static final Block lampActive = new BlockBuilder(NoNameDyes.MOD_ID)
            .setLuminance(14)
            .setHardness(0.5f)
            .setVisualUpdateOnMetadata()
            .setBlockSound(BlockSounds.GLASS)
            .build(new BlockModLamp("lamp.active",UtilIdRegistrar.nextId(),true)
            .withTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE));
    public static final Block wool = new BlockBuilder(NoNameDyes.MOD_ID)
            .setHardness(0.8f)
            .setVisualUpdateOnMetadata()
            .setBlockSound(BlockSounds.CLOTH)
            .setFlammability(30,60)
            .setItemBlock(ItemModBlockPainted::new)
            .build(new BlockModWool("wool",UtilIdRegistrar.nextId())
            .withTags(BlockTags.MINEABLE_BY_SHEARS, BlockTags.NOT_IN_CREATIVE_MENU));

    public static final Block netherRoots = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("nether_roots.png")
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.1f)
            .setResistance(0.1f)
            .setBlockModel(new BlockModelNo3dRender())
            .build(new BlockNetherRoots("nether.roots", UtilIdRegistrar.nextId(), Material.plant)
            .withTags(BlockTags.MINEABLE_BY_SHEARS,BlockTags.SHEARS_DO_SILK_TOUCH));

    //NOTE: luminance was originally 0.125f
    public static final Block mushroomInkCap = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("ink_cap.png")
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setLuminance(1)
            .setTickOnLoad()
            .setBlockModel(new BlockModelRenderBlocks(1))
            .build(new BlockMushroomInkCap("mushroom.ink.cap", UtilIdRegistrar.nextId())
            .withTags(BlockTags.BROKEN_BY_FLUIDS));

    public static final Block ochreBlock = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("ochre_block.png")
            .setBlockSound(BlockSounds.GRAVEL)
            .setHardness(0.6f)
            .build(new BlockOchre("block.ochre",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_SHOVEL);

    private static final BlockBuilder malachiteBuilder = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(3.0f)
            .setResistance(5.0f)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block oreMalachiteStone = malachiteBuilder
            .setTextures("ore_malachite_stone.png")
            .build(new BlockOreMalachite("ore.malachite.stone",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteBasalt = malachiteBuilder
            .setTextures("ore_malachite_basalt.png")
            .build(new BlockOreMalachite("ore.malachite.basalt",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteLimestone = malachiteBuilder
            .setTextures("ore_malachite_limestone.png")
            .build(new BlockOreMalachite("ore.malachite.limestone",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteGranite = malachiteBuilder
            .setTextures("ore_malachite_granite.png")
            .build(new BlockOreMalachite("ore.malachite.granite",UtilIdRegistrar.nextId()));

    public static final Block blockMalachite = malachiteBuilder
            .setTextures("malachite_block.png")
            .build(new Block("block.malachite",UtilIdRegistrar.nextId(),Material.stone));

    public static final Block flowerIndigo = ((BlockFlower)new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("indigo_flower.png")
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .build(new BlockFlower("flower.indigo", UtilIdRegistrar.nextId())))
            .setKilledByWeather()
            .withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

	public static final Block logPalm = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTopBottomTexture("log_palm_top.png")
            .setSideTextures("log_palm_sides.png")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
			.setTickOnLoad()
            .setFlammability(5,5)
            .setBlockModel(new BlockModelRenderBlocks(27))
            .build(new BlockLog("log.palm", UtilIdRegistrar.nextId()) {
			})
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);
	//TODO: change
	@Deprecated
	public static final Block logPalmRipe = new BlockBuilder(NoNameDyes.MOD_ID)
		.setItemBlock(b -> new ItemBlockDeprecated(b,Block.logOakMossy))
            .setTopBottomTexture("log_palm_top.png")
            .setSideTextures("log_palm_ripe_sides.png")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
			.setTickOnLoad()
            .setFlammability(5,3)
            .build(new BlockDeprecated("log.palm.ripe", UtilIdRegistrar.nextId(), Material.wood,Block.logOakMossy.id))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE,BlockTags.NOT_IN_CREATIVE_MENU);

    //HAS DISABLED STATS
    public static final Block leavesPalm = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.2f)
            .setLightOpacity(1)
            .setVisualUpdateOnMetadata()
            .setTickOnLoad()
            .setBlockColor(new BlockColorLeaves("palm"))
            .setFlammability(30,60)
            .build(new BlockLeavesPalm("leaves.palm", UtilIdRegistrar.nextId()))
            .withDisabledStats()
            .withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS);

	public static final Block saplingPalm = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("sapling_palm.png")
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .setHardness(0.0f)
            .setTickOnLoad()
            .setVisualUpdateOnMetadata()
            .build(new BlockSaplingPalm("sapling.palm", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

    public static final Block brickMalachite = malachiteBuilder
            .setResistance(10.0f)
            .setTextures("brick_malachite.png")
            .build(new Block("brick.malachite", UtilIdRegistrar.nextId(), Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block bleacher = new BlockBuilder(NoNameDyes.MOD_ID)
            .setVisualUpdateOnMetadata()
            .setImmovable()
            .setHardness(3.5f)
            .setBlockSound(BlockSounds.STONE)
            .setSideTextures("bleacher_sides.png")
            .setBottomTexture("bleacher_bottom.png")
            .setTopTexture("bleacher_top_empty.png")
            .build(new BlockBleacher("bleacher", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block netherrackVile = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures("netherrack_vile.png")
            .setHardness(0.4f)
            .setInfiniburn()
            .build(new Block("netherrack.vile", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block logCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTopBottomTexture("log_cinnamon_top.png")
            .setSideTextures("log_cinnamon_sides.png")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
            .setFlammability(5,5)
            .setBlockModel(new BlockModelRenderBlocks(27))
            .build(new BlockLog("log.cinnamon", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);

    //HAS DISABLED STATS
    public static final Block leavesCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.2f)
            .setLightOpacity(1)
            .setVisualUpdateOnMetadata()
            .setTickOnLoad()
            .setBlockColor(new BlockColorLeaves("cinnamon"))
            .setFlammability(30,60)
            .build(new BlockLeavesCinnamon("leaves.cinnamon", UtilIdRegistrar.nextId()))
            .withDisabledStats()
            .withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS);

    public static final Block saplingCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .setHardness(0.0f)
            .setTextures("sapling_cinnamon.png")
            .setTickOnLoad()
            .setVisualUpdateOnMetadata()
            .build(new BlockSaplingCinnamon("sapling.cinnamon", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

    public static final Block gallstone = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.5f)
            .setResistance(8.0f)
            .setTextures("gallstone.png")
            .build(new Block("gallstone", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block vileReactorIdle = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setTextures("vile_reactor_0.png")
            .build(new BlockVileReactor("vile.reactor.idle", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block vileReactorActive = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setLuminance(6)
            .setTextures("vile_reactor_1.png")
            .build(new BlockVileReactor("vile.reactor.active", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block vileReactorVeryActive = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setLuminance(12)
            .setTextures("vile_reactor_2.png")
            .build(new BlockVileReactor("vile.reactor.veryactive", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block logEbony = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTopBottomTexture("log_ebony_top.png")
            .setSideTextures("log_ebony_sides.png")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
            .setFlammability(5,5)
            .setBlockModel(new BlockModelRenderBlocks(27))
            .build(new BlockLog("log.ebony", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);

    //HAS DISABLED STATS
    public static final Block leavesEbony = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.2f)
            .setLightOpacity(1)
            .setVisualUpdateOnMetadata()
            .setTickOnLoad()
            .setBlockColor(new BlockColorLeaves("ebony"))
            .setFlammability(30,60)
            .build(new BlockLeavesEbony("leaves.ebony", UtilIdRegistrar.nextId()))
            .withDisabledStats()
            .withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS);

    public static final Block saplingEbony = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .setHardness(0.0f)
            .setTextures("sapling_ebony.png")
            .setTickOnLoad()
            .setVisualUpdateOnMetadata()
            .build(new BlockSaplingEbony("sapling.ebony", UtilIdRegistrar.nextId()))
			.withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

	//TODO: change
	@Deprecated
    public static final Block plaster = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.3f)
            .setTextures("plaster/plaster.png")
            .build(new BlockDeprecated("plaster", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block plasterMud = new BlockBuilder(NoNameDyes.MOD_ID)
            .setItemBlock(ItemBlockPlasterPainted::new)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.3f)
            .build(new BlockCeramicPainted("plaster.mud", UtilIdRegistrar.nextId(),Material.stone,"plaster/*_mud_plaster.png"))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	//TODO: change
	@Deprecated
    public static final Block blockCeramic = new BlockBuilder(NoNameDyes.MOD_ID)

            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures("ceramic/ceramic_block.png")
            .build(new BlockDeprecated("block.ceramic", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id+1))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	//TODO: change
	@Deprecated
    public static final Block tileCeramic = new BlockBuilder(NoNameDyes.MOD_ID)

            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures("ceramic/ceramic_tile.png")
            .build(new BlockDeprecated("tile.ceramic", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id+1))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block blockCeramicPainted = new BlockBuilder(NoNameDyes.MOD_ID)
            .setItemBlock(ItemBlockPlasterPainted::new)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures("ceramic/ceramic_block_painted.png")
            .build(new BlockCeramicPainted("block.ceramic.painted", UtilIdRegistrar.nextId(),Material.stone,"ceramic/*_ceramic_block.png"))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block tileCeramicPainted = new BlockBuilder(NoNameDyes.MOD_ID)
            .setItemBlock(ItemBlockPlasterPainted::new)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures("ceramic/ceramic_tile_painted.png")
            .build(new BlockCeramicPainted("tile.ceramic.painted", UtilIdRegistrar.nextId(),Material.stone,"ceramic/*_ceramic_tile.png"))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	public static final Block plasterLime = new BlockBuilder(NoNameDyes.MOD_ID)
		.setItemBlock(ItemBlockPlasterPainted::new)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(1.5f)
		.setResistance(10.0f)
		.build(new BlockCeramicPainted("plaster.limestone", UtilIdRegistrar.nextId(),Material.stone,"plaster/*_limestone_plaster.png"))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	public static final Block brickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(3.0f)
		.setResistance(10.0f)
		.setTextures("brick_ochre.png")
		.build(new Block("brick.ochre", UtilIdRegistrar.nextId(), Material.stone))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block slabBrickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setItemBlock(ItemBlockSlab::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockSlab(brickOchre, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block stairsBrickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(new BlockModelRenderBlocks(10))
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockStairs(brickOchre, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block slabBrickMalachite = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setItemBlock(ItemBlockSlab::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockSlab(brickMalachite, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block stairsBrickMalachite = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(new BlockModelRenderBlocks(10))
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockStairs(brickMalachite, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);


	public static void register() {
		Item.itemsList[plaster.id] = new ItemBlockDeprecated(plaster,plasterMud);
		Item.itemsList[blockCeramic.id] = new ItemBlockDeprecated(blockCeramic,blockCeramicPainted);
		Item.itemsList[tileCeramic.id] = new ItemBlockDeprecated(tileCeramic,tileCeramicPainted);
    }
}
