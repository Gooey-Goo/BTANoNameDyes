package goocraft4evr.nonamedyes.mixin.client.render;

import goocraft4evr.nonamedyes.NoNameDyes;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.texturepack.TexturePack;
import net.minecraft.client.render.texturepack.TexturePackList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Mixin(value= RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {
    @Shadow
    public TexturePackList texturePacks;

    @Inject(method="refreshTextures",at=@At("TAIL"))
    private void injected(List<Throwable> errors, CallbackInfo ci) {
		//TODO: verify that leaf colours need to be registered here
		/*
        TexturePack texturePack = texturePacks.selectedTexturePack;
        Properties colors = new Properties();
        try {
            colors.load(texturePack.getResourceAsStream("/assets/"+ NoNameDyes.MOD_ID+"/misc/colors.properties"));
        } catch (IOException iOException) {
            // empty catch block
        }
        //color loading code
        ColorPropertiesAccessor.invokeRegisterLeavesColor("cocoa",colors);
        ColorPropertiesAccessor.invokeRegisterLeavesColor("cinnamon",colors);
        ColorPropertiesAccessor.invokeRegisterLeavesColor("ebony",colors);
		ColorPropertiesAccessor.invokeRegisterLeavesColor("palm",colors);
		 */
    }
}

