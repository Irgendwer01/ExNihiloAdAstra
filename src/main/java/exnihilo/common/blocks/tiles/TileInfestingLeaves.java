package exnihilo.common.blocks.tiles;

import exnihilo.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileInfestingLeaves extends TileEntity implements ITickable {

    private int counter = 15;
    private int percentage = 0;

    public IBlockState leafState = Blocks.LEAVES.getDefaultState();

    @Override
    public void update() {
        if (!world.isRemote) {
            if (counter == 0) {
                if (percentage == 100) {
                    world.setBlockState(pos, CommonProxy.infestedLeaves.getDefaultState());
                    return;
                }
                percentage++;
                counter = 15;
            }
            counter--;
        }
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }


    @Override
    public @Nonnull NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("percentage", percentage);
        if (leafState != Blocks.LEAVES.getDefaultState()) {
            compound.setString("LeafBlock", leafState.getBlock().getRegistryName().toString());
            compound.setInteger("LeafMeta", leafState.getBlock().getMetaFromState(leafState));
        }
        return super.writeToNBT(compound);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("LeafBlock") && compound.hasKey("LeafMeta")) {
            leafState = Block.getBlockFromName(compound.getString("LeafBlock")).getStateFromMeta(compound.getInteger("LeafMeta"));
        }
        percentage = compound.getInteger("percentage");
    }


    @Override
    @Nonnull
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public IBlockState getLeafState() {
        return leafState;
    }

    public void setLeafState(IBlockState state) {
        this.leafState = state;
    }
    public int getPercentage() {
        return percentage;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
}
