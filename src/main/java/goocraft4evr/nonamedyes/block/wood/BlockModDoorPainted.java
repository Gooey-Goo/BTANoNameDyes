package goocraft4evr.nonamedyes.block.wood;

import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.block.BlockDoor;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockModDoorPainted
	extends BlockDoor {
	public BlockModDoorPainted(String key, int id, Material material, boolean isTop) {
		super(key, id, material, isTop);
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(ModItems.doorOakPainted, 1, 15 - (meta >> 4 & 0xF))};
	}
}

