package goocraft4evr.nonamedyes.mixin.core.player.inventory;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value= ContainerPlayerCreative.class, remap=false)
public abstract class ContainerPlayerCreativeMixin {
    @Inject(method="<clinit>",at=@At("TAIL"))
    private static void inject(CallbackInfo ci) {
        ContainerPlayerCreative.creativeItems.remove(new ItemStack(ModItems.dye,1,0));
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModItems.dye, 1, i));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.planksOakPainted, 1, i));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.fencePlanksOakPainted, 1, i));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.fencegatePlanksOakPainted, 1, i<<4));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.wool, 1, i));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.slabPlanksOakPainted, 1, i<<4));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.stairsPlanksOakPainted, 1, i<<4));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.chestPlanksOakPainted, 1, i<<4));
        }
        for (int i = 0; i< ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.lampIdle, 1, i));
        }
        for (int i = 0; i<16+ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.plasterMud, 1, i));
        }
		for (int i = 0; i<16+ItemModDye.dyeColors.length; i++) {
			ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.plasterLime, 1, i));
		}
        for (int i = 0; i<16+ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.blockCeramicPainted, 1, i));
        }
        for (int i = 0; i<16+ItemModDye.dyeColors.length; i++) {
            ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.tileCeramicPainted, 1, i));
        }
		for (int i = 0; i<ItemModDye.dyeColors.length; i++) {
			ContainerPlayerCreative.creativeItems.add(new ItemStack(ModItems.doorOakPainted, 1, i));
		}
		for (int i = 0; i<ItemModDye.dyeColors.length; i++) {
			ContainerPlayerCreative.creativeItems.add(new ItemStack(ModBlocks.trapDoorPainted, 1, i<<4));
		}
        ContainerPlayerCreative.creativeItemsCount += (ItemModDye.dyeColors.length)*14+64;
    }
}
