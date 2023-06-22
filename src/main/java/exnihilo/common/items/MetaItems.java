package exnihilo.common.items;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.common.CommonProxy;
import exnihilo.common.entities.ProjectilePebble;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;

import static exnihilo.common.CommonProxy.creativeTab;
import static exnihilo.common.blocks.BlockInfestingLeaves.infest;

public class MetaItems extends Item implements IModelRender {

    private final ArrayList<String> meta_items = new ArrayList<>();
    public MetaItems() {
        super();
        setTranslationKey("meta_item");
        setRegistryName("meta_item");
        setCreativeTab(creativeTab);
        setHasSubtypes(true);
        meta_items.add(0, "silkworm");
        meta_items.add(1, "grass_seeds");
        meta_items.add(2, "ancient_spores");
        meta_items.add(3, "porcelain_doll");
        meta_items.add(4, "stone_pebble");
        meta_items.add(5, "andesite_pebble");
        meta_items.add(6, "diorite_pebble");
        meta_items.add(7, "granite_pebble");
        data.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @Nonnull String getTranslationKey(ItemStack stack) {
        return getTranslationKey() + "." + meta_items.get(stack.getItemDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab))
            for (int i = 1; i < meta_items.size(); i++) {
                list.add(new ItemStack(this, 1, i));
            }
    }

    @Override
    public @Nonnull ActionResult<ItemStack> onItemRightClick( @Nonnull World worldIn, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        switch (stack.getMetadata()) {
            case (5):
            case (6):
            case (7):
            case (8): {
                ProjectilePebble projectile = new ProjectilePebble(worldIn, player);
                projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.5F);
                if (worldIn.isRemote) {
                    break;
                }
                worldIn.spawnEntity(projectile);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                player.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 0.6F, 1);
                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    public @Nonnull EnumActionResult onItemUse(EntityPlayer player, World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        ItemStack stack = player.getHeldItem(hand);
        switch (stack.getMetadata()) {
            case (0): {
                if ((worldIn.getBlockState(pos) != CommonProxy.infestingLeaves.getDefaultState() || worldIn.getBlockState(pos) != CommonProxy.infestedLeaves.getDefaultState()) && worldIn.getBlockState(pos).getBlock().isLeaves(worldIn.getBlockState(pos), worldIn, pos)) {
                    infest(pos, worldIn);
                }
            }
            case (2): {
                if (block == Blocks.DIRT || block == Blocks.MYCELIUM) {
                    worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                }
                break;
            }
            case (3): {
                if (block == Blocks.DIRT || block == Blocks.GRASS) {
                    worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                }
                break;
            }
        }
        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    public void initModel(ModelRegistryEvent e) {
        for (int i = 0; i < meta_items.size(); i++) {
            String variant = "type=" + meta_items.get(i);

            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName(), variant));
        }
    }
}
