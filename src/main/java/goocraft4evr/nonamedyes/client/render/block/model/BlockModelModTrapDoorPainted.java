package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelTrapDoor;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTrapDoor;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.helper.Sides;

public class BlockModelModTrapDoorPainted<T extends Block>
	extends BlockModelTrapDoor<T> {
	protected static TextureMap TEXTURES;

	public BlockModelModTrapDoorPainted(Block block) {
		super(block);

		TEXTURES = new TextureMap(NoNameDyes.MOD_ID, ItemModDye.NUM_DYES*2);
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/trapdoor/trapdoor_planks_oak_%s_top",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/trapdoor/trapdoor_planks_oak_%s_side",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		int color = data >> 4 & 0x0F;
		int orientation = data & 3;

		if (BlockTrapDoor.isTrapdoorOpen(data)) {
			int index = Sides.orientationLookUpTrapdoorOpen[6 * orientation + side.getId()];
			if (index < 2) {
				return TEXTURES.getTexture(color*2);
			}
			return TEXTURES.getTexture(color*2+1);
		}
		if (side.getAxis() == Axis.Y) {
			return TEXTURES.getTexture(color*2);
		}
		return TEXTURES.getTexture(color*2+1);
	}
}

