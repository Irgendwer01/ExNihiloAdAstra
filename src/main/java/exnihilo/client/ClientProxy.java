package exnihilo.client;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.common.CommonProxy;
import exnihilo.common.items.tools.HammerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
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
