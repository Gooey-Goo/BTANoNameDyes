package goocraft4evr.nonamedyes.mixin.client.render;

import goocraft4evr.nonamedyes.block.BlockBleacher;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.block.entity.TileEntityBleacher;
import net.minecraft.client.render.RenderBlocks;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value= RenderBlocks.class,remap = false)
public abstract class RenderBlocksMixin  {

	//TODO: this class is invalid
	/*
    @Shadow private WorldSource blockAccess;

    @Shadow public abstract float getBlockBrightness(WorldSource blockAccess, int x, int y, int z);

    @Shadow public abstract boolean renderStandardBlock(Block block, int x, int y, int z);

    @Shadow public abstract void renderTopFace(Block block, double x, double d1, double d2, int i);



    @Inject(method = "renderBlockByRenderType",at=@At(value="INVOKE",target = "Lnet/minecraft/core/block/Block;setBlockBoundsBasedOnState(Lnet/minecraft/core/world/World;III)V"), cancellable = true)
    private void renderMyAss(Block block, int renderType, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (block.id == ModBlocks.bleacher.id) {
            cir.setReturnValue(renderBlockBleacher(block,x,y,z));
            return;
        }
        if (block.id == ModBlocks.netherRoots.id) {
            cir.setReturnValue(renderBlockNetherRoots(block,x,y,z));
            return;
        }
    }

    @Unique
    private Boolean renderBlockBleacher(Block block, int x, int y, int z) {
        block.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.0625f, 0.9375f);
        this.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0625f);
        this.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.0f, 0.0f, 0.9375f, 1.0f, 1.0f, 1.0f);
        this.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.0f, 0.0f, 0.0625f, 0.0625f, 1.0f, 0.9375f);
        this.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.9375f, 0.0f, 0.0625f, 1.0f, 1.0f, 0.9375f);
        this.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        TileEntityBleacher teb = (TileEntityBleacher)blockAccess.getBlockTileEntity(x,y,z);
        if (teb.hasWaterSource || teb.currentFuelTime > 0) {
            renderTopFace(block, x, y-0.25, z, ((BlockBleacher)block).textures.getTexture(teb.isFuelled()?1:0));
        }
        return true;
    }

    @Unique
    private Boolean renderBlockNetherRoots(Block block, int x, int y, int z) {
        //create funny instance
        Tessellator t = Tessellator.instance;
        //git uv coords
        int texIndex = block.getBlockTexture(blockAccess, x, y, z, Side.BOTTOM);
        int texXCoord = texIndex % Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain;
        int texYCoord = texIndex / Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain;
        double minU = (double)texXCoord / (double)(TextureFX.tileWidthTerrain * Global.TEXTURE_ATLAS_WIDTH_TILES);
        double maxU = ((double)(texXCoord + TextureFX.tileWidthTerrain) - 0.01) / (double)(TextureFX.tileWidthTerrain * Global.TEXTURE_ATLAS_WIDTH_TILES);
        double minV = (double)texYCoord / (double)(TextureFX.tileWidthTerrain * Global.TEXTURE_ATLAS_WIDTH_TILES);
        double maxV = ((double)(texYCoord + TextureFX.tileWidthTerrain) - 0.01) / (double)(TextureFX.tileWidthTerrain * Global.TEXTURE_ATLAS_WIDTH_TILES);
        float grum = 0.001f;
        //set brightness maybe?
        float blockBrightness = getBlockBrightness(blockAccess, x, y, z);
        Tessellator.instance.setColorOpaque_F(blockBrightness, blockBrightness, blockBrightness);
        //base quad
        t.addVertexWithUV(x, y+grum, z, minU, minV);
        t.addVertexWithUV(x, y+grum, z+1, maxU, minV);
        t.addVertexWithUV(x+1, y+grum, z+1, maxU, maxV);
        t.addVertexWithUV(x+1, y+grum, z, minU, maxV);
        //premature cancel if block above is solid
        if (blockAccess.getBlockId(x,y+1,z) != 0) return true;
        //quads based on flags
        boolean renderNorth = blockAccess.getBlockId(x,y+1,z-1) == ModBlocks.netherRoots.id;
        boolean renderEast = blockAccess.getBlockId(x+1,y+1,z) == ModBlocks.netherRoots.id;
        boolean renderSouth = blockAccess.getBlockId(x,y+1,z+1) == ModBlocks.netherRoots.id;
        boolean renderWest = blockAccess.getBlockId(x-1,y+1,z) == ModBlocks.netherRoots.id;
        if (renderNorth) {
            t.addVertexWithUV(x, y, z+grum, minU, minV);
            t.addVertexWithUV(x+1, y, z+grum, maxU, minV);
            t.addVertexWithUV(x+1, y+1, z+grum, maxU, maxV);
            t.addVertexWithUV(x, y+1, z+grum, minU, maxV);
        }
        if (renderEast) {
            t.addVertexWithUV(x+1-grum, y, z, minU, minV);
            t.addVertexWithUV(x+1-grum, y, z+1, maxU, minV);
            t.addVertexWithUV(x+1-grum, y+1, z+1, maxU, maxV);
            t.addVertexWithUV(x+1-grum, y+1, z, minU, maxV);
        }
        if (renderSouth) {
            t.addVertexWithUV(x, y, z+1-grum, minU, minV);
            t.addVertexWithUV(x, y+1, z+1-grum, maxU, minV);
            t.addVertexWithUV(x+1, y+1, z+1-grum, maxU, maxV);
            t.addVertexWithUV(x+1, y, z+1-grum, minU, maxV);
        }
        if (renderWest) {
            t.addVertexWithUV(x+grum, y, z, minU, minV);
            t.addVertexWithUV(x+grum, y+1, z, maxU, minV);
            t.addVertexWithUV(x+grum, y+1, z+1, maxU, maxV);
            t.addVertexWithUV(x+grum, y, z+1, minU, maxV);
        }
        //set bounds
        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
        return true;
    }

	 */
}
