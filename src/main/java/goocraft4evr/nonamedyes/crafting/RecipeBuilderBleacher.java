package goocraft4evr.nonamedyes.crafting;

import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;

import java.util.Objects;

//TODO: extend RecipeBuilderBase when it becomes public accessible
public class RecipeBuilderBleacher implements Cloneable {
	protected String modID;
	protected RecipeSymbol input;
	public RecipeBuilderBleacher(String modID) {
		this.modID = Objects.requireNonNull(modID, "ModID must not be null!");
	}

	@SuppressWarnings({"unused"})
	public RecipeBuilderBleacher setInput(IItemConvertible item){
		return setInput(item, 0);
	}

	@Override
	public RecipeBuilderBleacher clone() {
		try {
			// none of the fields are mutated so this should be fine
			return (RecipeBuilderBleacher) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	@SuppressWarnings({"unused"})
	public RecipeBuilderBleacher setInput(IItemConvertible item, int meta){
		return setInput(new ItemStack(item, 1, meta));
	}
	@SuppressWarnings({"unused"})
	public RecipeBuilderBleacher setInput(ItemStack input){
		return setInput(new RecipeSymbol(input));
	}
	@SuppressWarnings({"unused"})
	public RecipeBuilderBleacher setInput(String itemGroup){
		return setInput(new RecipeSymbol(itemGroup));
	}
	@SuppressWarnings({"unused"})
	public RecipeBuilderBleacher setInput(RecipeSymbol input){
		RecipeBuilderBleacher builder = this.clone();
		builder.input = Objects.requireNonNull(input, "Input symbol must not be null!");
		return builder;
	}

	//TODO: add override annotation
	@SuppressWarnings({"unchecked"})
	public void create(String recipeID, ItemStack outputStack) {
		Objects.requireNonNull(input, "Input symbol must not be null!");
		((RecipeGroup<RecipeEntryBleacher>) RecipeBuilder.getRecipeGroup(modID, "bleacher", new RecipeSymbol(ModBlocks.bleacher.getDefaultStack())))
			.register(recipeID, new RecipeEntryBleacher(input, outputStack));
	}
}
