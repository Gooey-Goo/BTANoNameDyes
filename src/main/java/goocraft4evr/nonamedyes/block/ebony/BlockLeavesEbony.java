package goocraft4evr.nonamedyes.block.ebony;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;

public class BlockLeavesEbony extends BlockLeavesBase {

    public BlockLeavesEbony(String key, int id) {
        super(key, id, Material.leaves);
    }

    @Override
    protected Block getSapling() {
        return ModBlocks.saplingEbony;
    }
}
