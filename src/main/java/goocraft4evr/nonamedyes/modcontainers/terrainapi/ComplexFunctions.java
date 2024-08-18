package goocraft4evr.nonamedyes.modcontainers.terrainapi;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.world.biome.ModBiomes;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import useless.terrainapi.generation.Parameters;
import useless.terrainapi.generation.overworld.OverworldFunctions;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

import java.util.Random;

public class ComplexFunctions {
    public static WorldFeature getVileNetherrack(Parameters parameters){
        return new WorldFeatureOre(ModBlocks.netherrackVile.id, 16 + parameters.random.nextInt(16), false);
    }

    public static Integer getNetherRootsDensity(Parameters parameters){
        return (8+ parameters.random.nextInt(4))<<2;
    }

	public static void addBiomeSpecificManagedOreFeature(String modID, Block block, int defaultClusterSize, int defaultChances, float defaultRange, boolean hasStoneStates, Biome[] whitelist) {
		ChunkDecoratorOverworldAPI.oreFeatures.config.setOreValues(modID, block, defaultClusterSize, defaultChances, defaultRange);
		String currentBlock = block.getKey();
		ChunkDecoratorOverworldAPI.oreFeatures.addFeature((x) -> new WorldFeatureOre(block.id, ChunkDecoratorOverworldAPI.oreFeatures.config.clusterSize.get(currentBlock), hasStoneStates), null, OverworldFunctions::getStandardOreBiomesDensity, new Object[]{ChunkDecoratorOverworldAPI.oreFeatures.config.chancesPerChunk.get(currentBlock), whitelist}, ChunkDecoratorOverworldAPI.oreFeatures.config.verticalStartingRange.get(currentBlock), ChunkDecoratorOverworldAPI.oreFeatures.config.verticalEndingRange.get(currentBlock));
	}
}
