package goocraft4evr.nonamedyes.entity;

import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityIcecube
	extends EntityProjectile {
	private boolean firstBounce = true;

	public EntityIcecube(World world) {
		super(world);
	}

	public EntityIcecube(World world, EntityLiving entityliving) {
		super(world, entityliving);
	}

	public EntityIcecube(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
	}

	@Override
	public void init() {
		super.init();
		this.damage = 1;
	}

	@Override
	public void onHit(HitResult hitResult) {
		if (hitResult.entity != null) {
			hitResult.entity.hurt(this.owner, this.damage, DamageType.COMBAT);
		}
		if (!this.world.isClientSide) {
			EntityItem item = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(ModItems.ammoIcecube, 1));
			this.world.entityJoinedWorld(item);
		}
		this.remove();
	}

	@Override
	public void afterTick() {
		if (this.isInWater() && Math.abs(this.xd) + Math.abs(this.zd) > 0.5) {
			float modifier = 1.0f;
			if (this.firstBounce) {
				this.firstBounce = false;
				modifier = 1.0f + this.random.nextFloat() * 0.75f;
			}
			this.yd = (Math.abs(this.xd) + Math.abs(this.zd)) / 8.0 * (double)modifier;
		}
		super.afterTick();
	}
}

