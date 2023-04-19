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

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {


    /////////////////////////////////////
    //                                 //
    //            RENDERING            //
    //                                 //
    /////////////////////////////////////

    public static final IItemColor HAMMER_COLOR = (stack, tintIndex) -> tintIndex == 1 ? ((HammerBase) stack.getItem()).getColor() : 0xFFFFFF;

    @SubscribeEvent
    public static void registerColors(ColorHandlerEvent.Item e) {
        for (Item item : data.ITEMS) {
            if (item instanceof HammerBase) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(HAMMER_COLOR, item);
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
