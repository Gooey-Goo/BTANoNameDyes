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
import net.minecraft.core.world.WorldSource;

import java.util.Random;

public class BlockModLamp extends Block {
    boolean isActive;

    public BlockModLamp(String key, int id, boolean isActivated) {
        super(key, id, Material.stone);
        this.isActive = isActivated;
        this.setTicking(true);
    }

    @Override
    public int tickRate() {
        return 2;
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        world.scheduleBlockUpdate(i, j, k, this.id, this.tickRate());
    }

    public static int getMetadataForColour(int i) {
        return i;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        boolean isPoweredByBlock = world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z);
        if (this.isActive) {
            if (!isPoweredByBlock) {
                world.setBlockAndMetadataWithNotify(x, y, z, ModBlocks.lampIdle.id, world.getBlockMetadata(x, y, z));
            }
        } else if (isPoweredByBlock) {
            world.setBlockAndMetadataWithNotify(x, y, z, ModBlocks.lampActive.id, world.getBlockMetadata(x, y, z));
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(ModBlocks.lampIdle, 1, meta)};
    }
}
