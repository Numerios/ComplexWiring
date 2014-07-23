package num.complexwiring.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumNugget {
    IRON(Strings.NUGGET_IRON_NAME),
    COPPER(Strings.NUGGET_COPPER_NAME),
    TIN(Strings.NUGGET_TIN_NAME),
    SILVER(Strings.NUGGET_SILVER_NAME),
    LEAD(Strings.NUGGET_LEAD_NAME),
    NICKEL(Strings.NUGGET_NICKEL_NAME),
    ALUMINIUM(Strings.NUGGET_ALUMINIUM_NAME),
    TUNGSTEN(Strings.NUGGET_TUNGSTEN_NAME),
    TITANIUM(Strings.NUGGET_TITANIUM_NAME),
    ZINC(Strings.NUGGET_ZINC_NAME),
    COBALT(Strings.NUGGET_COBALT_NAME);

    public static final EnumNugget[] VALID = values();
    public final String name;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumNugget(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        // removes the "nugget" from the name and makes it lowercase (ex. nuggetCopper -> copper)
        return ModuleBase.nugget.getUnlocalizedName() + "." + name.toLowerCase().substring(6);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleBase.nugget, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
