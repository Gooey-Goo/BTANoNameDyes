package goocraft4evr.nonamedyes.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemDoor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemModDoorPainted
	extends ItemDoor {
	public ItemModDoorPainted(String name, int id, Block doorBottom, Block doorTopId) {
		super(name, id, doorBottom, doorTopId);
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int zOffset;
		if (!world.canPlaceInsideBlock(blockX, blockY, blockZ)) {
			blockX += side.getOffsetX();
			blockY += side.getOffsetY();
			blockZ += side.getOffsetZ();
		}
		if (!this.doorBlockBottom.canPlaceBlockAt(world, blockX, blockY, blockZ)) {
			return false;
		}
		Direction dir = entityplayer.getHorizontalPlacementDirection(side).rotate(3);
		int meta = dir.getHorizontalIndex();
		int xOffset = -dir.getOffsetX();
		int isSolidBlockLeft = (world.isBlockNormalCube(blockX - xOffset, blockY, blockZ - (zOffset = -dir.getOffsetZ())) ? 1 : 0) + (world.isBlockNormalCube(blockX - xOffset, blockY + 1, blockZ - zOffset) ? 1 : 0);
		int isSolidBlockRight = (world.isBlockNormalCube(blockX + xOffset, blockY, blockZ + zOffset) ? 1 : 0) + (world.isBlockNormalCube(blockX + xOffset, blockY + 1, blockZ + zOffset) ? 1 : 0);
		boolean isDoorLeft = world.getBlockId(blockX - xOffset, blockY, blockZ - zOffset) == this.doorBlockBottom.id || world.getBlockId(blockX - xOffset, blockY + 1, blockZ - zOffset) == this.doorBlockTop.id;
		boolean isDoorRight = world.getBlockId(blockX + xOffset, blockY, blockZ + zOffset) == this.doorBlockBottom.id || world.getBlockId(blockX + xOffset, blockY + 1, blockZ + zOffset) == this.doorBlockTop.id;
		boolean isMirrored = false;
		if (isDoorLeft && !isDoorRight) {
			isMirrored = true;
		} else if (isSolidBlockRight > isSolidBlockLeft) {
			isMirrored = true;
		}
		if (isMirrored) {
			meta = meta - 1 & 3;
			meta += 4;
			meta |= 8;
		}
		world.editingBlocks = true;
		world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.doorBlockBottom.id, meta |= 15 - itemstack.getMetadata() << 4 & 0xF0);
		world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, this.doorBlockTop.id, meta);
		world.editingBlocks = false;
		world.notifyBlocksOfNeighborChange(blockX, blockY, blockZ, this.doorBlockBottom.id);
		world.notifyBlocksOfNeighborChange(blockX, blockY + 1, blockZ, this.doorBlockTop.id);
		world.playBlockSoundEffect(entityplayer, (float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, this.doorBlockBottom, EnumBlockSoundEffectType.PLACE);
		itemstack.consumeItem(entityplayer);
		return true;
	}

	@Override
	public String getLanguageKey(ItemStack itemstack) {
		return super.getKey() + "." + ItemModDye.dyeColors[itemstack.getMetadata() & 0xF];
	}
}
