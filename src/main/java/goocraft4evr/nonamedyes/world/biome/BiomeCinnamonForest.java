package goocraft4evr.nonamedyes.world.biome;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.world.worldgen.WorldFeatureTreeCinnamon;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancyRainforest;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreePalm;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeShrub;

import java.util.Random;

public class BiomeCinnamonForest extends Biome {
	public BiomeCinnamonForest(String key) {
		super(key);
	}

	@Override
    public WorldFeature getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(12) == 0) {
			return new WorldFeatureTreePalm(Block.logPalm, Block.leavesPalm,false,false);
		}
        if (random.nextInt(4) == 0) {
            return new WorldFeatureTreeShrub(Block.leavesShrub.id, Block.logOak.id);
        }
        return new WorldFeatureTreeCinnamon(ModBlocks.leavesCinnamon.id, ModBlocks.logCinnamon.id);
    }
}
