package goocraft4evr.nonamedyes.client.render.block.model;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.TextureMap;
import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelBleacher<T extends Block> extends BlockModelStandard<T> {
	protected final TextureMap TEXTURES = new TextureMap(NoNameDyes.MOD_ID,5);
	public BlockModelBleacher(Block block) {
		super(block);
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_top_empty");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_top_water");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_top_bleach");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_sides");
		TEXTURES.addTexture(NoNameDyes.MOD_ID+":block/bleacher_bottom");
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (side == Side.BOTTOM) return TEXTURES.getTexture(4);
		return TEXTURES.getTexture(side.isHorizontal()?3:0);
	}

	@Override
	public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		if (side != Side.TOP) return super.getBlockTexture(blockAccess,x,y,z,side);
		TileEntityBleacher teb = (TileEntityBleacher)renderBlocks.blockAccess.getBlockTileEntity(x,y,z);
		return TEXTURES.getTexture(teb.currentWaterTime>0?(teb.isFuelled()?2:1):0);
	}
}
