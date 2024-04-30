package goocraft4evr.nonamedyes.entity;

import goocraft4evr.nonamedyes.client.render.entity.SeaSnailRenderer;
import goocraft4evr.nonamedyes.entity.animal.EntitySeaSnail;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.EntityHelper;

public class ModEntities {

	public static void registerClientside() {
		EntityHelper.Client.assignEntityRenderer(EntitySeaSnail.class, new SeaSnailRenderer());
	}

	public static void register() {
		EntityHelper.Core.createEntity(EntitySeaSnail.class, 200, "Seasnail");

		MobInfoRegistry.register(EntitySeaSnail.class, "sea.snail.name", "sea.snail.desc",
			15, 10, new MobInfoRegistry.MobDrop[]{
				new MobInfoRegistry.MobDrop(new ItemStack(ModItems.dye,1,12),
					1.0f, 0, 2)});
	}
}
