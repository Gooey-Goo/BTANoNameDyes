package goocraft4evr.nonamedyes.mixin.client.render;

import net.minecraft.client.render.FontRenderer;
import net.minecraft.core.util.helper.Color;
import net.minecraft.client.util.helper.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = FontRenderer.class, remap = false)
public abstract class FontRendererMixin {
	//TODO: weird indent appears
	@ModifyVariable(method = "renderStringAtPos", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private String injected(String text) {
		StringBuilder sb = new StringBuilder();
		boolean specialChar = false;
		for (char c : text.toCharArray()) {
			if (specialChar) {
				int index = "ɏɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯʰʱʲʳʴʵʶʷʸʹʺʻʼʽʾʿˀˁ˂˃˄˅ˆˇˈˉˊˋˌˍˎ".indexOf(c);
				if (index>-1) {
					Color colour = Colors.allChatColors[16+index];
					sb.append("<").append(colour.toHexRGBA(), 2, 8).append(">");
					specialChar = false;
					continue;
				}
			} else specialChar = c == '\u00a7';
			sb.append(c);
		}
		return sb.toString();
	}
}
