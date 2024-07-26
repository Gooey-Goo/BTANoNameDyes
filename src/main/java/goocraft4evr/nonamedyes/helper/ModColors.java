package goocraft4evr.nonamedyes.helper;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.util.helper.Color;
import net.minecraft.client.util.helper.Colors;

public abstract class ModColors {
    public static final Color[] allPlasterColors = new Color[16+ItemModDye.NUM_DYES];

    public static void loadColors() {
        Colors.fillColorArray("/assets/"+ NoNameDyes.MOD_ID +"/misc/colors_plaster.png", allPlasterColors);
    }
}
