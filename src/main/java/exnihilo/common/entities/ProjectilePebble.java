package exnihilo.common.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public class ProjectilePebble extends EntityThrowable {

    private EntityLivingBase thrower;
    public ProjectilePebble(World worldIn) {
        super(worldIn);
    }

    public ProjectilePebble(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.thrower = throwerIn;
    }

    public ProjectilePebble(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @Override
    protected void onImpact(RayTraceResult result) {
            if (result.entityHit != null) {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 0.4F);
            }

            for (int j = 0; j < 8; ++j) {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, posX, posY, posZ, 0.0D, 0.0D, 0.0D, Block.getStateId(Blocks.STONE.getDefaultState()));
            }
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }
}
