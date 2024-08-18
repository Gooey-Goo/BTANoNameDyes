package goocraft4evr.nonamedyes.client.render.item.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelModDye
	extends ItemModelStandard {
	public static IconCoordinate[] dyeIcons = new IconCoordinate[16];

	public ItemModelModDye(Item item) {
		super(item, NoNameDyes.MOD_ID);
	}

	@Override
	@NotNull
	public IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return dyeIcons[MathHelper.clamp(meta, 0, ItemModDye.NUM_DYES-1)];
	}

	static {
		for (int i = 0; i < ItemModDye.NUM_DYES; ++i) {
			dyeIcons[i] = TextureRegistry.getTexture(NoNameDyes.MOD_ID+":item/"+ItemModDye.getTextureName(ItemModDye.dyeColors[i])+"_dye");
		}
	}
}
