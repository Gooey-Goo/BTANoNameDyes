package goocraft4evr.nonamedyes.item;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.UtilIdRegistrar;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.tag.ItemTags;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {

    public static Item dye = ItemModDye.createDyes("dye", UtilIdRegistrar.nextId()).withTags(ItemTags.NOT_IN_CREATIVE_MENU);
    public static Item bleachingPowder = new ItemBuilder(NoNameDyes.MOD_ID)
			.setIcon(String.format("%s:item/bleaching_powder",NoNameDyes.MOD_ID))
			.build(new Item("bleaching.powder",UtilIdRegistrar.nextId()));

    public static Item foodSnickerdoodle = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/snickerdoodle",NoNameDyes.MOD_ID))
		.build(new ItemFood("snickerdoodle", UtilIdRegistrar.nextId(), 1, 0, false,16));

    public static Item vileShard = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/vile_shard",NoNameDyes.MOD_ID))
		.build(new Item("vileshard",UtilIdRegistrar.nextId()));

    public static Item ceramic = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/ceramic",NoNameDyes.MOD_ID))
		.build(new Item("ceramic",UtilIdRegistrar.nextId()));

	public static Item limePowder = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/lime_powder",NoNameDyes.MOD_ID))
		.build(new Item("lime.powder",UtilIdRegistrar.nextId()));

	public static Item wetPlaster = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/wet_plaster",NoNameDyes.MOD_ID))
		.build(new Item("plaster.wet",UtilIdRegistrar.nextId()));

	public static Item paintedPlaster = new ItemBuilder(NoNameDyes.MOD_ID)
		.build(new ItemPaintedPlaster("plaster.painted",UtilIdRegistrar.nextId()));

	public static Item paintScraper = new ItemBuilder(NoNameDyes.MOD_ID)
		.build(new ItemToolPaintScraper("tool.paintscraper",UtilIdRegistrar.nextId()));

	public static Item brickOchre = new ItemBuilder(NoNameDyes.MOD_ID)
		.setIcon(String.format("%s:item/brick_ochre",NoNameDyes.MOD_ID))
		.build(new Item("brick.ochre",UtilIdRegistrar.nextId()));

    public static void register() {

    }
}
