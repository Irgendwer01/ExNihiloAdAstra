package exnihilo;

import exnihilo.client.ClientProxy;
import exnihilo.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "exnihilo", name = "Ex Nihilo Ad Astra", version = "1.0", dependencies =
        "after:crafttweaker;" +
        "after:groovyscript;" +
        "after:jei")
public class ExNihilo {

    @SidedProxy(clientSide = "exnihilo.client.ClientProxy", serverSide = "exnihilo.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onLoad(FMLConstructionEvent e) {
        proxy.onLoad();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent e) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit();
    }
}
