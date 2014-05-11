package num.complexwiring.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Strings;

public class ModuleTools extends ModuleBase {
    public static Item toolSaw, toolSmasher;
    public static CreativeTabCW tabCWTools = new CreativeTabCW("tabCWTools", null);
    public static Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial("CWTOOLMATERIAL", 3, 32, 5.0F, 1.5F, 0);

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
        tabCWTools.setIcon(new ItemStack(toolSaw));
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
    }

    private void registerItems() {
        toolSaw = new ItemToolSaw();
        GameRegistry.registerItem(toolSaw, Strings.SAW_NAME);

        toolSmasher = new ItemToolSmasher();
        GameRegistry.registerItem(toolSmasher, Strings.SMASHER_NAME);
    }

    private void registerRecipes() {
        GameRegistry.addRecipe(new ItemStack(toolSaw),
                "SII",
                "SFI",
                "S  ",
                'S', new ItemStack(Items.stick),
                'I', new ItemStack(Items.iron_ingot),
                'F', new ItemStack(Items.flint)
        );

        GameRegistry.addRecipe(new ItemStack(toolSaw),
                "IIS",
                "IFS",
                "  S",
                'S', new ItemStack(Items.stick),
                'I', new ItemStack(Items.iron_ingot),
                'F', new ItemStack(Items.flint)
        );

        for (String log : new String[]{"log", "wood", "logWood"}) {
            for (ItemStack logIS : OreDictionary.getOres(log)) {
                if (logIS == null) {
                    continue;
                }
                GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 6), new ItemStack(toolSaw, 1, OreDictionary.WILDCARD_VALUE), logIS);
            }
        }

        GameRegistry.addRecipe(new ItemStack(toolSmasher),
                "IGI",
                "IFI",
                " S ",
                'S', new ItemStack(Items.stick),
                'I', new ItemStack(Items.iron_ingot),
                'F', new ItemStack(Items.flint),
                'G', new ItemStack(Items.gold_ingot)
        );

      /*  for (String ore : new String[] {"oreIron", "oreGold", "oreCopper", "oreTin", "oreSilver", "oreLead"}) {
            int n = 0;
            for (ItemStack oreIS : OreDictionary.getOres(ore)) {
                if (oreIS == null){
                    continue;
                }
                System.out.println("Adding" + oreIS.toString());
                GameRegistry.addShapelessRecipe(EnumDust.VALID[n].getIS(2), new ItemStack(toolSaw, 1, OreDictionary.WILDCARD_VALUE),
                        oreIS);
                n++;
            }
        }       */
    }
}
