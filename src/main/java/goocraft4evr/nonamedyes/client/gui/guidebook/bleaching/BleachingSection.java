package goocraft4evr.nonamedyes.client.gui.guidebook.bleaching;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.crafting.ModRecipes;
import goocraft4evr.nonamedyes.crafting.RecipeEntryBleacher;
import net.minecraft.client.gui.guidebook.GuidebookPage;
import net.minecraft.client.gui.guidebook.GuidebookSection;
import net.minecraft.client.gui.guidebook.SearchableGuidebookSection;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.Pair;
import net.minecraft.core.util.helper.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BleachingSection extends SearchableGuidebookSection {
	private final List<GuidebookPage> pages = new ArrayList<>();
	private Pair<String, List<GuidebookPage>> filteredPages = null;

	public BleachingSection(String translationKey, ItemStack tabIcon, int bgColor, int fgColor) {
		super(translationKey, tabIcon, bgColor, fgColor);
		this.reloadRecipes();
	}

	@Override
	public List<GuidebookPage> searchPages(SearchQuery query) {
		if (this.filteredPages == null || !Objects.equals(this.filteredPages.getLeft(), query.rawQuery)) {
			ArrayList<RecipeEntryBleacher> filteredRecipes = new ArrayList<>();
			ArrayList<RecipeEntryBleacher> allRecipes = new ArrayList<>(ModRecipes.BLEACHER.getAllRecipes());
			allRecipes.removeIf(Objects::isNull);
			NoNameDyes.LOGGER.info(allRecipes.toString());
			for (RecipeEntryBleacher recipe : allRecipes) {
				if (!recipe.matchesQueryIgnoreExceptions(query)) continue;
				filteredRecipes.add(recipe);
			}
			ArrayList<GuidebookPage> filteredPages = new ArrayList<>();
			int filteredRecipeSize = filteredRecipes.size();
			int filteredPageCount = MathHelper.ceilInt(filteredRecipeSize, 3);
			for (int i = 0; i < filteredPageCount; ++i) {
				int j = i * 6;
				ArrayList<RecipeEntryBleacher> recipes = new ArrayList<>(filteredRecipes.subList(Math.min(j, filteredRecipeSize), Math.min(j + 6, filteredRecipeSize)));
				if (recipes.size() <= 0) continue;
				filteredPages.add(new BleachingPage(this, recipes));
			}
			this.filteredPages = Pair.of(query.rawQuery, filteredPages);
			return filteredPages;
		}
		return this.filteredPages.getRight();
	}

	public void reloadRecipes() {
		this.pages.clear();
		ArrayList<RecipeEntryBleacher> allRecipes = new ArrayList<>(ModRecipes.BLEACHER.getAllRecipes());
		NoNameDyes.LOGGER.info(allRecipes.toString());
		allRecipes.removeIf(Objects::isNull);
		int totalRecipes = allRecipes.size();
		int totalPages = MathHelper.ceilInt(totalRecipes, 6);
		for (int i = 0; i < totalPages; ++i) {
			int j = i * 6;
			ArrayList<RecipeEntryBleacher> recipes = new ArrayList<>(allRecipes.subList(Math.min(j, totalRecipes), Math.min(j + 6, totalRecipes)));
			this.pages.add(new BleachingPage(this, recipes));
		}
	}

	@Override
	public List<GuidebookPage> getPages() {
		if (pages.isEmpty()) reloadRecipes();
		return this.pages;
	}

	@Override
	public List<GuidebookSection.Index> getIndices() {
		return null;
	}
}
