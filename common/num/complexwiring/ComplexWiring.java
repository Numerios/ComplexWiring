package num.complexwiring;

import num.complexwiring.block.ModBlocks;
import num.complexwiring.lib.Reference;
import num.complexwiring.recipe.ModCrafting;
import num.complexwiring.recipe.ModSmelting;
import num.complexwiring.item.ModItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ComplexWiring {

    @Instance(Reference.MOD_ID)
    public static ComplexWiring instance;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        // initialize all blocks and items
        ModItems.init();
        ModBlocks.init();
        
        // initialize all crafting and smelting recipes
        ModCrafting.init();
        ModSmelting.init();        
    }

    @Init
    public void init(FMLInitializationEvent event) {
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}
