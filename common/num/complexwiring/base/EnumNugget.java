package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumNugget {
    IRON(Strings.NUGGET_IRON_NAME, EnumMaterial.IRON),
    COPPER(Strings.NUGGET_COPPER_NAME, EnumMaterial.COPPER),
    TIN(Strings.NUGGET_TIN_NAME, EnumMaterial.TIN),
    SILVER(Strings.NUGGET_SILVER_NAME, EnumMaterial.SILVER),
    LEAD(Strings.NUGGET_LEAD_NAME, EnumMaterial.LEAD);

    public static final EnumNugget[] VALID = values();
    public final EnumMaterial material;
    public final String name;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumNugget(String name, EnumMaterial material) {
        this.name = name;
        this.material = material;
    }

    public String getUnlocalizedName() {
        // removes the "nugget" from the name and makes it lowercase (ex. nuggetCopper -> copper)
        return ModuleBase.nugget.getUnlocalizedName() + "." + name.toLowerCase().substring(6);
    }

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
