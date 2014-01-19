package num.complexwiring.base;

import cpw.mods.fml.common.registry.GameRegistry;
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
        ingot = new ItemIngot(1025);
        ((ItemIngot) ingot).registerOres();
        GameRegistry.registerItem(ingot, ingot.getUnlocalizedName());

        dust = new ItemDust(1026);
        ((ItemDust) dust).registerOres();
        GameRegistry.registerItem(dust, dust.getUnlocalizedName());
    }

    private void registerRecipes() {
        for (EnumDust dust : EnumDust.VALID) {
            if (dust == EnumDust.IRON) {
                FurnaceRecipes.smelting().addSmelting(ModuleBase.dust.itemID, dust.meta, new ItemStack(Item.ingotIron), 0.6F);
            } else if (dust == EnumDust.GOLD) {
                FurnaceRecipes.smelting().addSmelting(ModuleBase.dust.itemID, dust.meta, new ItemStack(Item.ingotGold), 0.6F);
            } else {
                FurnaceRecipes.smelting().addSmelting(ModuleBase.dust.itemID, dust.meta, EnumIngot.VALID[dust.meta - 2].getIS(1), 0.6F);
            }
        }
    }
}
