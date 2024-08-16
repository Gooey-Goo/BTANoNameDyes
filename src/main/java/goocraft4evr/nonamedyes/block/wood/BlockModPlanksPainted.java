package goocraft4evr.nonamedyes.block.wood;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

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
}
