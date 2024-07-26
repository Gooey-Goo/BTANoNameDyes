package goocraft4evr.nonamedyes;

import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;

public class TextureMap {
    private final IconCoordinate[] textures;
    private int curr;
    private final String modid;

    public TextureMap(String modid, int length) {
        this.modid = modid;
        textures = new IconCoordinate[length];
        curr = 0;
    }

    public IconCoordinate getTexture(int index) {
		//just return the first texture if the index range is out of bounds
		return textures[index<0 || index>textures.length-1 ? 0 : index];
    }

    public int length() {
        return textures.length;
    }

	//replaced with addTexture
	@Deprecated
    public void addBlockTexture(String texture) {
        textures[curr++] = TextureRegistry.getTexture(texture);
    }

	//replaced with addTexture
	@Deprecated
    public void addItemTexture(String texture) {
		textures[curr++] = TextureRegistry.getTexture(texture);
    }

	public void addTexture(String texture) {
		textures[curr++] = TextureRegistry.getTexture(texture);
	}
}
