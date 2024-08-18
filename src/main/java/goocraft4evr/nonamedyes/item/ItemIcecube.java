package goocraft4evr.nonamedyes.item;

import goocraft4evr.nonamedyes.entity.EntityIcecube;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class ItemIcecube
	extends Item
	implements IDispensable {
	public ItemIcecube(String name, int id) {
		super(name, id);
		this.maxStackSize = 64;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.consumeItem(entityplayer);
		world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
		if (!world.isClientSide) {
			world.entityJoinedWorld(new EntityIcecube(world, entityplayer));
		}
		return itemstack;
	}

	@Override
	public void onDispensed(ItemStack itemStack, World world, double x, double y, double z, int xOffset, int zOffset, Random random) {
		EntityIcecube icecube = new EntityIcecube(world, x, y, z);
		icecube.setHeading(xOffset, 0.1, zOffset, 1.1f, 6.0f);
		world.entityJoinedWorld(icecube);
	}
}
