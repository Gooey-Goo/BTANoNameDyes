package goocraft4evr.nonamedyes.client.render.block.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.core.block.Block;

public class BlockModelNetherRoots<T extends Block> extends BlockModelStandard<T> {
	public BlockModelNetherRoots(Block block) {
		super(block);
	}

	@Override
	public boolean shouldItemRender3d() {
		return false;
	}
}
