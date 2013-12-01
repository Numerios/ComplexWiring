package num.complexwiring;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.core.*;
import num.complexwiring.core.proxy.CommonProxy;
import num.complexwiring.lib.Reference;
import num.complexwiring.recipe.RecipeManager;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {PacketHandler.CHANNEL}, packetHandler = PacketHandler.class)

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

        ModuleManager.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

        ModuleManager.init();

        RecipeManager.addAllRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
