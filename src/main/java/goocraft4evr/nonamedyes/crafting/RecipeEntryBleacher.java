package goocraft4evr.nonamedyes.crafting;

import goocraft4evr.nonamedyes.crafting.adapter.RecipeBleacherJsonAdapter;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.*;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class RecipeEntryBleacher
	extends RecipeEntryBase<RecipeSymbol, ItemStack, Void>
	implements HasJsonAdapter {
	public RecipeEntryBleacher(RecipeSymbol input, ItemStack output) {
		super(input, output, null);
	}

	public RecipeEntryBleacher() {
	}

	@Override
	public Void getData() {
		return null;
	}

	@Override
	public boolean containsData(Void data) {
		return false;
	}

	public boolean matches(ItemStack stack) {
		return ((RecipeSymbol)this.getInput()).matches(stack);
	}

	public boolean matchesQueryIgnoreExceptions(SearchQuery query) {
		try {
			return this.matchesQuery(query);
		} catch (IllegalArgumentException | NullPointerException e) {
			return false;
		}
	}

	public boolean matchesQuery(SearchQuery query) {
		switch (query.mode) {
			case ALL: {
				if (!this.matchesRecipe(query) && !this.matchesUsage(query) || !this.matchesScope(query)) break;
				return true;
			}
			case RECIPE: {
				if (!this.matchesRecipe(query) || !this.matchesScope(query)) break;
				return true;
			}
			case USAGE: {
				if (!this.matchesUsage(query) || !this.matchesScope(query)) break;
				return true;
			}
		}
		return false;
	}

	private boolean matchesRecipe(SearchQuery query) {
		if (query.query.getLeft() == SearchQuery.QueryType.NAME) {
			if (query.strict && ((ItemStack)this.getOutput()).getDisplayName().equalsIgnoreCase(query.query.getRight())) {
				return true;
			}
			if (!query.strict && ((ItemStack)this.getOutput()).getDisplayName().toLowerCase().contains(query.query.getRight().toLowerCase())) {
				return true;
			}
		} else if (query.query.getLeft() == SearchQuery.QueryType.GROUP && !Objects.equals(query.query.getRight(), "")) {
			List<ItemStack> groupStacks = new RecipeSymbol(query.query.getRight()).resolve();
			if (groupStacks == null) {
				return false;
			}
			return groupStacks.contains(this.getOutput());
		}
		return false;
	}

	private boolean matchesUsage(SearchQuery query) {
		List<ItemStack> stacks = ((RecipeSymbol)this.getInput()).resolve();
		for (ItemStack stack : stacks) {
			if (stack == null) continue;
			if (query.query.getLeft() == SearchQuery.QueryType.NAME) {
				if (query.strict && stack.getDisplayName().equalsIgnoreCase(query.query.getRight())) {
					return true;
				}
				if (query.strict || !stack.getDisplayName().toLowerCase().contains(query.query.getRight().toLowerCase())) continue;
				return true;
			}
			if (query.query.getLeft() != SearchQuery.QueryType.GROUP || Objects.equals(query.query.getRight(), "")) continue;
			List<ItemStack> groupStacks = new RecipeSymbol(query.query.getRight()).resolve();
			if (groupStacks == null) {
				return false;
			}
			return groupStacks.contains(this.getOutput());
		}
		return false;
	}

	private boolean matchesScope(SearchQuery query) {
		if (query.scope.getLeft() == SearchQuery.SearchScope.NONE) {
			return true;
		}
		if (query.scope.getLeft() == SearchQuery.SearchScope.NAMESPACE) {
			RecipeNamespace namespace = (RecipeNamespace) Registries.RECIPES.getItem(query.scope.getRight());
			if (namespace == this.parent.getParent()) {
				return true;
			}
		} else if (query.scope.getLeft() == SearchQuery.SearchScope.NAMESPACE_GROUP) {
			RecipeGroup group;
			try {
				group = Registries.RECIPES.getGroupFromKey(query.scope.getRight());
			} catch (IllegalArgumentException e) {
				group = null;
			}
			if (group == this.parent) {
				return true;
			}
		}
		return false;
	}

	@Override
	public RecipeJsonAdapter<?> getAdapter() {
		return new RecipeBleacherJsonAdapter();
	}
}
