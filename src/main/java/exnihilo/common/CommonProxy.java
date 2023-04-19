package exnihilo.common;

import exnihilo.API.utils.data;
import exnihilo.Values;
import exnihilo.common.items.MetaItems;
import exnihilo.common.items.tools.HammerBase;
import net.minecraft.creativetab.CreativeTabs;
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
        new HammerBase("wooden_hammer", Item.ToolMaterial.WOOD, 0x643200);
        new HammerBase("stone_hammer", Item.ToolMaterial.STONE, 0xcdcdcd);
        new HammerBase("golden_hammer", Item.ToolMaterial.GOLD, 0xffe650);
        new HammerBase("iron_hammer", Item.ToolMaterial.IRON, 0xc8c8c8);
        new HammerBase("diamond_hammer", Item.ToolMaterial.DIAMOND, 0xc8ffff);
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
