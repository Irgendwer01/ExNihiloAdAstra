package exnihilo;

import exnihilo.common.CommonProxy;
import exnihilo.compat.TOPHandler;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.apiimpl.TheOneProbeImp;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = "exnihilo", name = "Ex Nihilo Ad Astra", version = "1.0", dependencies =
        "after:crafttweaker;" +
        "after:groovyscript;" +
        "after:jei")
public class ExNihilo {

    @SidedProxy(clientSide = "exnihilo.client.ClientProxy", serverSide = "exnihilo.common.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger("exnihilo");

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
        if (Loader.isModLoaded("theoneprobe")) {
            ITheOneProbe top = TheOneProbe.theOneProbeImp;
            top.registerProvider(new TOPHandler());
        }
    }
}
