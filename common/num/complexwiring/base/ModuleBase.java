package num.complexwiring.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import num.complexwiring.ComplexWiring;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.core.module.Module;

public class ModuleBase extends Module {
    public static Item ingot, dust, nugget, screwdriver, component, debugger;
    public static CreativeTabCW tabCWBase = new CreativeTabCW("tabCWBase", null);

    @Override
    public void preInit() {
        registerItems();
    }

    @Override
    public void init() {
        registerVanillaOreDict();
        registerRecipes();
    }

    private void registerVanillaOreDict() {
        OreDictionary.registerOre("ingotIron", Items.iron_ingot);
        OreDictionary.registerOre("ingotGold", Items.gold_ingot);
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

        if (ComplexWiring.DEBUG) {
            debugger = new ItemDebugger();
            ((ItemDebugger) debugger).registerOres();
            GameRegistry.registerItem(debugger, debugger.getUnlocalizedName());
        }

        component = new ItemComponent();
        ((ItemComponent) component).registerOres();
        GameRegistry.registerItem(component, component.getUnlocalizedName());
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

        GameRegistry.addShapedRecipe(new ItemStack(Items.paper, 2), "###", '#', EnumComponent.WOOD_PULP.getIS(1));
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.planks), "#", "#", '#', EnumComponent.WOOD_PULP.getIS(1)); //wood - 4 plank
        GameRegistry.addShapedRecipe(new ItemStack(Items.stick), "#", '#', EnumComponent.WOOD_PULP.getIS(1));     //wood - 8 stick

        GameRegistry.addRecipe(new ShapedOreRecipe(screwdriver, "F  ", " S ", "  C", 'F', Items.flint, 'S', Items.stick, 'C', "cobblestone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(screwdriver, "  F", " S ", "C  ", 'F', Items.flint, 'S', Items.stick, 'C', "cobblestone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(screwdriver, "F", "S", "C", 'F', Items.flint, 'S', Items.stick, 'C', "cobblestone"));
    }
}
