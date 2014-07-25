package num.complexwiring.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.core.Reference;
import num.complexwiring.core.Strings;

public enum EnumDust {
    IRON(Strings.DUST_IRON_NAME),
    GOLD(Strings.DUST_GOLD_NAME),
    COPPER(Strings.DUST_COPPER_NAME),
    TIN(Strings.DUST_TIN_NAME),
    SILVER(Strings.DUST_SILVER_NAME),
    LEAD(Strings.DUST_LEAD_NAME),
    NICKEL(Strings.DUST_NICKEL_NAME),
    ALUMINIUM(Strings.DUST_ALUMINIUM_NAME),
    TUNGSTEN(Strings.DUST_TUNGSTEN_NAME),
    TITANIUM(Strings.DUST_TITANIUM_NAME),
    ZINC(Strings.DUST_ZINC_NAME),
    COBALT(Strings.DUST_COBALT_NAME);

    public static final EnumDust[] VALID = values();
    public final String name;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumDust(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        // removes the "dust" from the name and makes it lowercase (ex. dustCopper -> copper)
        return ModuleBase.dust.getUnlocalizedName() + "." + name.toLowerCase().substring(4);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleBase.dust, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
