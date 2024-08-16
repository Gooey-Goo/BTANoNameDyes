package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.block.model.BlockModelChest;
import net.minecraft.client.render.block.model.BlockModelChestPainted;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockChest;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelChestModDyed<T extends Block>
	extends BlockModelChest<T> {
	private final TextureMap TEXTURES;

	public BlockModelChestModDyed(Block block) {
		super(block, "minecraft:block/chest_planks_oak");
		//textures are in a 1D array instead of 2D, but otherwise the same as vanilla.
		TEXTURES = new TextureMap(NoNameDyes.MOD_ID,ItemModDye.NUM_DYES*7);
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_front",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_left_front",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_right_front",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_left_back",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_right_back",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_side",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":block/chest/chest_planks_oak_painted_%s_top",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		int color = (meta >> 4)*7;
		if (side == Side.TOP || side == Side.BOTTOM) {
			return TEXTURES.getTexture(color+6);
		}
		Side facing = BlockChest.getDirectionFromMeta(meta).getSide();
		BlockChest.Type type = BlockChest.getTypeFromMeta(meta);
		if (type == BlockChest.Type.SINGLE && side == facing) {
			return TEXTURES.getTexture(color);
		}
		if (type == BlockChest.Type.LEFT) {
			if (side == facing) {
				return TEXTURES.getTexture(color+1);
			}
			if (side == facing.getOpposite()) {
				return TEXTURES.getTexture(color+4);
			}
		}
		if (type == BlockChest.Type.RIGHT) {
			if (side == facing) {
				return TEXTURES.getTexture(color+2);
			}
			if (side == facing.getOpposite()) {
				return TEXTURES.getTexture(color+3);
			}
		}
		if (side.getAxis() != Axis.Y) {
			return TEXTURES.getTexture(color+5);
		}
		return TEXTURES.getTexture(color+6);
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		int color = (data >> 4)*7;
		if (side == Side.SOUTH) {
			return TEXTURES.getTexture(color);
		}
		if (side.isHorizontal()) {
			return TEXTURES.getTexture(color+5);
		}
		return TEXTURES.getTexture(color+6);
	}
}
