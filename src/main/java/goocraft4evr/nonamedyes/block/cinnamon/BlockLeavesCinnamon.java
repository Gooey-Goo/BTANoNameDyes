package goocraft4evr.nonamedyes.block.cinnamon;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;

public class BlockLeavesCinnamon extends BlockLeavesBase {

    public BlockLeavesCinnamon(String key, int id) {
        super(key, id, Material.leaves);
    }

    @Override
    protected Block getSapling() {
        return ModBlocks.saplingCinnamon;
    }
}
