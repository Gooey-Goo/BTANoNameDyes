package goocraft4evr.nonamedyes.player.inventory;

import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import goocraft4evr.nonamedyes.player.inventory.slot.SlotBleaching;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

import java.util.List;

import static goocraft4evr.nonamedyes.NoNameDyes.LOGGER;

public class ContainerBleacher extends Container {
    public TileEntityBleacher tileEntity;
    private boolean hasWaterSource = false;
    private int currentBleachTime = 0;
    private int currentFuelTime = 0;
    private int itemFuelTime = 0;
    private int itemBleachTime = 0;

    public ContainerBleacher(InventoryPlayer inventoryplayer, TileEntityBleacher tileentitybleacher) {
        tileEntity = tileentitybleacher;
        //fuel slot (0)
        this.addSlot(new Slot(tileEntity, 0, 20, 35));
        //input slots (1-4)
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new Slot(tileentitybleacher, i*2+j+1, 50+j * 18, 17+i * 18));
            }
        }
        //output slots (5-8)
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new SlotBleaching(tileentitybleacher, i*2+j+5, 122+j * 18, 17+i * 18));
            }
        }
		//player slots (9-35)
		for (int i = 0; i < 3; ++i) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 66 + i * 18));
			}
		}
		//player slots (0-8)
		for (int j = 0; j < 9; ++j) {
			this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 124));
		}
    }

    @Override
    public void updateInventory() {
        super.updateInventory();
        for (ICrafting crafter : crafters) {
            if (hasWaterSource != tileEntity.hasWaterSource) {
				crafter.updateCraftingInventoryInfo(this, 0, tileEntity.hasWaterSource?1:0);
            }
            if (currentBleachTime != tileEntity.currentBleachTime) {
				crafter.updateCraftingInventoryInfo(this, 1, tileEntity.currentBleachTime);
            }
            if (currentFuelTime != tileEntity.currentFuelTime) {
				crafter.updateCraftingInventoryInfo(this, 2, tileEntity.currentFuelTime);
            }
            if (itemBleachTime != tileEntity.maxBleachTime) {
				crafter.updateCraftingInventoryInfo(this, 3, tileEntity.maxBleachTime);
            }
            if (itemFuelTime == tileEntity.maxFuelTime) continue;
			crafter.updateCraftingInventoryInfo(this, 4, tileEntity.maxFuelTime);
        }
        hasWaterSource = tileEntity.hasWaterSource;
        currentBleachTime = tileEntity.currentBleachTime;
        currentFuelTime = tileEntity.currentFuelTime;
        itemBleachTime = tileEntity.maxBleachTime;
        itemFuelTime = tileEntity.maxFuelTime;
    }

    @Override
    public void updateClientProgressBar(int id, int value) {
        if (id == 0) {
            this.tileEntity.hasWaterSource = value==1;
        }
        if (id == 1) {
            this.tileEntity.currentBleachTime = value;
        }
        if (id == 2) {
            this.tileEntity.currentFuelTime = value;
        }
        if (id == 3) {
            this.tileEntity.maxBleachTime = value;
        }
        if (id == 4) {
            this.tileEntity.maxFuelTime = value;
        }
    }

    @Override
    public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
		if (slot.id >= 0 && slot.id <= 9) {
			return this.getSlots(slot.id, 1, false);
		}
		if (action == InventoryAction.MOVE_ALL) {
			if (slot.id >= 9 && slot.id <= 36) {
				return this.getSlots(9, 27, false);
			}
			if (slot.id >= 36 && slot.id <= 44) {
				return this.getSlots(36, 9, false);
			}
		}
		if (slot.id >= 9 && slot.id <= 43) {
			return this.getSlots(9, 36, false);
		}
		return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
		if (slot.id >= 9 && slot.id <= 44) {
			if (action != InventoryAction.MOVE_ALL) {
				if (target > 0 && target < 9) return this.getSlots(target-1, 1, false);
			}
			if (slot.id <= 35) {
				return this.getSlots(36, 9, false);
			}
			return this.getSlots(9, 27, false);
		}
		if (slot.id >= 0) {
			if (slot.id >= 5) {
				return this.getSlots(9, 36, true);
			}
			return this.getSlots(9, 36, false);
		}
		return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        return this.tileEntity.canInteractWith(entityplayer);
    }
}
