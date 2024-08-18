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
import goocraft4evr.nonamedyes.client.render.block.model.*;
import goocraft4evr.nonamedyes.client.render.colorizer.ModColorizers;
import goocraft4evr.nonamedyes.item.ItemBlockDeprecated;
import goocraft4evr.nonamedyes.item.block.ItemModBlockPainted;
import goocraft4evr.nonamedyes.item.block.ItemBlockPlasterPainted;
import goocraft4evr.nonamedyes.item.block.ItemModBlockSlabPainted;
import net.minecraft.client.render.block.color.BlockColorCustom;
import net.minecraft.client.render.block.model.*;
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
		.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/plank/planks_oak_painted_%s",false))
		.build(new BlockModPlanksPainted("planks.oak.painted",UtilIdRegistrar.nextId()))
		.withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block fencePlanksOakPainted = woodBlockBuilder
            .setItemBlock(b -> new ItemModBlockPainted(b,false))
            .setBlockModel(b -> new BlockModelFenceModDyed<>(b,NoNameDyes.MOD_ID+":block/plank/planks_oak_painted_%s"))
            .build(new BlockModFencePainted("fence.planks.oak.painted",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.CAN_HANG_OFF, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block fencegatePlanksOakPainted = woodBlockBuilder
            .setItemBlock(b -> new ItemModBlockPainted(b,true))
            .setBlockModel(b -> new BlockModelFenceGateModDyed<>(b,NoNameDyes.MOD_ID+":block/plank/planks_oak_painted_%s"))
            .build(new BlockModFenceGatePainted("fencegate.planks.oak.painted",UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block slabPlanksOakPainted = woodBlockBuilder
		.setItemBlock(ItemModBlockSlabPainted::new)
		.setUseInternalLight()
		.setBlockModel(b -> new BlockModelSlabModDyed<>(b,NoNameDyes.MOD_ID+":block/plank/planks_oak_painted_%s"))
		.build(new BlockModSlabPainted(planksOakPainted,UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block stairsPlanksOakPainted = woodBlockBuilder
		.setItemBlock(b -> new ItemModBlockPainted(b,true))
		.setUseInternalLight()
		.setBlockModel(b -> new BlockModelStairsModDyed<>(b,NoNameDyes.MOD_ID+":block/plank/planks_oak_painted_%s"))
		.build(new BlockModStairsPainted(planksOakPainted,UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block chestPlanksOakPainted = new BlockBuilder(NoNameDyes.MOD_ID)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setVisualUpdateOnMetadata()
		.setBlockSound(BlockSounds.WOOD)
		.setImmovable()
		.setLightOpacity(3)
		.setItemBlock(b -> new ItemModBlockPainted(b,true))
		.setBlockModel(BlockModelChestModDyed::new)
		.build(new BlockModChestPainted("chest.planks.oak.painted",UtilIdRegistrar.nextId(), Material.wood))
		.withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block lampIdle = new BlockBuilder(NoNameDyes.MOD_ID)
		.setHardness(0.5f)
		.setVisualUpdateOnMetadata()
		.setBlockSound(BlockSounds.GLASS)
		.setBlockModel(BlockModelModLamp::new)
		.setItemBlock(ItemModBlockPainted::new)
		.build(new BlockModLamp("lamp.idle",UtilIdRegistrar.nextId(),false)
		.withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU));


    public static final Block lampActive = new BlockBuilder(NoNameDyes.MOD_ID)
		.setLuminance(14)
		.setHardness(0.5f)
		.setVisualUpdateOnMetadata()
		.setBlockSound(BlockSounds.GLASS)
		.setBlockModel(BlockModelModLamp::new)
		.build(new BlockModLamp("lamp.active",UtilIdRegistrar.nextId(),true)
		.withTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE));
    public static final Block wool = new BlockBuilder(NoNameDyes.MOD_ID)
		.setHardness(0.8f)
		.setVisualUpdateOnMetadata()
		.setBlockSound(BlockSounds.CLOTH)
		.setFlammability(30,60)
		.setItemBlock(ItemModBlockPainted::new)
		.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/wool/wool_%s",false))
		.build(new BlockModWool("wool",UtilIdRegistrar.nextId())
		.withTags(BlockTags.MINEABLE_BY_SHEARS, BlockTags.NOT_IN_CREATIVE_MENU));

    public static final Block netherRoots = new BlockBuilder(NoNameDyes.MOD_ID)
		.setTextures(NoNameDyes.MOD_ID+":block/nether_roots")
		.setBlockSound(BlockSounds.GRASS)
		.setHardness(0.1f)
		.setResistance(0.1f)
		.setBlockModel(BlockModelNetherRoots::new)
		.build(new BlockNetherRoots("nether.roots", UtilIdRegistrar.nextId(), Material.plant)
		.withTags(BlockTags.MINEABLE_BY_SHEARS,BlockTags.SHEARS_DO_SILK_TOUCH));

    public static final Block mushroomInkCap = new BlockBuilder(NoNameDyes.MOD_ID)
		.setTextures(NoNameDyes.MOD_ID+":block/ink_cap")
		.setBlockSound(BlockSounds.GRASS)
		.setHardness(0.0f)
		.setLuminance(1)
		.setTickOnLoad()
		.setBlockModel(BlockModelCrossedSquares::new)
		.build(new BlockMushroomInkCap("mushroom.ink.cap", UtilIdRegistrar.nextId())
		.withTags(BlockTags.BROKEN_BY_FLUIDS));

    public static final Block ochreBlock = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures(NoNameDyes.MOD_ID+":block/ochre_block")
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
            .setTextures(NoNameDyes.MOD_ID+":block/ore_malachite_stone")
            .build(new BlockOreMalachite("ore.malachite.stone",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteBasalt = malachiteBuilder
            .setTextures(NoNameDyes.MOD_ID+":block/ore_malachite_basalt")
            .build(new BlockOreMalachite("ore.malachite.basalt",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteLimestone = malachiteBuilder
            .setTextures(NoNameDyes.MOD_ID+":block/ore_malachite_limestone")
            .build(new BlockOreMalachite("ore.malachite.limestone",UtilIdRegistrar.nextId()));

    public static final Block oreMalachiteGranite = malachiteBuilder
            .setTextures(NoNameDyes.MOD_ID+":block/ore_malachite_granite")
            .build(new BlockOreMalachite("ore.malachite.granite",UtilIdRegistrar.nextId()));

    public static final Block blockMalachite = malachiteBuilder
            .setTextures(NoNameDyes.MOD_ID+":block/malachite_block")
            .build(new Block("block.malachite",UtilIdRegistrar.nextId(),Material.stone));

    public static final Block flowerIndigo = (new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures(NoNameDyes.MOD_ID+":block/indigo_flower")
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setBlockModel(BlockModelCrossedSquares::new)
            .build(new BlockFlower("flower.indigo", UtilIdRegistrar.nextId())))
            .setKilledByWeather()
            .withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

	@Deprecated
	public static final Block logPalm = new BlockBuilder(NoNameDyes.MOD_ID)
		.setTopBottomTextures(NoNameDyes.MOD_ID+":block/log_palm_top")
		.setSideTextures(NoNameDyes.MOD_ID+":block/log_palm_sides")
		.setBlockSound(BlockSounds.WOOD)
		.setHardness(2.0f)
		.setVisualUpdateOnMetadata()
		.setTickOnLoad()
		.setFlammability(5,5)
		.setBlockModel(BlockModelAxisAligned::new)
		.build(new BlockDeprecated("log.palm", UtilIdRegistrar.nextId(), Material.wood,Block.logPalm.id))
		.withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE,BlockTags.NOT_IN_CREATIVE_MENU);
	//TODO: change
	@Deprecated
	public static final Block logPalmRipe = new BlockBuilder(NoNameDyes.MOD_ID)
		.setItemBlock(b -> new ItemBlockDeprecated(b,Block.logOakMossy))
            .setTopBottomTextures(NoNameDyes.MOD_ID+":block/log_palm_top")
            .setSideTextures(NoNameDyes.MOD_ID+":block/log_palm_ripe_sides")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
			.setTickOnLoad()
            .setFlammability(5,3)
            .build(new BlockDeprecated("log.palm.ripe", UtilIdRegistrar.nextId(), Material.wood,Block.logOakMossy.id))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE,BlockTags.NOT_IN_CREATIVE_MENU,BlockTags.NOT_IN_CREATIVE_MENU);

	@Deprecated
    public static final Block leavesPalm = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.GRASS)
		.setHardness(0.2f)
		.setLightOpacity(1)
		.setVisualUpdateOnMetadata()
		.setTickOnLoad()
		.setFlammability(30,60)
		.build(new BlockDeprecated("leaves.palm", UtilIdRegistrar.nextId(), Material.leaves,Block.leavesPalm.id))
		.withDisabledStats()
		.withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS,BlockTags.NOT_IN_CREATIVE_MENU);

	@Deprecated
	public static final Block saplingPalm = new BlockBuilder(NoNameDyes.MOD_ID)
		.setTextures(NoNameDyes.MOD_ID+":block/sapling_palm")
		.setBlockSound(BlockSounds.GRASS)
		.setBlockModel(BlockModelCrossedSquares::new)
		.setHardness(0.0f)
		.setTickOnLoad()
		.setVisualUpdateOnMetadata()
		.build(new BlockDeprecated("sapling.palm", UtilIdRegistrar.nextId(), Material.plant,Block.saplingPalm.id))
		.withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR, BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block brickMalachite = malachiteBuilder
            .setResistance(10.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/brick_malachite")
            .build(new Block("brick.malachite", UtilIdRegistrar.nextId(), Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block bleacher = new BlockBuilder(NoNameDyes.MOD_ID)
		.setVisualUpdateOnMetadata()
		.setImmovable()
		.setHardness(3.5f)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(BlockModelBleacher::new)
		.build(new BlockBleacher("bleacher", UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block netherrackVile = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTextures(NoNameDyes.MOD_ID+":block/netherrack_vile")
            .setHardness(0.4f)
            .setInfiniburn()
            .build(new Block("netherrack.vile", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block logCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
            .setTopBottomTextures(NoNameDyes.MOD_ID+":block/log_cinnamon_top")
            .setSideTextures(NoNameDyes.MOD_ID+":block/log_cinnamon_sides")
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
            .setFlammability(5,5)
            .setBlockModel(BlockModelAxisAligned::new)
            .build(new BlockLog("log.cinnamon", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);

    //HAS DISABLED STATS
    public static final Block leavesCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.GRASS)
		.setHardness(0.2f)
		.setLightOpacity(1)
		.setVisualUpdateOnMetadata()
		.setTickOnLoad()
		.setBlockColor(b -> new BlockColorCustom(ModColorizers.cinnamon))
		.setFlammability(30,60)
		.setBlockModel(b -> new BlockModelLeaves<>(b,NoNameDyes.MOD_ID+":block/leaves_cinnamon"))
		.build(new BlockLeavesCinnamon("leaves.cinnamon", UtilIdRegistrar.nextId()))
		.withDisabledStats()
		.withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS);

    public static final Block saplingCinnamon = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(BlockModelCrossedSquares::new)
            .setHardness(0.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/sapling_cinnamon")
            .setTickOnLoad()
            .setVisualUpdateOnMetadata()
            .build(new BlockSaplingCinnamon("sapling.cinnamon", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

    public static final Block gallstone = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.5f)
            .setResistance(8.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/gallstone")
            .build(new Block("gallstone", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block vileReactorIdle = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/vile_reactor_0")
            .build(new BlockVileReactor("vile.reactor.idle", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE);

    public static final Block vileReactorActive = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setLuminance(6)
            .setTextures(NoNameDyes.MOD_ID+":block/vile_reactor_1")
            .build(new BlockVileReactor("vile.reactor.active", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block vileReactorVeryActive = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(2.0f)
            .setResistance(15.0f)
            .setLuminance(12)
            .setTextures(NoNameDyes.MOD_ID+":block/vile_reactor_2")
            .build(new BlockVileReactor("vile.reactor.veryactive", UtilIdRegistrar.nextId()))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block logEbony = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.WOOD)
		.setHardness(2.0f)
		.setVisualUpdateOnMetadata()
		.setFlammability(5,5)
		.setBlockModel(b -> new BlockModelAxisAligned<>(b)
			.withTextures(NoNameDyes.MOD_ID+":block/log_ebony_top",
				NoNameDyes.MOD_ID+":block/log_ebony_sides"))
		.build(new BlockLog("log.ebony", UtilIdRegistrar.nextId()))
		.withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);

    //HAS DISABLED STATS
    public static final Block leavesEbony = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.GRASS)
		.setHardness(0.2f)
		.setLightOpacity(1)
		.setVisualUpdateOnMetadata()
		.setTickOnLoad()
		.setBlockColor(b -> new BlockColorCustom(ModColorizers.ebony))
		.setFlammability(30,60)
		.setBlockModel(b -> new BlockModelLeaves<>(b,NoNameDyes.MOD_ID+":block/leaves_ebony"))
		.build(new BlockLeavesEbony("leaves.ebony", UtilIdRegistrar.nextId()))
		.withDisabledStats()
		.withTags(BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS);

    public static final Block saplingEbony = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(BlockModelCrossedSquares::new)
            .setHardness(0.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/sapling_ebony")
            .setTickOnLoad()
            .setVisualUpdateOnMetadata()
            .build(new BlockSaplingEbony("sapling.ebony", UtilIdRegistrar.nextId()))
			.withTags(BlockTags.BROKEN_BY_FLUIDS,BlockTags.PLANTABLE_IN_JAR);

	//TODO: change
	@Deprecated
    public static final Block plaster = new BlockBuilder(NoNameDyes.MOD_ID)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.3f)
            .setTextures(NoNameDyes.MOD_ID+":block/plaster/plaster")
            .build(new BlockDeprecated("plaster", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block plasterMud = new BlockBuilder(NoNameDyes.MOD_ID)
            .setItemBlock(ItemBlockPlasterPainted::new)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(0.3f)
			.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/plaster/plaster_mud_%s",true))
            .build(new BlockCeramicPainted("plaster.mud", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	//TODO: change
	@Deprecated
    public static final Block blockCeramic = new BlockBuilder(NoNameDyes.MOD_ID)

            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/ceramic/ceramic_block")
            .build(new BlockDeprecated("block.ceramic", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id+1))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	//TODO: change
	@Deprecated
    public static final Block tileCeramic = new BlockBuilder(NoNameDyes.MOD_ID)

            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/ceramic/ceramic_tile")
            .build(new BlockDeprecated("tile.ceramic", UtilIdRegistrar.nextId(),Material.stone,UtilIdRegistrar.curr_id+1))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block blockCeramicPainted = new BlockBuilder(NoNameDyes.MOD_ID)
            .setItemBlock(ItemBlockPlasterPainted::new)
            .setBlockSound(BlockSounds.STONE)
            .setHardness(1.0f)
            .setTextures(NoNameDyes.MOD_ID+":block/ceramic/ceramic_block_painted")
		.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/ceramic/ceramic_block_%s",true))
            .build(new BlockCeramicPainted("block.ceramic.painted", UtilIdRegistrar.nextId(),Material.stone))
            .withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

    public static final Block tileCeramicPainted = new BlockBuilder(NoNameDyes.MOD_ID)
		.setItemBlock(ItemBlockPlasterPainted::new)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(1.0f)
		.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/ceramic/ceramic_tile_%s",true))
		.build(new BlockCeramicPainted("tile.ceramic.painted", UtilIdRegistrar.nextId(),Material.stone))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	public static final Block plasterLime = new BlockBuilder(NoNameDyes.MOD_ID)
		.setItemBlock(ItemBlockPlasterPainted::new)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(1.5f)
		.setResistance(10.0f)
		.setBlockModel(b -> new BlockModelModDyed<>(b,NoNameDyes.MOD_ID+":block/plaster/plaster_limestone_%s",true))

		.build(new BlockCeramicPainted("plaster.limestone", UtilIdRegistrar.nextId(),Material.stone))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE,BlockTags.NOT_IN_CREATIVE_MENU);

	public static final Block brickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(3.0f)
		.setResistance(10.0f)
		.setBlockModel(b -> new BlockModelStandard<>(b)
			.withTextures(NoNameDyes.MOD_ID+":block/brick_ochre"))
		.build(new Block("brick.ochre", UtilIdRegistrar.nextId(), Material.stone))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block slabBrickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setItemBlock(ItemBlockSlab::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.setBlockModel(b -> new BlockModelSlab<>(b)
			.withTextures(NoNameDyes.MOD_ID+":block/brick_ochre"))
		.build(new BlockSlab(brickOchre, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block stairsBrickOchre = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(BlockModelStairs::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockStairs(brickOchre, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block slabBrickMalachite = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setItemBlock(ItemBlockSlab::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.setBlockModel(b -> new BlockModelSlab<>(b)
			.withTextures(NoNameDyes.MOD_ID+":block/brick_malachite"))
		.build(new BlockSlab(brickMalachite, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block stairsBrickMalachite = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(BlockModelStairs::new)
		.setVisualUpdateOnMetadata()
		.setUseInternalLight()
		.build(new BlockStairs(brickMalachite, UtilIdRegistrar.nextId()))
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block trapDoorPainted = woodBlockBuilder
		.setBlockModel(BlockModelModTrapDoorPainted::new)
		.setItemBlock(block -> new ItemModBlockPainted(block,true))
		.build(new BlockModTrapDoorPainted("trapdoor.planks.oak.painted",UtilIdRegistrar.nextId(),Material.wood))
		.withTags(BlockTags.MINEABLE_BY_AXE,BlockTags.NOT_IN_CREATIVE_MENU);

	private static final BlockBuilder doorBuilder = new BlockBuilder(NoNameDyes.MOD_ID)
		.setHardness(3.0f)
		.setVisualUpdateOnMetadata()
		.setBlockSound(BlockSounds.WOOD);

	public static final Block doorPlanksOakBottom = doorBuilder
		.setBlockModel(b -> new BlockModelModDoorPainted<>(b,false))
		.build(new BlockModDoorPainted("door.planks.oak.painted",UtilIdRegistrar.nextId(),Material.wood,false))
		.withTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_AXE);

	public static final Block doorPlanksOakTop = doorBuilder
		.setBlockModel(b -> new BlockModelModDoorPainted<>(b,true))
		.build(new BlockModDoorPainted("door.planks.oak.painted",UtilIdRegistrar.nextId(),Material.wood,true))
		.withTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_AXE);

	private static final BlockBuilder eskolaiteBuilder = new BlockBuilder(NoNameDyes.MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(3.0f)
		.setResistance(5.0f)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block oreEskolaiteStone = eskolaiteBuilder
		.setTextures(NoNameDyes.MOD_ID+":block/ore_eskolaite_stone")
		.build(new BlockOreEskolaite("ore.eskolaite.stone",UtilIdRegistrar.nextId()));

	public static final Block oreEskolaiteBasalt = eskolaiteBuilder
		.setTextures(NoNameDyes.MOD_ID+":block/ore_eskolaite_basalt")
		.build(new BlockOreEskolaite("ore.eskolaite.basalt",UtilIdRegistrar.nextId()));

	public static final Block oreEskolaiteLimestone = eskolaiteBuilder
		.setTextures(NoNameDyes.MOD_ID+":block/ore_eskolaite_limestone")
		.build(new BlockOreEskolaite("ore.eskolaite.limestone",UtilIdRegistrar.nextId()));

	public static final Block oreEskolaiteGranite = eskolaiteBuilder
		.setTextures(NoNameDyes.MOD_ID+":block/ore_eskolaite_granite")
		.build(new BlockOreEskolaite("ore.eskolaite.granite",UtilIdRegistrar.nextId()));


	public static void register() {
		Item.itemsList[plaster.id] = new ItemBlockDeprecated(plaster,plasterMud);
		Item.itemsList[blockCeramic.id] = new ItemBlockDeprecated(blockCeramic,blockCeramicPainted);
		Item.itemsList[tileCeramic.id] = new ItemBlockDeprecated(tileCeramic,tileCeramicPainted);
		Item.itemsList[logPalm.id] = new ItemBlockDeprecated(logPalm,Block.logPalm);
		Item.itemsList[leavesPalm.id] = new ItemBlockDeprecated(leavesPalm,Block.leavesPalm);
		Item.itemsList[saplingPalm.id] = new ItemBlockDeprecated(saplingPalm,Block.saplingPalm);
    }
}
