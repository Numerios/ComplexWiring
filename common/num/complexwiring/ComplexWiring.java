package num.complexwiring;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.core.ConfigHandler;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.core.Logger;
import num.complexwiring.core.module.ModuleManager;
import num.complexwiring.core.proxy.CommonProxy;
import num.complexwiring.core.Reference;
import num.complexwiring.mechanical.ModuleMechanical;
import num.complexwiring.world.ModuleWorld;

import java.io.File;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_CONFIG_CLASS)
public class ComplexWiring {
    public static final boolean DEBUG = (Reference.MOD_VERSION.contains("alpha") || Reference.MOD_VERSION.contains("VERSION"));
    @Instance(Reference.MOD_ID)
    public static ComplexWiring instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Logger.init();
        Logger.debug("Let's do this!");

        ConfigHandler.init(new File(event.getModConfigurationDirectory(), "/ComplexWiring.cfg"));

        ModuleManager.INSTANCE.register(new ModuleBase());
        //  ModuleManager.INSTANCE.register(new ModuleTablet());
        ModuleManager.INSTANCE.register(new ModuleWorld());
        ModuleManager.INSTANCE.register(new ModuleMechanical());

        ModuleManager.INSTANCE.preInit();

        // FMLCommonHandler.instance().bus().register(new PlayerEventHandler());
        FMLCommonHandler.instance().bus().register(instance);

        Logger.debug("PreInit finished!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Logger.debug("Init started!");
        proxy.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        ModuleManager.INSTANCE.init();
        ConfigHandler.save();
        Logger.debug("Init finished!");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Logger.debug("PostInit started!");

        ModuleManager.INSTANCE.postInit();
        Logger.debug("I'm ready to rock!");
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Reference.MOD_ID)) ConfigHandler.reloadConfig();
    }
}
