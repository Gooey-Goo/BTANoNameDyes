package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.util.helper.Side;

public class BlockModelModDyed<T extends Block> extends BlockModelStandard<T> {
	private final TextureMap TEXTURES;

	public BlockModelModDyed(Block block, String textureNamespace, boolean hasVanilla) {
		super(block);
		TEXTURES = new TextureMap(NoNameDyes.MOD_ID,ItemModDye.NUM_DYES+(hasVanilla?16:0));
		if (hasVanilla) {
			for (int i = 0; i < 16; ++i) {
				TEXTURES.addTexture(String.format(textureNamespace, ItemModDye.getTextureName(ItemDye.dyeColors[15^i])));
			}
		}
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(textureNamespace,ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return TEXTURES.getTexture(data);
	}
}
