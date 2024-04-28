package goocraft4evr.nonamedyes.crafting.adapter;

import com.google.gson.*;
import goocraft4evr.nonamedyes.crafting.RecipeEntryBleacher;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.ItemStack;

import java.lang.reflect.Type;

public class RecipeBleacherJsonAdapter implements RecipeJsonAdapter<RecipeEntryBleacher> {
	@Override
	public RecipeEntryBleacher deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		RecipeSymbol input = (RecipeSymbol)context.deserialize(obj.get("input").getAsJsonObject(), (Type)((Object)RecipeSymbol.class));
		ItemStack output = (ItemStack)context.deserialize(obj.get("output").getAsJsonObject(), (Type)((Object)ItemStack.class));
		return new RecipeEntryBleacher(input, output);
	}

	@Override
	public JsonElement serialize(RecipeEntryBleacher src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.addProperty("name", src.toString());
		obj.addProperty("type", Registries.RECIPE_TYPES.getKey(src.getClass()));
		obj.add("input", context.serialize(src.getInput(), (Type)((Object)RecipeSymbol.class)));
		obj.add("output", context.serialize(src.getOutput(), (Type)((Object)ItemStack.class)));
		return obj;
	}
}
