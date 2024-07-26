package goocraft4evr.nonamedyes.client.render.block.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLamp;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelModLamp<T extends BlockLamp>
	extends BlockModelStandard<T> {
	private static final IconCoordinate[] texCoordsInactive = new IconCoordinate[16];
	private static final IconCoordinate[] texCoordsActive = new IconCoordinate[16];

	public BlockModelModLamp(Block block) {
		super(block);
	}

	@Override
	public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		int data = blockAccess.getBlockMetadata(x, y, z);
		if (((BlockLamp)this.block).isActive) {
			return texCoordsActive[data & 0xF];
		}
		return texCoordsInactive[data & 0xF];
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return texCoordsActive[data & 0xF];
	}

	static {
		for (int i = 0; i < 16; ++i) {
			texCoordsInactive[i] = TextureRegistry.getTexture("minecraft:block/lamp_" + ItemDye.dyeColors[15 - i] + "_idle");
			texCoordsActive[i] = TextureRegistry.getTexture("minecraft:block/lamp_" + ItemDye.dyeColors[15 - i] + "_active");
		}
	}
}

