package num.complexwiring;

import net.minecraft.creativetab.CreativeTabs;
import num.complexwiring.block.ModBlocks;
import num.complexwiring.core.Logger;
import num.complexwiring.core.proxy.CommonProxy;
import num.complexwiring.creativetab.ModCreativeTabs;
import num.complexwiring.item.ModItems;
import num.complexwiring.lib.Reference;
import num.complexwiring.recipe.ModCrafting;
import num.complexwiring.recipe.ModSmelting;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ComplexWiring {

    @Instance(Reference.MOD_ID)
    public static ComplexWiring instance;

    public static CreativeTabs tabCW = new ModCreativeTabs(CreativeTabs.getNextID(), Reference.MOD_ID);

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    public static final boolean DEBUG = true;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Logger.init();

        // initialize all blocks and items
        ModItems.init();
        ModBlocks.init();

        // initialize all crafting and smelting recipes
        ModCrafting.init();
        ModSmelting.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.initRendering();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
