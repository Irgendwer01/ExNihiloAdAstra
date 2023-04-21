package exnihilo.common.items;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.common.entities.PebbleEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class MetaItems extends Item implements IModelRender {

    private final ArrayList<String> meta_items = new ArrayList<>();
    public MetaItems() {
        super();
        setTranslationKey("meta_item");
        setRegistryName("meta_item");
        setCreativeTab(creativeTab);
        setHasSubtypes(true);
        meta_items.add(0, "silkworm");
        meta_items.add(1, "porcelain_clay");
        meta_items.add(2, "grass_seeds");
        meta_items.add(3, "ancient_spores");
        meta_items.add(4, "porcelain_doll");
        meta_items.add(5, "stone_pebble");
        meta_items.add(6, "andesite_pebble");
        meta_items.add(7, "diorite_pebble");
        meta_items.add(8, "granite_pebble");
        data.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslationKey(ItemStack stack) {
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        switch (stack.getMetadata()) {
            case (5):
            case (6):
            case (7):
            case (8): {
                ItemStack pebble = stack.copy();
                pebble.setCount(1);
                PebbleEntity projectile = new PebbleEntity(worldIn, player);
                projectile.setStack(pebble);
                projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.5F);
                worldIn.spawnEntity(projectile);
                stack.shrink(1);
                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        ItemStack stack = player.getHeldItem(hand);
        switch (stack.getMetadata()) {
            case (2): {
                if (block == Blocks.DIRT) {
                    worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
                    stack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
                break;
            }
            case (3): {
                if (block == Blocks.DIRT) {
                    worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                    stack.shrink(1);
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
