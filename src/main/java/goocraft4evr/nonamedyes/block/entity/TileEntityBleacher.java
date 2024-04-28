package goocraft4evr.nonamedyes.block.entity;

import goocraft4evr.nonamedyes.crafting.ModRecipes;
import goocraft4evr.nonamedyes.crafting.RecipeEntryBleacher;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

import java.util.List;

public class TileEntityBleacher extends TileEntity implements IInventory {
    public final ItemStack[] bleacherItemStacks = new ItemStack[9];
    public boolean hasWaterSource;
    public int maxFuelTime = 0;
    public int currentFuelTime = 0;
    public int currentBleachTime = 0;
    public int maxBleachTime = 200;
    @Override
    public int getSizeInventory() {
        return bleacherItemStacks.length;
    }

    //slot 0 = fuel input
    //slot 1-4 = block input
    //slot 5-8 = block output
    @Override
    public ItemStack getStackInSlot(int i) {
        return bleacherItemStacks[i];
    }

    public void updateWaterSource() {
        int blockId;
        boolean test =
                (((blockId = worldObj.getBlockId(x,y-1,z)) == Block.fluidWaterStill.id ||
                        blockId == Block.fluidWaterFlowing.id) &&
                        worldObj.getBlockMetadata(x,y-1,z) == 0);
        if (test != hasWaterSource) {
            hasWaterSource = test;
            this.onInventoryChanged();
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int numOfItems) {
        if (this.bleacherItemStacks[slot] != null) {
            if (bleacherItemStacks[slot].stackSize <= numOfItems) {
                ItemStack itemstack = bleacherItemStacks[slot];
                bleacherItemStacks[slot] = null;
                return itemstack;
            }
            ItemStack itemstack1 = bleacherItemStacks[slot].splitStack(numOfItems);
            if (bleacherItemStacks[slot].stackSize <= 0) {
                bleacherItemStacks[slot] = null;
            }
            return itemstack1;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        bleacherItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Bleacher";
    }

    @Override
    public void tick() {
        boolean isFuelTimeHigherThan0 = currentFuelTime > 0;
        boolean requiresUpdate = false;
        if (isFuelled()) currentFuelTime--;
        if (!this.worldObj.isClientSide) {
            updateWaterSource();
            if (currentFuelTime == 0 && canBleach()) {
                //NOTE: max fuel time is hardcoded to 8 items
                maxFuelTime = currentFuelTime = getFuelTime();
                if (currentFuelTime > 0) {
                    requiresUpdate = true;
                    if (bleacherItemStacks[0] != null) {
                        bleacherItemStacks[0].stackSize--;
                        if (bleacherItemStacks[0].stackSize <= 0) {
                            bleacherItemStacks[0] = null;
                        }
                    }
                }
            }
            if (isFuelled() && canBleach()) {
                currentBleachTime++;
                if (currentBleachTime == maxBleachTime) {
                    //smelt an item and update the furnace
                    currentBleachTime = 0;
                    bleachItem();
                    requiresUpdate = true;
                }
            } else currentBleachTime = 0;
            if (isFuelTimeHigherThan0 != currentFuelTime > 0) {
                requiresUpdate = true;
                worldObj.markBlockNeedsUpdate(x,y,z);
            }
        }
        //change the inventory if the furnace has been updated
        if (requiresUpdate) {
            this.onInventoryChanged();
        }
    }

    private int getFuelTime() {
        ItemStack itemStack = bleacherItemStacks[0];
        if (itemStack == null) return 0;
        return itemStack.itemID == ModItems.bleachingPowder.id?1600:0;
    }

    private void bleachItem() {
        if (!canBleach()) return;
        int emptyIndex = -1;
        boolean hasOutput = false;
        for (int i=0;i<4;i++) {
            if (bleacherItemStacks[1+i] == null) continue;
			ItemStack itemstack = null;
			for (RecipeEntryBleacher recipeEntryBase : ModRecipes.BLEACHER) {
				if (recipeEntryBase == null || !recipeEntryBase.matches(bleacherItemStacks[1+i])) continue;
				itemstack = recipeEntryBase.getOutput();
			}
            if (itemstack == null) continue;
            for (int j=0;j<4;j++) {
                if (bleacherItemStacks[5+j] == null) {
                    if (emptyIndex == -1) emptyIndex = j;
                    continue;
                }
                if (bleacherItemStacks[5+j].isItemEqual(itemstack) &&
                    (bleacherItemStacks[5+j].stackSize < getInventoryStackLimit() &&
                    bleacherItemStacks[5+j].stackSize < bleacherItemStacks[5+j].getMaxStackSize())) {
                    bleacherItemStacks[5+j].stackSize++;
                    hasOutput = true;
                    break;
                }
            }
            if (emptyIndex != -1) {
                bleacherItemStacks[5+emptyIndex] = itemstack.copy();
                hasOutput = true;
            }
            if (hasOutput) {
                bleacherItemStacks[1+i].stackSize--;
                if (bleacherItemStacks[1+i].stackSize <= 0) bleacherItemStacks[1+i] = null;
                return;
            }
        }
    }

    public boolean isFuelled() {
        return hasWaterSource && currentFuelTime > 0;
    }

    private boolean canBleach() {
        for (int i=0;i<4;i++) {
            if (bleacherItemStacks[1+i] == null) continue;
			ItemStack itemstack = null;
			for (RecipeEntryBleacher recipeEntryBase : ModRecipes.BLEACHER) {
				if (recipeEntryBase == null || !recipeEntryBase.matches(bleacherItemStacks[1+i])) continue;
				itemstack = recipeEntryBase.getOutput();
			}
            if (itemstack == null) continue;
            for (int j=0;j<4;j++) {
                if (bleacherItemStacks[5+j] == null) return true;
                if (!bleacherItemStacks[5+j].isItemEqual(itemstack)) continue;
                if ((bleacherItemStacks[5+j].stackSize < getInventoryStackLimit() &&
                    bleacherItemStacks[5+j].stackSize < bleacherItemStacks[5+j].getMaxStackSize())||
                    bleacherItemStacks[5+j].stackSize < itemstack.getMaxStackSize()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        if (this.worldObj.getBlockTileEntity(this.x, this.y, this.z) != this) {
            return false;
        }
        return entityPlayer.distanceToSqr((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) <= 64.0;
    }

    @Override
    public void sortInventory() {
        //TODO: sort inventory
    }

    public int getFuelRemainingScaled(int i) {
        if (maxFuelTime == 0) return 0;
        return currentFuelTime * i / maxFuelTime;
    }

    public int getBleachProgressScaled(int i) {
        if (maxBleachTime == 0) return 0;
        return currentBleachTime * i / maxBleachTime;
    }
}
