package exnihilo.client;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.ExNihilo;
import exnihilo.client.model.InfestedLeavesBakedModel;
import exnihilo.client.render.RenderInfestingLeaves;
import exnihilo.client.render.renderProjectilePebble;
import exnihilo.common.CommonProxy;
import exnihilo.common.blocks.tiles.TileInfestingLeaves;
import exnihilo.common.entities.ProjectilePebble;
import exnihilo.common.items.tools.HammerBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static exnihilo.ExNihilo.logger;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {


    /////////////////////////////////////
    //                                 //
    //            RENDERING            //
    //                                 //
    /////////////////////////////////////

    public static final IItemColor HAMMER_COLOR = (stack, tintIndex) -> {
        if (stack.getItem() instanceof HammerBase && tintIndex == 1) {
            return ((HammerBase) stack.getItem()).getColor();
        }
        return 0xFFFFFF;
    };
    @SubscribeEvent
    public static void registerColors(ColorHandlerEvent.Item e) {
        for (Item item : data.ITEMS) {
            if (item instanceof HammerBase) {
                e.getItemColors().registerItemColorHandler(HAMMER_COLOR, item);
            }
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        for (Item item : data.ITEMS) {
            if (item instanceof IModelRender) {
                ((IModelRender) item).initModel(e);
            }
        }
        for (Block block : data.BLOCKS) {
            if (block instanceof IModelRender) {
                ((IModelRender) block).initModel(e);
            }
        }
        RenderingRegistry.registerEntityRenderingHandler(ProjectilePebble.class, new renderProjectilePebble.Factory());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfestingLeaves.class, new RenderInfestingLeaves());
        ModelLoader.setCustomStateMapper(infestedLeaves, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return InfestedLeavesBakedModel.variantTag;
            }
        });
    }

    @Override
    public void onLoad() {
        logger.log(org.apache.logging.log4j.Level.INFO, "test");
        super.onLoad();
    }

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
