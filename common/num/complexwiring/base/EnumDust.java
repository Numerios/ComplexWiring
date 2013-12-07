package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumDust {
    COPPER(Strings.DUST_COPPER_NAME),
    TIN(Strings.DUST_TIN_NAME),
    SILVER(Strings.DUST_SILVER_NAME),
    LEAD(Strings.DUST_LEAD_NAME);

    public static final EnumDust[] VALID = values();
    public final String name;
    public final int meta = this.ordinal();
    public Icon icon;

    private EnumDust(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        // removes the "dust" from the name and makes it lowercase (ex. dustCopper -> copper)
        return ModuleBase.dust.getUnlocalizedName() + "." + name.toLowerCase().substring(4);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleBase.dust, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
