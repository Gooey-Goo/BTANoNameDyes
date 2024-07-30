package goocraft4evr.nonamedyes.mixin.core.net.command;

import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.net.command.TextFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static goocraft4evr.nonamedyes.NoNameDyes.LOGGER;

@Mixin(value= TextFormatting.class,remap = false)
public abstract class TextFormattingMixin {

    @Final
    private static final TextFormatting[] FORMATTINGS = new TextFormatting[22+ItemModDye.NUM_DYES];

    @Unique
    private static String getNameFromKey(String key) {
        StringBuilder sb = new StringBuilder();
        boolean capitalize = true;
        for (int i=0;i<key.length();i++) {
            char c = (capitalize?key.toUpperCase():key).charAt(i);
            capitalize = c == '.';
            if (capitalize) c = ' ';
            sb.append(c);
        }
        return sb.toString();
    }
    @Shadow
    private static String simpleName(String str) {
        return "";
    }
    @Inject(method = "getColorFormatting(Ljava/lang/String;)Lnet/minecraft/core/net/command/TextFormatting;", at = @At(value = "TAIL", shift = At.Shift.BEFORE), cancellable = true)
    private static void getColorFormatting(String name, CallbackInfoReturnable<TextFormatting> cir){
        for (int i2 = 22; i2 < 22 + ItemModDye.NUM_DYES; ++i2) {
            TextFormatting color = TextFormatting.get(i2);
            for (String name2 : color.getNames()) {
                if (!name.equalsIgnoreCase(simpleName(name2))) continue;
                cir.setReturnValue(color);
                return;
            }
        }

    }

    static {
        FORMATTINGS[0] = TextFormatting.WHITE;
        FORMATTINGS[1] = TextFormatting.ORANGE;
        FORMATTINGS[2] = TextFormatting.MAGENTA;
        FORMATTINGS[3] = TextFormatting.LIGHT_BLUE;
        FORMATTINGS[4] = TextFormatting.YELLOW;
        FORMATTINGS[5] = TextFormatting.LIME;
        FORMATTINGS[6] = TextFormatting.PINK;
        FORMATTINGS[7] = TextFormatting.GRAY;
        FORMATTINGS[8] = TextFormatting.LIGHT_GRAY;
        FORMATTINGS[9] = TextFormatting.CYAN;
        FORMATTINGS[10] = TextFormatting.PURPLE;
        FORMATTINGS[11] = TextFormatting.BLUE;
        FORMATTINGS[12] = TextFormatting.BROWN;
        FORMATTINGS[13] = TextFormatting.GREEN;
        FORMATTINGS[14] = TextFormatting.RED;
        FORMATTINGS[15] = TextFormatting.BLACK;
        FORMATTINGS[16] = TextFormatting.OBFUSCATED;
        FORMATTINGS[17] = TextFormatting.BOLD;
        FORMATTINGS[18] = TextFormatting.STRIKETHROUGH;
        FORMATTINGS[19] = TextFormatting.UNDERLINE;
        FORMATTINGS[20] = TextFormatting.ITALIC;
        FORMATTINGS[21] = TextFormatting.RESET;
        for (int i=22;i<FORMATTINGS.length;i++) {
            FORMATTINGS[i] = new TextFormatting(i).setNames(getNameFromKey(ItemModDye.dyeColors[i-22]));
        }
    }

    @ModifyConstant(method="<init>", constant = @Constant(stringValue = "0123456789abcdefklmnor"))
    private String methodName(String variable) {
        return variable + "ɏɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯʰʱʲʳʴʵʶʷʸʹʺʻʼʽʾʿˀˁ˂˃˄˅ˆˇˈˉˊˋˌˍˎ";
    }
}
