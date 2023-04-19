package exnihilo.common.items;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
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

    private ArrayList<String> meta_items = new ArrayList<>();
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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        ItemStack stack = player.getHeldItem(hand);
        switch (stack.getMetadata()) {
            case (2):
                if (block == Blocks.DIRT) {
                    worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
                    stack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
            case (3):
                if (block == Blocks.MYCELIUM) {
                    worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                    stack.shrink(1);
                    return EnumActionResult.SUCCESS;
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
