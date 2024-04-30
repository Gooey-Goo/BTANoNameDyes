package goocraft4evr.nonamedyes.entity.animal;

import goocraft4evr.nonamedyes.NoNameDyes;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.Global;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.animal.EntityWaterAnimal;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import java.util.List;

public class EntitySeaSnail extends EntityWaterAnimal {
	private int timeUntilNextGoo;
	public EntitySeaSnail(World world) {
		super(world);
		skinName = "seasnail";
		setSize(0.9f, 0.9f);
		scoreValue = 10;
		moveSpeed = 0.1f;
		timeUntilNextGoo = this.random.nextInt(3000) + 3000;
	}

	@Override
	public int getMaxHealth() {
		return 15;
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	protected String getDeathSound() {
		return null;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isJumping && (isInWater())) yd -= 0.04;

		if (!this.world.isClientSide && --timeUntilNextGoo <= 0) {
			this.world.playSoundAtEntity(null, this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
			this.spawnAtLocation(new ItemStack(ModItems.dye.id,1+this.random.nextInt(3),12),0);
			this.timeUntilNextGoo = this.random.nextInt(3000) + 3000;
		}
	}

	@Override
	public void trySuffocate() {
		if (this.isAlive() && !this.isUnderLiquid(Material.water) && this.canBreatheUnderwater()) {
			--this.airSupply;
			if (this.airSupply == -20) {
				this.airSupply = 0;
				this.hurt(null, 2, DamageType.DROWN);
			}
			this.remainingFireTicks = 0;
		} else {
			this.airSupply = this.airMaxSupply;
		}
	}

	@Override
	protected void updatePlayerActionState() {
		super.updatePlayerActionState();
		if (isInWater() && !isMovementBlocked()) isJumping = false;
	}

	@Override
	public String getEntityTexture() {
		return String.format("/assets/%s/mob/%s/%d.png", NoNameDyes.MOD_ID,skinName,getSkinVariant());
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = 5;
		return entityData.getByte(1) % skinVariantCount;
	}

	@Override
	protected boolean makeStepSound() {
		return false;
	}

	@Override
	protected void dropFewItems() {
		int j = this.random.nextInt(3);
		for (int k = 0; k < j; ++k) {
			this.spawnAtLocation(new ItemStack(ModItems.dye.id,1,12), 1);
		}
	}
}
