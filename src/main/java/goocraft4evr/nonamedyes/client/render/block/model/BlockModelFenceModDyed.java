package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelFence;
import net.minecraft.client.render.block.model.BlockModelPlanksPainted;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFence;
import net.minecraft.core.util.helper.Side;

public class BlockModelFenceModDyed<T extends BlockFence>
	extends BlockModelFence<T> {
	private final TextureMap TEXTURES;
	public BlockModelFenceModDyed(Block block, String textureNamespace) {
		super(block);
		TEXTURES = new TextureMap(NoNameDyes.MOD_ID, ItemModDye.NUM_DYES);
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(textureNamespace,ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int meta) {
		return TEXTURES.getTexture(meta);
	}
}

