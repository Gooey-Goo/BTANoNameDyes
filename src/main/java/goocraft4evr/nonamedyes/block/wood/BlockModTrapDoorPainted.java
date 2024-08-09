package goocraft4evr.nonamedyes.block.wood;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.core.block.BlockTrapDoor;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockModTrapDoorPainted
	extends BlockTrapDoor {
	public BlockModTrapDoorPainted(String key, int id, Material material) {
		super(key, id, material, false);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		int meta = world.getBlockMetadata(x, y, z);
		NoNameDyes.LOGGER.info("meta is "+meta);
		int colorBits = meta & 0xF0;
		meta = this.getMetaForDirection(entity.getHorizontalPlacementDirection(side, PlacementMode.SIDE));
		if (entity.getVerticalPlacementDirection(side, sideHeight) == Direction.UP) {
			meta |= 8;
		}
		world.setBlockMetadata(x, y, z, meta |= colorBits);
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(ModBlocks.trapDoorPainted, 1, (meta >> 4 & 0xF) << 4)};
	}
}
