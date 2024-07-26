package goocraft4evr.nonamedyes.client.render.entity;

import goocraft4evr.nonamedyes.client.render.model.ModelSeaSnail;
import goocraft4evr.nonamedyes.entity.animal.EntitySeaSnail;
import net.minecraft.client.render.entity.LivingRenderer;
public class SeaSnailRenderer extends LivingRenderer<EntitySeaSnail> {

	public SeaSnailRenderer() {
		super(new ModelSeaSnail(), 0.7f);
	}
}
