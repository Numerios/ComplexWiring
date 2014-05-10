package num.complexwiring;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.core.Logger;
import num.complexwiring.core.ModuleManager;
import num.complexwiring.core.proxy.CommonProxy;
import num.complexwiring.lib.Reference;
import num.complexwiring.power.electrical.NetworkTickHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)

public class ComplexWiring {

    public static final boolean DEBUG = true;
    @Instance(Reference.MOD_ID)
    public static ComplexWiring instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Logger.init();

        ModuleManager.preInit();
        //GameRegistry.registerPlayerTracker(new PlayerTracker());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {                                                               //  ass
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(NetworkTickHandler.INSTANCE);

        ModuleManager.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
