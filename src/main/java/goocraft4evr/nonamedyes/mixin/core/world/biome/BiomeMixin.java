package goocraft4evr.nonamedyes.mixin.core.world.biome;

import goocraft4evr.nonamedyes.entity.animal.EntitySeaSnail;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value= Biome.class,remap=false)
public class BiomeMixin {
	@Shadow
	protected List<SpawnListEntry> spawnableWaterCreatureList;
	@Inject(method="<init>",at=@At(value = "TAIL"))
	private void inject(CallbackInfo ci) {
		spawnableWaterCreatureList.add(new SpawnListEntry(EntitySeaSnail.class, 10));
	}
}
