package exnihilo.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static exnihilo.common.CommonProxy.metaItems;

public class CreativeTab extends CreativeTabs {
    CreativeTab() {
        super("exnihilo");
    }
    @Override
    public ItemStack createIcon() {
        return metaItems.getDefaultInstance();
    }
}
