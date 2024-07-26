package goocraft4evr.nonamedyes.block.wood;

import net.minecraft.core.block.BlockFenceGate;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockModFenceGatePainted extends BlockFenceGate {

    public BlockModFenceGatePainted(String key, int id) {
        super(key, id);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(this, 1, meta&0xF0)};
    }

    public static int getMetadataForColour(int i) {
        return i << 4;
    }
}
