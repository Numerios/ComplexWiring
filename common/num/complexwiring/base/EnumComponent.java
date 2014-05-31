package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumComponent {
    WOOD_PULP(Strings.WOOD_PULP_NAME);

    public static final EnumComponent[] VALID = values();

    public final String name;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumComponent(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        return ModuleBase.component.getUnlocalizedName() + "." + name.toLowerCase();
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleBase.component, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
