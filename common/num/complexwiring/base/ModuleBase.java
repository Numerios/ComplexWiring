package num.complexwiring.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;

public class ModuleBase extends Module {
    public static Item ingot, dust;
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
    }

    private void registerRecipes() {
        for (EnumDust dust : EnumDust.VALID) {
            if (dust == EnumDust.IRON) {
                FurnaceRecipes.smelting().func_151394_a(EnumDust.VALID[dust.meta].getIS(1), new ItemStack(Items.iron_ingot), 0.6F);
            } else if (dust == EnumDust.GOLD) {
                FurnaceRecipes.smelting().func_151394_a(EnumDust.VALID[dust.meta].getIS(1), new ItemStack(Items.gold_ingot), 0.6F);
            } else {
                FurnaceRecipes.smelting().func_151394_a(EnumDust.VALID[dust.meta].getIS(1), EnumIngot.VALID[dust.meta - 2].getIS(1), 0.6F);
            }
        }
    }
}
