package goocraft4evr.nonamedyes.block.wood;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockModSlabPainted extends BlockSlab {
    public BlockModSlabPainted(Block modelBlock, int id) {
        super(modelBlock, id);
    }

    public static int getMetadataForColour(int i) {
        return i << 4;
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
        int meta = entity.getVerticalPlacementDirection(side, sideHeight) == Direction.UP ? 2 : 0;
        world.setBlockMetadataWithNotify(x, y, z, meta | world.getBlockMetadata(x, y, z));
    }
}
