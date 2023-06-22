package exnihilo.common;

import exnihilo.API.utils.data;
import exnihilo.Values;
import exnihilo.common.blocks.BlockInfestedLeaves;
import exnihilo.common.blocks.BlockInfestingLeaves;
import exnihilo.common.blocks.tiles.TileInfestingLeaves;
import exnihilo.common.entities.ProjectilePebble;
import exnihilo.common.items.MetaItems;
import exnihilo.common.items.tools.HammerBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Values.modID)
public class CommonProxy {
    public static final CreativeTabs creativeTab = new CreativeTab();

    /////////////////////////////////////
    //                                 //
    //         ITEMS & BLOCKS          //
    //                                 //
    /////////////////////////////////////
    public static final Item metaItems = new MetaItems();
    public static final Block infestingLeaves = new BlockInfestingLeaves();
    public static final Block infestedLeaves = new BlockInfestedLeaves();

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
        for (Block block : data.BLOCKS) {
            e.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        for (Block block : data.BLOCKS) {
            e.getRegistry().register(block);
        }
        GameRegistry.registerTileEntity(TileInfestingLeaves.class, new ResourceLocation("exnihilo:tile_infested_leaves"));
    }

    @SubscribeEvent
    public static void registerEnitities(RegistryEvent.Register<EntityEntry> e) {
        e.getRegistry().register(EntityEntryBuilder.create()
                .entity(ProjectilePebble.class)
                .id(new ResourceLocation("exnihilo:pebble_entity"), 1)
                .name("pebble_entity")
                .tracker(50, 30, true)
                .build());
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
