package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelFenceGate;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFenceGate;
import net.minecraft.core.util.helper.Side;

public class BlockModelFenceGateModDyed<T extends BlockFenceGate> extends BlockModelFenceGate<T> {
	private final TextureMap TEXTURES;

	public BlockModelFenceGateModDyed(Block block, String textureNamespace) {
		super(block);
		TEXTURES = new TextureMap(NoNameDyes.MOD_ID, ItemModDye.NUM_DYES);
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(textureNamespace,ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return TEXTURES.getTexture(data>>4);
	}
}
