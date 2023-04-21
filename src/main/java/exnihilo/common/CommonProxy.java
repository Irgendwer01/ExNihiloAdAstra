package exnihilo.common;

import exnihilo.API.utils.data;
import exnihilo.Values;
import exnihilo.common.items.MetaItems;
import exnihilo.common.items.tools.HammerBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Values.modId)
public class CommonProxy {
    public static final CreativeTabs creativeTab = new CreativeTab();

    /////////////////////////////////////
    //                                 //
    //         ITEMS & BLOCKS          //
    //                                 //
    /////////////////////////////////////
    public static final Item metaItems = new MetaItems();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        data.ITEMS.add(new HammerBase("wooden_hammer", Item.ToolMaterial.WOOD, 0x896727));
        data.ITEMS.add(new HammerBase("stone_hammer", Item.ToolMaterial.STONE, 0x9a9a9a));
        data.ITEMS.add(new HammerBase("golden_hammer", Item.ToolMaterial.GOLD, 0xeaee57));
        data.ITEMS.add(new HammerBase("iron_hammer", Item.ToolMaterial.IRON, 0xFFFFFF));
        data.ITEMS.add(new HammerBase("diamond_hammer", Item.ToolMaterial.DIAMOND, 0x33ebcb));
        for (Item item : data.ITEMS) {
            e.getRegistry().register(item);
        }
    }

    public void onLoad() {
    }
    public void preInit() {

    }

    public void init() {

    }

    public void postInit() {

    }
}
