package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.BlockModLamp;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelModLamp<T extends BlockModLamp>
	extends BlockModelStandard<T> {
	private static final IconCoordinate[] texCoordsInactive = new IconCoordinate[16];
	private static final IconCoordinate[] texCoordsActive = new IconCoordinate[16];

	public BlockModelModLamp(Block block) {
		super(block);
	}

	@Override
	public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		int data = blockAccess.getBlockMetadata(x, y, z);
		if (block.isActive) {
			return texCoordsActive[data & 0xF];
		}
		return texCoordsInactive[data & 0xF];
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return texCoordsActive[data & 0xF];
	}

	static {
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			texCoordsInactive[i] = TextureRegistry.getTexture(String.format(NoNameDyes.MOD_ID+":block/lamp/lamp_%s_idle",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			texCoordsActive[i] = TextureRegistry.getTexture(String.format(NoNameDyes.MOD_ID+":block/lamp/lamp_%s_active",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}
}

