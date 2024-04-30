package goocraft4evr.nonamedyes.mixin.client.gui.guidebook;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.client.gui.guidebook.bleaching.BleachingSection;
import net.minecraft.client.gui.guidebook.GuidebookSections;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value= GuidebookSections.class, remap = false)
public class GuidebookSectionsMixin {
	@Inject(method = "init",at=@At("TAIL"))
	private static void init(CallbackInfo ci) {
		GuidebookSections.register(new BleachingSection("guidebook.section.bleacher", new ItemStack(ModBlocks.bleacher), 0xe0e000, 0xffffff));
	}
}
