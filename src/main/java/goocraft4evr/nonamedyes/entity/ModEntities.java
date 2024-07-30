package goocraft4evr.nonamedyes.entity;

import goocraft4evr.nonamedyes.UtilIdRegistrar;
import goocraft4evr.nonamedyes.client.render.entity.SeaSnailRenderer;
import goocraft4evr.nonamedyes.entity.animal.EntitySeaSnail;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.EntityHelper;

public class ModEntities {

	public static void registerPost() {
		MobInfoRegistry.register(EntitySeaSnail.class, "sea.snail.name", "sea.snail.desc",
			15, 10, new MobInfoRegistry.MobDrop[]{
				new MobInfoRegistry.MobDrop(new ItemStack(ModItems.dye,1,12),
					1.0f, 0, 2)});
	}

	public static void register() {
		EntityHelper.createEntity(EntitySeaSnail.class, UtilIdRegistrar.nextId(), "Seasnail", SeaSnailRenderer::new);
	}
}
