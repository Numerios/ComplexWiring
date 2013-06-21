package num.complexwiring;

import num.complexwiring.lib.Reference;
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
    }

    @Init
    public void init(FMLInitializationEvent event) {
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}
