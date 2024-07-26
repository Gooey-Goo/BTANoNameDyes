package goocraft4evr.nonamedyes.block;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockCeramicPainted extends Block {

    public BlockCeramicPainted(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause != EnumDropCause.IMPROPER_TOOL) {
            return new ItemStack[]{new ItemStack(this,1,meta)};
        }
        return null;
    }

    public static int getMetadataForColour(int i) {
        return i;
    }
}
