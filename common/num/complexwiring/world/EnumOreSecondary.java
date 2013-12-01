package num.complexwiring.world;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumOreSecondary {
    CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, EnumOrePrimary.COPPER),
    CASSITERITE(Strings.ORE_CASSITERITE_NAME, EnumOrePrimary.TIN),
    ARGENTITE(Strings.ORE_ARGENTITE_NAME, EnumOrePrimary.SILVER);

    public static final EnumOreSecondary[] VALID = values();
    public final String name;
    public final EnumOrePrimary origin;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumOreSecondary(String name, EnumOrePrimary origin) {
        this.name = name;
        this.origin = origin;
    }

    public String getUnlocalizedName() {
        // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
        return name.toLowerCase().substring(3);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.oreSecondary, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }

}
