package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelDoor;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockDoor;
import net.minecraft.core.util.helper.Side;

public class BlockModelModDoorPainted<T extends BlockDoor>
	extends BlockModelDoor<T> {
	public final TextureMap TEXTURES;
	private final boolean isTop;

	public BlockModelModDoorPainted(Block block, boolean isTop) {
		super(block);
		this.isTop = isTop;

		TEXTURES = new TextureMap(NoNameDyes.MOD_ID, ItemModDye.NUM_DYES*2);
		for (int i=0;i< ItemModDye.NUM_DYES;i++) {
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/door/door_planks_oak_%s_bottom",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/door/door_planks_oak_%s_top",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		int sideId = side.getId();
		int color = (data >> 3 & 0x1E);
		if (sideId < 2) {
			return TEXTURES.getTexture(color+1);
		}
		if (this.isTop) {
			return TEXTURES.getTexture(color+1);
		}
		return TEXTURES.getTexture(color);
	}
}

