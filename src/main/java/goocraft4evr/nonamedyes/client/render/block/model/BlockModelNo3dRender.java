package goocraft4evr.nonamedyes.client.render.block.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.core.block.Block;

public class BlockModelNo3dRender<T extends Block> extends BlockModelStandard<T> {
    public BlockModelNo3dRender(Block block) {
        super(block);
    }

    @Override
    public boolean shouldItemRender3d() {
        return false;
    }
}
