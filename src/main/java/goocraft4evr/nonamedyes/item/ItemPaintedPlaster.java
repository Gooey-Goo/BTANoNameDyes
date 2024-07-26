package goocraft4evr.nonamedyes.item;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.helper.ModColors;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.*;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.awt.Color;

// implements IColored
public class ItemPaintedPlaster extends Item {
	//private final IconCoordinate base = TextureRegistry.getTexture("painted_wet_plaster.png");

	public ItemPaintedPlaster(String name, int id) {
		super(name,id);
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        int blockId = world.getBlockId(blockX, blockY, blockZ);
		if (blockId == Block.mud.id) {
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX,blockY,blockZ, ModBlocks.plasterMud.id,itemstack.getMetadata());
				if (entityplayer.getGamemode().consumeBlocks()) {
					--itemstack.stackSize;
				}
			}
            return true;
        }
		if (blockId == Block.cobbleLimestone.id) {
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX,blockY,blockZ, ModBlocks.plasterLime.id,itemstack.getMetadata());
				if (entityplayer.getGamemode().consumeBlocks()) {
					--itemstack.stackSize;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String getLanguageKey(ItemStack itemstack) {
		int meta = itemstack.getMetadata();
		return super.getKey() + "." + (meta>15?ItemModDye.dyeColors[meta-16]: ItemDye.dyeColors[15-meta]);
	}

	/*
	@Override
	public ColoredTexture[] getTextures(ItemStack itemstack) {
		return new ColoredTexture[]{
			new ColoredTexture(base, new Color(ModColors.allPlasterColors[itemstack.getMetadata()].value))};
	}

	 */
}
