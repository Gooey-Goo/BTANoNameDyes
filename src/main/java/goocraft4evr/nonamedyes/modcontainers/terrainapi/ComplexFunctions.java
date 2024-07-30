package goocraft4evr.nonamedyes.modcontainers.terrainapi;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.world.biome.ModBiomes;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import useless.terrainapi.generation.Parameters;

import java.util.Random;

public class ComplexFunctions {
    public static WorldFeature getVileNetherrack(Parameters parameters){
        return new WorldFeatureOre(ModBlocks.netherrackVile.id, 16 + parameters.random.nextInt(16), false);
    }
    public static Integer getNetherRootsDensity(Parameters parameters){
        return (8+ parameters.random.nextInt(4))<<2;
    }
}
