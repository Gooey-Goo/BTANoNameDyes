package goocraft4evr.nonamedyes.modcontainers.terrainapi;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.world.biome.ModBiomes;
import goocraft4evr.nonamedyes.world.worldgen.WorldFeatureNetherRoots;
import goocraft4evr.nonamedyes.world.worldgen.WorldFeatureOchre;
import goocraft4evr.nonamedyes.world.worldgen.WorldFeatureTreePalm;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.generate.feature.WorldFeatureLake;
import useless.terrainapi.api.TerrainAPI;
import useless.terrainapi.generation.Parameters;
import useless.terrainapi.generation.nether.api.ChunkDecoratorNetherAPI;
import useless.terrainapi.generation.overworld.OverworldConfig;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

public class TerrainAPIPlugin implements TerrainAPI {
	public static final OverworldConfig overworldConfig = ChunkDecoratorOverworldAPI.overworldConfig;
    @Override
    public String getModID() {
        return NoNameDyes.MOD_ID;
    }

    @Override
    public void onInitialize() {
		ChunkDecoratorOverworldAPI.oreFeatures.addManagedOreFeature(getModID(), ModBlocks.oreMalachiteStone, 6, 2, 1/4f, true);
		overworldConfig.addTreeDensity(ModBiomes.OVERWORLD_CINNAMON_FOREST, 8);
		overworldConfig.addTreeDensity(ModBiomes.OVERWORLD_EBONY_FOREST, 4);

		overworldConfig.addGrassDensity(ModBiomes.OVERWORLD_CINNAMON_FOREST, 10);
		overworldConfig.addGrassDensity(ModBiomes.OVERWORLD_EBONY_FOREST, 2);
		overworldConfig.addGrassDensity(ModBiomes.OVERWORLD_EBONY_FOREST, 1);

		overworldConfig.addRandomGrassBlock(ModBiomes.OVERWORLD_CINNAMON_FOREST, Block.tallgrassFern);

		ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureFlowers(ModBlocks.mushroomInkCap.id), 48, -1, 1,
			new Biome[]{Biomes.OVERWORLD_GRASSLANDS, Biomes.OVERWORLD_MEADOW, Biomes.OVERWORLD_PLAINS});
		ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureOchre(32), 6, -1, 1,
			new Biome[]{Biomes.OVERWORLD_DESERT, Biomes.OVERWORLD_OUTBACK, Biomes.OVERWORLD_OUTBACK_GRASSY});
		ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureFlowers(ModBlocks.flowerIndigo.id), 2, 1, 1,
			new Biome[]{Biomes.OVERWORLD_RAINFOREST, Biomes.OVERWORLD_SEASONAL_FOREST, ModBiomes.OVERWORLD_CINNAMON_FOREST});

		ChunkDecoratorNetherAPI.randomFeatures.addFeature(ComplexFunctions::getVileNetherrack, null, (Parameters x) -> 1, null, 12, 120/128f);
		ChunkDecoratorNetherAPI.biomeFeatures.addFeature((Parameters x) -> new WorldFeatureNetherRoots(24), null, ComplexFunctions::getNetherRootsDensity, null, 120/128f);
    }
}
