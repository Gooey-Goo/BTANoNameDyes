package goocraft4evr.nonamedyes.block.wood;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockModPlanksPainted extends Block {
    public BlockModPlanksPainted(String key, int id) {
        super(key, id, Material.wood);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(this, 1, meta)};
    }

    public static int getMetadataForColour(int i) {
        return i;
    }
	/*
	public static int getIndexFromMeta(int meta, boolean isUpper){
		if (isUpper){
			meta = meta >> 4;
		}
		if (textures.size() > meta){
			return textures.get(meta);
		}
		return textures.get(0);
	}
	static {
		for (String s : ItemModDye.dyeColors){
			textures.add(TextureHelper.getOrCreateBlockTextureIndex(NoNameDyes.MOD_ID, "plank/" + s.replace(".", "_") + "_plank.png"));
		}
	}
	
	 */
}
