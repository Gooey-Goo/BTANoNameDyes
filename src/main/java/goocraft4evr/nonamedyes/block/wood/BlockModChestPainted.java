package goocraft4evr.nonamedyes.block.wood;

import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.BlockChest;
import net.minecraft.core.block.BlockChestPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import java.util.ArrayList;
import java.util.List;

public class BlockModChestPainted extends BlockChest {
	public static List<IconCoordinate> textures = new ArrayList<>();
    public BlockModChestPainted(String key, int id, Material material) {
        super(key, id, material);
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(this, 1, meta&0xF0)};
    }
/*
	private int indexFromSideAndMeta(Side side, int meta){
		IconCoordinate chestColored;
		int color = meta >> 4;
		int rotation = meta & 0b00000011;
		Type type = BlockChestPainted.getTypeFromMeta(meta);
		if (textures.size() > color){
			chestColored = textures.get(color);
		} else {
			chestColored = textures.get(0);
		}
		int index = 0;
		int sideNum = 0;
		if (side == Side.TOP || side == Side.BOTTOM){
			index = 0;
		} else {
			switch (side){
				case NORTH:
					sideNum = 0;
					break;
				case EAST:
					sideNum = 3;
					break;
				case SOUTH:
					sideNum = 2;
					break;
				case WEST:
					sideNum = 1;
					break;
			}
			int prod = (sideNum + rotation)%4;
			if (prod == 0){
				if (type == Type.SINGLE){
					index = 2;
				} else if (type == Type.LEFT){
					index = 3;
				} else {
					index = 4;
				}
			} else if (prod == 2) {
				if (type == Type.SINGLE){
					index = 1;
				} else if (type == Type.LEFT){
					index = 6;
				} else {
					index = 5;
				}
			}else {
				index = 1;
			}
		}
		return chestColored[index];
	}

 */

    public static int getMetaForDyeColor(int i) {
        return i << 4;
    }
	/*
	static {
		for (String s : ItemModDye.dyeColors){
			IconCoordinate[] chestTextures = new IconCoordinate[7];
			String dirString = s.replace(".", "_");
			chestTextures[0] = TextureRegistry.getTexture("chest/" + dirString + "_chest_top.png");
			chestTextures[1] = TextureRegistry.getTexture("chest/" + dirString + "_chest_side.png");
			chestTextures[2] = TextureRegistry.getTexture("chest/" + dirString + "_chest_front_single.png");
			chestTextures[3] = TextureRegistry.getTexture("chest/" + dirString + "_chest_front_left.png");
			chestTextures[4] = TextureRegistry.getTexture("chest/" + dirString + "_chest_front_right.png");
			chestTextures[5] = TextureRegistry.getTexture("chest/" + dirString + "_chest_back_left.png");
			chestTextures[6] = TextureRegistry.getTexture("chest/" + dirString + "_chest_back_right.png");
			textures.add(chestTextures);
		}
	}
	
	 */
}
