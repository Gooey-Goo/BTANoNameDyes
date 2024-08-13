package goocraft4evr.nonamedyes.client.render.item.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemModelModDoorPainted
	extends ItemModelStandard {
	public static TextureMap TEXTURES = new TextureMap(NoNameDyes.MOD_ID,ItemModDye.NUM_DYES);

	public ItemModelModDoorPainted(Item item) {
		super(item, null);
		for (int i=0;i<ItemModDye.NUM_DYES;i++) {
			TEXTURES.addTexture(String.format(NoNameDyes.MOD_ID+":item/door_%s",ItemModDye.getTextureName(ItemModDye.dyeColors[i])));
		}
	}

	@Override
	@NotNull
	public IconCoordinate getIcon(Entity entity, ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return TEXTURES.getTexture(meta);
	}
}

