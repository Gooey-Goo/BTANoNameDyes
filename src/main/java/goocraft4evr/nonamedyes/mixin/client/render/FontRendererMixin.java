package goocraft4evr.nonamedyes.mixin.client.render;

import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.core.util.helper.Color;
import net.minecraft.client.util.helper.Colors;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = FontRenderer.class, remap = false)
public abstract class FontRendererMixin {
    @Final
    private int[] colorCode = new int[32+ItemModDye.NUM_DYES<<1];

    @Inject(method="<init>",at=@At("TAIL"))
    private void injected(Minecraft minecraft, GameSettings gameSettings, String fontPath, RenderEngine renderEngine, CallbackInfo ci) {
        for (int i = 0; i < ItemModDye.NUM_DYES<<1; ++i) {
            Color color = Colors.allChatColors[16+i%ItemModDye.NUM_DYES];
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            if (i >= ItemModDye.NUM_DYES) {
                r /= 4;
                g /= 4;
                b /= 4;
            }
            this.colorCode[32+i] = (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
        }
    }

    @Shadow
    private boolean obfuscatedStyle;
    @Shadow
    private boolean boldStyle;
    @Shadow
    private boolean italicStyle;
    @Shadow
    private boolean underlineStyle;
    @Shadow
    private boolean strikethroughStyle;
    @Shadow
    private int textColor;
    @Shadow
    private float alpha;

	//TODO: fix this mixin
	/*
    @Inject(method="renderStringAtPos",at=@At(value="INVOKE_ASSIGN",ordinal=1,target="Ljava/lang/String;indexOf(I)I"),locals = LocalCapture.CAPTURE_FAILHARD)
    private void injected(String text, boolean flag, CallbackInfo ci, int i, char c) {
        if (formatCode > 21) {
            this.obfuscatedStyle = false;
            this.boldStyle = false;
            this.strikethroughStyle = false;
            this.underlineStyle = false;
            this.italicStyle = false;
            if (flag) {
                formatCode += ItemModDye.NUM_DYES;
            }
            this.textColor = this.colorCode[10+formatCode];
            GL11.glColor4f((float)(this.textColor >> 16) / 255.0f, (float)(this.textColor >> 8 & 0xFF) / 255.0f, (float)(this.textColor & 0xFF) / 255.0f, this.alpha);
        }
    }

	 */

    @ModifyConstant(method = "renderStringAtPos",constant = @Constant(stringValue = "0123456789abcdefklmnor"))
    private String modify(String code) {
        return code+"ɏɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯʰʱʲʳʴʵʶʷʸʹʺʻʼʽʾʿˀˁ˂˃˄˅ˆˇˈˉˊˋˌˍˎ";
    }
}
