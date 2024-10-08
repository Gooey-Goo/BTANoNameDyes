package goocraft4evr.nonamedyes.block;

import goocraft4evr.nonamedyes.ModGuis;
import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import goocraft4evr.nonamedyes.client.gui.GuiBleacher;
import goocraft4evr.nonamedyes.mixin.server.entity.player.EntityPlayerMPAccessor;
import goocraft4evr.nonamedyes.player.inventory.ContainerBleacher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.server.entity.player.EntityPlayerMP;

import java.util.Objects;
import java.util.Random;

public class BlockBleacher extends BlockTileEntity {

    protected Random bleacherRand = new Random();

    public BlockBleacher(String key, int id) {
        super(key, id, Material.stone);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        TileEntityBleacher tileEntityBleacher = (TileEntityBleacher) world.getBlockTileEntity(x,y,z);
        if (tileEntityBleacher.isFuelled()) {
            double particleX = (double)x + rand.nextFloat();
			double particleY = (double)y + 1.0f;
			double particleZ = (double)z + rand.nextFloat();
			double motionX = rand.nextFloat()*0.01f-0.02f;
			double motionY = rand.nextFloat()*0.04f;
			double motionZ = rand.nextFloat()*0.01f-0.02f;
            world.spawnParticle("bubble",
                    particleX, particleY, particleZ,
                    motionX, motionY, motionZ,0);
        }
    }

    @Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xPlaced, double yPlaced) {
        if (!world.isClientSide) {
            TileEntityBleacher tileEntityBleacher = (TileEntityBleacher) world.getBlockTileEntity(x,y,z);

			ItemStack item = player.getCurrentEquippedItem();
			int water = getWaterAmountFromItem(item);
			if (water > 0) {
				int minWater = getWaterMinAmountFromItem(item);
				int waterUsed = tileEntityBleacher.addWater(water,minWater);
				if (waterUsed > 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem,updateItemStackWithWaterAdded(player.getCurrentEquippedItem(),waterUsed));
					world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f, "liquid.splash", 0.5f, 1.0f);
					world.markBlockDirty(x,y,z);
					return true;
				}
			}
            if ((player instanceof EntityPlayerSP)) displayGUIBleacherClient((EntityPlayerSP) player, tileEntityBleacher);
            else displayGUIBleacherServer((EntityPlayerMP) player, tileEntityBleacher);
        }
        return true;
    }

	/*
	The following three methods are for modders, so they can add more items to
	increment the bleacher's water level. I would recommend injecting at the
	return value, then adding your own behaviour if the current return value is 0
	(or if the returned ItemStack is equal to the parameter ItemStack).

	getWaterAmountFromItem() returns how much the given item should increase the water level by.
	getWaterMinAmountFromItem() returns the minimum amount of water the given item needs to increase the
	water level by. e.g. if the minimum is 100, the water level will remain the same if the item contributes
	99 or less to the water level.
	updateItemStackWithWaterAdded() returns the item the player should be holding given what item they
	used to add water and how much water was added. can also be used to play sound effects and other misc behaviour.
	*/
	private int getWaterAmountFromItem(ItemStack item) {
		if (item == null) return 0;
		if (item.getItem() == ItemBucket.bucketWater) return 1600;
		return 0;
	}

	private int getWaterMinAmountFromItem(ItemStack item) {
		return 0;
	}

	private ItemStack updateItemStackWithWaterAdded(ItemStack item, int amountAdded) {
		assert item != null;
		if (item.getItem() == ItemBucket.bucketWater) {
			return new ItemStack(ItemBucket.bucket);
		}
		return item;
	}

	public static void displayGUIBleacherClient(EntityPlayerSP player, TileEntityBleacher tileentitybleacher) {
        Minecraft.getMinecraft(Minecraft.class).displayGuiScreen(new GuiBleacher(player.inventory, tileentitybleacher));
    }

    public static void displayGUIBleacherServer(EntityPlayerMP player, TileEntityBleacher tileentitybleacher) {
        ((EntityPlayerMPAccessor)player).invokeGetNextWindowId();
        player.playerNetServerHandler.sendPacket(new Packet100OpenWindow(
                ((EntityPlayerMPAccessor)player).getCurrentWindowId(),
                ModGuis.bleacherId,
                tileentitybleacher.getInvName(),
                tileentitybleacher.getSizeInventory()));
        player.craftingInventory = new ContainerBleacher(player.inventory, tileentitybleacher);
        player.craftingInventory.windowId = ((EntityPlayerMPAccessor)player).getCurrentWindowId();
        player.craftingInventory.onContainerInit(player);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityBleacher();
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case PICK_BLOCK:
            case PROPER_TOOL:
            case SILK_TOUCH: {
                return new ItemStack[]{new ItemStack(ModBlocks.bleacher)};
            }
        }
        return null;
    }

    @Override
    public void onBlockRemoved(World world, int x, int y, int z, int data) {
        TileEntityBleacher tileentitybleacher = (TileEntityBleacher)world.getBlockTileEntity(x, y, z);
        for (int l = 0; l < tileentitybleacher.getSizeInventory(); ++l) {
            ItemStack itemstack = tileentitybleacher.getStackInSlot(l);
            if (itemstack == null) continue;
            float f = this.bleacherRand.nextFloat() * 0.8f + 0.1f;
            float f1 = this.bleacherRand.nextFloat() * 0.8f + 0.1f;
            float f2 = this.bleacherRand.nextFloat() * 0.8f + 0.1f;
            while (itemstack.stackSize > 0) {
                int i1 = this.bleacherRand.nextInt(21) + 10;
                if (i1 > itemstack.stackSize) {
                    i1 = itemstack.stackSize;
                }
                itemstack.stackSize -= i1;
                EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                float f3 = 0.05f;
                entityitem.xd = (float)this.bleacherRand.nextGaussian() * f3;
                entityitem.yd = (float)this.bleacherRand.nextGaussian() * f3 + 0.2f;
                entityitem.zd = (float)this.bleacherRand.nextGaussian() * f3;
                world.entityJoinedWorld(entityitem);
            }
        }
        super.onBlockRemoved(world, x, y, z, data);
    }
}
