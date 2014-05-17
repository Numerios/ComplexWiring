package num.complexwiring.world.ore.secondary.rare;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreSecondaryRare {
    BAUXITE(Strings.ORE_BAUXITE_NAME, "oreAluminium"),
    CRYOLITE(Strings.ORE_CRYOLITE_NAME, "oreAluminium"),

    URANINITE(Strings.ORE_URANINITE_NAME, "oreUranium"),
    AUTUNITE(Strings.ORE_AUTUNITE_NAME, "oreUranium"),

    WOLFRAMITE(Strings.ORE_WOLFRAMITE_NAME, "oreTungsten"),
    SCHEELITE(Strings.ORE_SCHEELITE_NAME, "oreTungsten"),

    ILMENITE(Strings.ORE_ILMENITE_NAME, "oreTitanium"),
    RUTILE(Strings.ORE_RUTILE_NAME, "oreTitanium"),

    CALAVERITE(Strings.ORE_CALAVERITE_NAME, "oreGold"),
    SYLVANITE(Strings.ORE_SYLVANITE_NAME, "oreGold"),
    PUREGOLD(Strings.ORE_PUREGOLD_NAME, "oreGold"),

    LITHIUM(Strings.ORE_LITHIUM_NAME, "clay"),

    LAZURITE(Strings.ORE_LAZURITE_NAME, "oreLapis"),

    BERYL(Strings.ORE_BERYL_NAME, "oreEmerald");

    public static final EnumOreSecondaryRare[] VALID = values();
    public final String name;
    public final String origin;
    public final int meta = this.ordinal();
    public IIcon icon;

    EnumOreSecondaryRare(String name, String oreDictOrigin) {
        this.name = name;
        this.origin = oreDictOrigin;
    }

    public String getUnlocalizedName() {
        // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
        return name.toLowerCase().substring(3);
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/secondary/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.oreSecondaryRare, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
