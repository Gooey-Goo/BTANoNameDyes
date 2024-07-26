package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.util.helper.Side;

public class BlockModelStairsModDyed<T extends BlockStairs> extends BlockModelStairs<T> {
	private final TextureMap TEXTURES;

	public BlockModelStairsModDyed(Block block, String textureNamespace) {
		super(block);
		TEXTURES = new TextureMap(NoNameDyes.MOD_ID,ItemModDye.NUM_DYES);
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(textureNamespace,ItemModDye.dyeColors[i]));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return TEXTURES.getTexture(data>>4);
	}
}
