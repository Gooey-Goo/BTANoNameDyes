package goocraft4evr.nonamedyes.client.render.item.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.helper.ModColors;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.item.Item;

public class ItemModelPaintedPlaster extends ItemModelStandard {
	public ItemModelPaintedPlaster(Item item) {
		super(item, NoNameDyes.MOD_ID);
	}

	public int getColorFromMeta(int meta) {
		return ModColors.allPlasterColors[meta % ModColors.allPlasterColors.length].value;
	}
}
