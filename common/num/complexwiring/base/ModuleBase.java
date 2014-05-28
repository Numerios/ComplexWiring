package num.complexwiring.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            if (dust == EnumDust.IRON) {
                GameRegistry.addSmelting(dust.getIS(1), new ItemStack(Items.iron_ingot), 0.6F);
            } else if (dust == EnumDust.GOLD) {
                GameRegistry.addSmelting(dust.getIS(1), new ItemStack(Items.gold_ingot), 0.6F);
            } else {
                GameRegistry.addSmelting(dust.getIS(1), EnumIngot.VALID[dust.meta - 2].getIS(1), 0.6F);
            }
        }

        for (EnumNugget nugget : EnumNugget.VALID) {
            if (nugget == EnumNugget.IRON) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(nugget.getIS(9), "ingotIron"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(Items.iron_ingot, nugget.name, nugget.name, nugget.name,
                        nugget.name, nugget.name, nugget.name, nugget.name, nugget.name, nugget.name));   //I'm so sorry
            } else {
                GameRegistry.addRecipe(new ShapelessOreRecipe(nugget.getIS(9), "ingot" + nugget.name.substring(6)));
                GameRegistry.addRecipe(new ShapelessOreRecipe(EnumIngot.VALID[nugget.meta - 1].getIS(1), nugget.name, nugget.name, nugget.name,
                        nugget.name, nugget.name, nugget.name, nugget.name, nugget.name, nugget.name));   //I'm so sorry (again)
            }
        }
    }
}
