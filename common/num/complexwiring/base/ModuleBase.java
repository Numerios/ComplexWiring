package num.complexwiring.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;

public class ModuleBase extends Module {
    public static Item ingot, dust, nugget, screwdriver;
    public static CreativeTabCW tabCWBase = new CreativeTabCW("tabCWBase", null);

    @Override
    public void preInit() {
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerItems() {
        ingot = new ItemIngot();
        ((ItemIngot) ingot).registerOres();
        GameRegistry.registerItem(ingot, ingot.getUnlocalizedName());

        dust = new ItemDust();
        ((ItemDust) dust).registerOres();
        GameRegistry.registerItem(dust, dust.getUnlocalizedName());

        nugget = new ItemNugget();
        ((ItemNugget) nugget).registerOres();
        GameRegistry.registerItem(nugget, nugget.getUnlocalizedName());

        screwdriver = new ItemScrewdriver();
        ((ItemScrewdriver) screwdriver).registerOres();
        GameRegistry.registerItem(screwdriver, screwdriver.getUnlocalizedName());
    }

    private void registerRecipes() {
        for (EnumDust dust : EnumDust.VALID) {
            GameRegistry.addSmelting(dust.getIS(1), EnumMaterial.getMaterial(dust).getIngot(), 0.6F);
        }

        for (EnumNugget nugget : EnumNugget.VALID) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(nugget.getIS(9), EnumMaterial.getMaterial(nugget).getIngot()));
            GameRegistry.addRecipe(new ShapelessOreRecipe(EnumMaterial.getMaterial(nugget).getIngot().copy(), nugget.name, nugget.name, nugget.name,
                    nugget.name, nugget.name, nugget.name, nugget.name, nugget.name, nugget.name));   //I'm so sorry
        }
    }
}
