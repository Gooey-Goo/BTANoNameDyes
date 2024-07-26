package goocraft4evr.nonamedyes.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockNetherRoots extends Block {
    public BlockNetherRoots(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int blockId = world.getBlockId(x, y-1, z);
        return world.isAirBlock(x,y,z) && blockId == Block.netherrack.id;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if (world.getBlockId(x, y - 1, z) != Block.netherrack.id) {
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }


    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
        if (side == 1) {
            return true;
        }
        return super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case SILK_TOUCH:
            case PICK_BLOCK: {
                return new ItemStack[]{new ItemStack(this)};
            }
        }
        return null;
    }

}
