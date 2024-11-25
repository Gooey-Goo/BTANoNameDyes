package goocraft4evr.nonamedyes.block.entity;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import goocraft4evr.nonamedyes.block.BlockVileReactor;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.item.ModItems;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.World;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TileEntityVileReactor extends TileEntity {
    public int killCount = 0;
    private final int range = 4;
    private final int killLimit = 20;
    private final int killMark = killLimit/3;
    private static final WeightedRandomBag<WeightedRandomLootObject> reactorDrops = new WeightedRandomBag();

    @Override
    public void tick() {
        if (this.tileEntityInvalid) return;
        if (!worldObj.isClientSide&&
            worldObj.dimension == Dimension.nether) {
            List<Entity> list = worldObj.getEntitiesWithinAABB(Entity.class,AABB.getBoundingBoxFromPool(x-range, y-range, z-range, x+range, y+range, z+range));
            if (!list.isEmpty()) {
                for (Entity e : list) {
                    if (!(e instanceof EntityLiving)) continue;
                    EntityLiving entity = (EntityLiving)e;
                    //hacky way to determine if entity just died
                    if (entity.deathTime==1) {
                        ++killCount;
                        if (onEntityKilled()) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean onEntityKilled() {
        if (killCount>=killLimit) {
            worldObj.setBlockWithNotify(x,y,z,0);
            generateNetherrack(worldObj,worldObj.rand,x,y-1,z);
            generateItems(worldObj,worldObj.rand,x,y,z);
            worldObj.playSoundEffect(null,SoundCategory.WORLD_SOUNDS,x,y,z,"random.explode",1.0f,1.0f);
			doBusrtFx(worldObj,worldObj.rand,x,y,z);
            worldObj.removeBlockTileEntity(x,y,z);
            return true;
        } else if (killCount==killMark*2) {
            BlockVileReactor.updateReactorBlockState(worldObj,x,y,z);
        } else if (killCount==killMark) {
            BlockVileReactor.updateReactorBlockState(worldObj,x,y,z);
        }
        worldObj.playSoundEffect(null,SoundCategory.WORLD_SOUNDS,x,y,z,"random.fizz",1.0f,0.2f);
        doSmokeFx(worldObj,worldObj.rand,x,y,z);
        return false;
    }

    private  void doBusrtFx(World world, Random rand, int x, int y, int z) {
        for (int i=0;i<30+rand.nextInt(30);i++) {
            double particleX = (float)x + 0.5f;
            double particleY = (float)y + 0.5f;
            double particleZ = (float)z + 0.5f;
            double motionX = 1.3*(rand.nextFloat()-0.5);
            double motionY = 1.3*(rand.nextFloat()-0.5);
            double motionZ = 1.3*(rand.nextFloat()-0.5);
            world.spawnParticle("explode", particleX, particleY, particleZ, motionX, motionY, motionZ,0);
        }
    }

    private void doSmokeFx(World world, Random rand, int x, int y, int z) {
        for (int i=0;i<10+rand.nextInt(20);i++) {
            double particleX = (float)x + rand.nextFloat();
            double particleY = (float)y + 1.0f;
            double particleZ = (float)z + rand.nextFloat();
            double motionX = 0.2*(rand.nextFloat()-0.5);
            double motionY = 0.1*rand.nextFloat();
            double motionZ = 0.2*(rand.nextFloat()-0.5);
            world.spawnParticle("largesmoke", particleX, particleY, particleZ, motionX, motionY, motionZ,0);
        }
    }

    private void generateItems(World world, Random rand, int x, int y, int z) {
        int chances = 10 + rand.nextInt(10);
        for (int i=0;i<chances;i++) {
            ItemStack itemstack = reactorDrops.getRandom().getItemStack();
            float f = rand.nextFloat() * 0.8f + 0.1f;
            float f1 = rand.nextFloat() * 0.8f + 0.1f;
            float f2 = rand.nextFloat() * 0.8f + 0.1f;
            EntityItem entityitem = new EntityItem(world,
                    (float)x + f,
                    (float)y + f1,
                    (float)z + f2,
                    itemstack);
            float f3 = 0.05f;
            entityitem.xd = (float)rand.nextGaussian() * f3;
            entityitem.yd = (float)rand.nextGaussian() * f3 + 0.2f;
            entityitem.zd = (float)rand.nextGaussian() * f3;
            world.entityJoinedWorld(entityitem);
        }
    }

    //this could be a world feature if I weren't lazy
    private void generateNetherrack(World world, Random random, int x, int y, int z) {
        final int minrad = 2;
		final int maxrad = 3;
		final int mindepth = 4;
		final int maxdepth = 8;
        int minradX = -minrad - random.nextInt(maxrad+1-minrad);
        int maxradX = minrad + random.nextInt(maxrad+1-minrad);
        int minradZ = -minrad - random.nextInt(maxrad+1-minrad);
        int maxradZ = minrad + random.nextInt(maxrad+1-minrad);
        int dist = Arrays.stream(new int[]{-minradX,-minradZ,maxradX,maxradZ}).max().getAsInt();
        int depth = mindepth+ random.nextInt(maxdepth+1-mindepth);
        for (int ix = minradX;ix <= maxradX;ix++) {
            for (int iz=minradZ;iz<=maxradZ;iz++) {
                int taxicab = Math.abs(ix)+Math.abs(iz);
                if (taxicab > dist) continue;
                int d = (dist-taxicab)*depth/dist+random.nextInt(3);
                for (int iy=0;iy>=-d;iy--) {
                    if (world.getBlockId(x+ix,y+iy,z+iz) != Block.netherrack.id) continue;
                    world.setBlockWithNotify(x+ix,y+iy,z+iz,ModBlocks.netherrackVile.id);
                }
            }
        }
    }

	@Override
	public void readFromNBT(CompoundTag nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		killCount = nbttagcompound.getShort("Kills");
	}

	@Override
	public void writeToNBT(CompoundTag nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.putShort("Kills", (short)killCount);
	}

    static {
        reactorDrops.addEntry(new WeightedRandomLootObject(new ItemStack(ModItems.vileShard), 2, 3), 30.0);
        reactorDrops.addEntry(new WeightedRandomLootObject(new ItemStack(Item.bone), 1, 2), 15.0);
        reactorDrops.addEntry(new WeightedRandomLootObject(new ItemStack(Item.dye,1,15), 1, 3), 12.0);
        reactorDrops.addEntry(new WeightedRandomLootObject(new ItemStack(Item.sulphur), 1, 3), 7.0);
        //reactorDrops.addEntry(new WeightedRandomLootObject(new ItemStack(ModBlocks.netherrackVile), 1, 2), 2.5);
    }
}
