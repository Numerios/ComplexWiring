package num.complexwiring;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.core.Logger;
import num.complexwiring.core.ModuleManager;
import num.complexwiring.core.proxy.CommonProxy;
import num.complexwiring.lib.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ComplexWiring {

    public static final boolean DEBUG = true;

    @Instance(Reference.MOD_ID)
    public static ComplexWiring instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabCW tabCW = new CreativeTabCW("tabCW", null);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Logger.init();
        ModuleManager.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
