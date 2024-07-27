package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelBleacher<T extends Block> extends BlockModelStandard<T> {
	protected final TextureMap TEXTURES = new TextureMap(NoNameDyes.MOD_ID,5);
	public BlockModelBleacher(Block block) {
		super(block);
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_top_empty");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_fluid_water");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_fluid_bleach");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_sides");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_bottom");
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (side == Side.BOTTOM) return TEXTURES.getTexture(4);
		return TEXTURES.getTexture(side.isHorizontal()?3:0);
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		TileEntityBleacher teb = (TileEntityBleacher)renderBlocks.blockAccess.getBlockTileEntity(x,y,z);
		block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
		renderBottomFace(tessellator,block, x, y, z, TEXTURES.getTexture(4));
		renderNorthFace(tessellator,block, x, y, z, TEXTURES.getTexture(3));
		renderEastFace(tessellator,block, x, y, z, TEXTURES.getTexture(3));
		renderSouthFace(tessellator,block, x, y, z, TEXTURES.getTexture(3));
		renderWestFace(tessellator,block, x, y, z, TEXTURES.getTexture(3));
		renderTopFace(tessellator,block, x, y, z, TEXTURES.getTexture(teb.hasWaterSource?(teb.isFuelled()?2:1):0));
		return true;
	}
}
