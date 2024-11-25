package goocraft4evr.nonamedyes.modcontainers.btwaila;

import org.slf4j.Logger;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.util.ProgressBarOptions;
import toufoumaster.btwaila.tooltips.TooltipRegistry;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class BTWailaPlugin implements BTWailaCustomTooltipPlugin {
    @Override
    public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
        tooltipRegistry.register(new BleacherTooltip());
    }
}