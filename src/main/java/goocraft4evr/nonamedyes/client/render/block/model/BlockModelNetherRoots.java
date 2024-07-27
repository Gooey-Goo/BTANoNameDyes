package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.block.ModBlocks;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelNetherRoots<T extends Block> extends BlockModelStandard<T> {
	protected final IconCoordinate TEXTURE = TextureRegistry.getTexture(NoNameDyes.MOD_ID+":block/nether_roots");
	public BlockModelNetherRoots(Block block) {
		super(block);
	}

	@Override
	public boolean shouldItemRender3d() {
		return false;
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		float grum = 0.001f;
		//set brightness maybe?
		float blockBrightness = getBlockBrightness(renderBlocks.blockAccess, x, y, z);
		Tessellator.instance.setColorOpaque_F(blockBrightness, blockBrightness, blockBrightness);
		//base quad
		tessellator.addVertexWithUV(x, y+grum, z, TEXTURE.getIconUMin(), TEXTURE.getIconVMin());
		tessellator.addVertexWithUV(x, y+grum, z+1, TEXTURE.getIconUMax(), TEXTURE.getIconVMin());
		tessellator.addVertexWithUV(x+1, y+grum, z+1, TEXTURE.getIconUMax(), TEXTURE.getIconVMax());
		tessellator.addVertexWithUV(x+1, y+grum, z, TEXTURE.getIconUMin(), TEXTURE.getIconVMax());
		//premature cancel if block above is solid
		if (renderBlocks.blockAccess.getBlockId(x,y+1,z) != 0) return true;
		//quads based on flags
		boolean renderNorth = renderBlocks.blockAccess.getBlockId(x,y+1,z-1) == ModBlocks.netherRoots.id;
		boolean renderEast = renderBlocks.blockAccess.getBlockId(x+1,y+1,z) == ModBlocks.netherRoots.id;
		boolean renderSouth = renderBlocks.blockAccess.getBlockId(x,y+1,z+1) == ModBlocks.netherRoots.id;
		boolean renderWest = renderBlocks.blockAccess.getBlockId(x-1,y+1,z) == ModBlocks.netherRoots.id;
		if (renderNorth) {
			tessellator.addVertexWithUV(x, y, z+grum, TEXTURE.getIconUMin(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+1, y, z+grum, TEXTURE.getIconUMax(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+1, y+1, z+grum, TEXTURE.getIconUMax(), TEXTURE.getIconVMax());
			tessellator.addVertexWithUV(x, y+1, z+grum, TEXTURE.getIconUMin(), TEXTURE.getIconVMax());
		}
		if (renderEast) {
			tessellator.addVertexWithUV(x+1-grum, y, z, TEXTURE.getIconUMin(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+1-grum, y, z+1, TEXTURE.getIconUMax(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+1-grum, y+1, z+1, TEXTURE.getIconUMax(), TEXTURE.getIconVMax());
			tessellator.addVertexWithUV(x+1-grum, y+1, z, TEXTURE.getIconUMin(), TEXTURE.getIconVMax());
		}
		if (renderSouth) {
			tessellator.addVertexWithUV(x, y, z+1-grum, TEXTURE.getIconUMin(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x, y+1, z+1-grum, TEXTURE.getIconUMax(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+1, y+1, z+1-grum, TEXTURE.getIconUMax(), TEXTURE.getIconVMax());
			tessellator.addVertexWithUV(x+1, y, z+1-grum, TEXTURE.getIconUMin(), TEXTURE.getIconVMax());
		}
		if (renderWest) {
			tessellator.addVertexWithUV(x+grum, y, z, TEXTURE.getIconUMin(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+grum, y+1, z, TEXTURE.getIconUMax(), TEXTURE.getIconVMin());
			tessellator.addVertexWithUV(x+grum, y+1, z+1, TEXTURE.getIconUMax(), TEXTURE.getIconVMax());
			tessellator.addVertexWithUV(x+grum, y, z+1, TEXTURE.getIconUMin(), TEXTURE.getIconVMax());
		}
		//set bounds
		block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
		return true;
	}
}
