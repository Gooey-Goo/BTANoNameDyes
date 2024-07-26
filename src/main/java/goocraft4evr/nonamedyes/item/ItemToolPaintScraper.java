package goocraft4evr.nonamedyes.item;

import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemToolPaintScraper extends Item {
	public ItemToolPaintScraper(String name, int id) {
		super(name, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.iron.getDurability());
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int id = world.getBlockId(blockX, blockY, blockZ);
		//Too lazy to make a tag, ping me if you want one.
		int newId;
		if (id == ModBlocks.plasterMud.id) {
			newId = Block.mud.id;
		} else if (id == ModBlocks.plasterLime.id) {
			newId = Block.cobbleLimestone.id;
		} else {
			return false;
		}
		if (world.isClientSide) {
			return true;
		}
		int meta = world.getBlockMetadata(blockX,blockY,blockZ);
		world.dropItem(blockX, blockY, blockZ, new ItemStack(ModItems.paintedPlaster,1,meta));
		world.setBlockWithNotify(blockX,blockY,blockZ,newId);
		itemstack.damageItem(1, entityplayer);
		return true;
	}
}
