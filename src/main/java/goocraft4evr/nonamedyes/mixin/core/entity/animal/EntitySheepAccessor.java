package goocraft4evr.nonamedyes.mixin.core.entity.animal;

import net.minecraft.core.entity.animal.EntitySheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntitySheep.class, remap=false)
public interface EntitySheepAccessor {
    @Accessor("fleeceColorTable")
    static void setFleeceColorTable(float[][] table) {
        throw new AssertionError();
    }
}
