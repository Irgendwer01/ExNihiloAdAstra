package exnihilo.common.blocks;

import exnihilo.API.utils.data;
import exnihilo.common.CommonProxy;
import exnihilo.common.blocks.tiles.TileInfestingLeaves;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockInfestingLeaves extends BlockLeaves implements ITileEntityProvider {

    public BlockInfestingLeaves() {
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(CHECK_DECAY, true)
                .withProperty(DECAYABLE, true));
        this.setRegistryName("infesting_leaves");
        this.setTranslationKey("infesting_leaves");
        this.setCreativeTab(CommonProxy.creativeTab);
        this.setTickRandomly(true);
        this.leavesFancy = true;
        data.BLOCKS.add(this);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (!state.getValue(DECAYABLE)) {
            meta |= 1;
        }
        if (state.getValue(CHECK_DECAY)) {
            meta |= 2;
        }
        return meta;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(DECAYABLE, (meta & 1) == 0)
                .withProperty(CHECK_DECAY, (meta & 2) > 0);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        this.updateTick(worldIn, pos, state, random);
        spread(pos, worldIn, random);
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    public static void spread(BlockPos pos, World world, Random random) {
        int rand = random.nextInt(26);
        Iterable<BlockPos> mutableBlockPos = BlockPos.MutableBlockPos.getAllInBox(new BlockPos(pos.getX()-1, pos.getY()-1, pos.getZ()-1), new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+1));
        ArrayList<BlockPos> blockPosList = new ArrayList<>();
        for (BlockPos blockPos : mutableBlockPos) {
            blockPosList.add(blockPos);
        }
        if (blockPosList.get(rand) != null) {
            IBlockState blockState = world.getBlockState(blockPosList.get(rand));
            if ((blockState != CommonProxy.infestingLeaves.getDefaultState() || blockState != CommonProxy.infestedLeaves.getDefaultState()) && blockState.getBlock().isLeaves(blockState, world, blockPosList.get(rand))) {
                infest(blockPosList.get(rand), world);
            }
        }
    }

    public static void infest(BlockPos pos, World world) {
        IBlockState leafState = world.getBlockState(pos);
        world.setBlockState(pos, CommonProxy.infestingLeaves.getDefaultState());
        if (world.getTileEntity(pos) != null) {
            ((TileInfestingLeaves) world.getTileEntity(pos)).setLeafState(leafState);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileInfestingLeaves();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

}
