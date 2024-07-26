package goocraft4evr.nonamedyes.block;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockModWool extends Block {
    private static TextureMap textures;

    public BlockModWool(String key, int id) {
        super(key, id, Material.cloth);
		/*
        textures = new TextureMap(NoNameDyes.MOD_ID,ItemModDye.dyeColors.length);
        for (int i = 0; i< textures.length(); i++) {
            //generate textures for the remaining dyes to ensure they're all contiguous
            textures.addBlockTexture( "wool/"+ItemModDye.getTextureName(ItemModDye.dyeColors[i])+"_wool.png");
        }

		 */
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(this, 1, meta)};
    }

    public static int getMetadataForColour(int i) {
        return i;
    }
}
