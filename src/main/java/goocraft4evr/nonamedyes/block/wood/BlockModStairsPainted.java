package goocraft4evr.nonamedyes.block.wood;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModStairsPainted extends BlockStairs {
    public BlockModStairsPainted(Block modelBlock, int id) {
        super(modelBlock, id);
    }

    public static int getMetaForDyeColor(int i) {
        return i << 4;
    }
}
