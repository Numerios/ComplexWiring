package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumIngot {
    COPPER(Strings.INGOT_COPPER_NAME, EnumMaterial.COPPER),
    TIN(Strings.INGOT_TIN_NAME, EnumMaterial.TIN),
    SILVER(Strings.INGOT_SILVER_NAME, EnumMaterial.SILVER),
    LEAD(Strings.INGOT_LEAD_NAME, EnumMaterial.LEAD);

    public static final EnumIngot[] VALID = values();
    public final EnumMaterial material;
    public final String name;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumIngot(String name, EnumMaterial material) {
        this.name = name;
        this.material = material;
    }

    public String getUnlocalizedName() {
        // removes the "ingot" from the name and makes it lowercase (ex. ingotCopper -> copper)
        return ModuleBase.ingot.getUnlocalizedName() + "." + name.toLowerCase().substring(5);
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleBase.ingot, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
