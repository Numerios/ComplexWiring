package num.complexwiring.world.ore;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreSecondary {

    CHALCOCITE(Strings.ORE_CHALCOCITE_NAME, "oreCopper"),
    BORNITE(Strings.ORE_BORNITE_NAME, "oreCopper"),
    CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, "oreCopper"),
    MALACHITE(Strings.ORE_MALACHITE_NAME, "oreCopper"),

    CASSITERITE(Strings.ORE_CASSITERITE_NAME, "oreTin"),
    STANNITE(Strings.ORE_STANNITE_NAME, "oreTin"),

    ARGENTITE(Strings.ORE_ARGENTITE_NAME, "oreSilver"),
    CHLORARGYTE(Strings.ORE_CHLORARGYTE_NAME, "oreSilver"),

    GALENITE(Strings.ORE_GALENITE_NAME, "oreLead"),
    CERUSSITE(Strings.ORE_CERUSSITE_NAME, "oreLead"),
    ANGLESITE(Strings.ORE_ANGLESITE_NAME, "oreLead"),

    BAUXITE(Strings.ORE_BAUXITE_NAME, "oreAluminium"),
    CRYOLITE(Strings.ORE_CRYOLITE_NAME, "oreAluminium"),

    URANITE(Strings.ORE_URANINITE_NAME, "oreUranium"),
    AUTUNITE(Strings.ORE_AUTUNITE_NAME, "oreUranium"),

    WOLFRAMITE(Strings.ORE_WOLFRAMITE_NAME, "oreTungsten"),
    SCHEELITE(Strings.ORE_SCHEELITE_NAME, "oreTungsten"),

    ILMENITE(Strings.ORE_ILMENITE_NAME, "oreTitanium"),
    RUTILE(Strings.ORE_RUTILE_NAME, "oreTitanium"),

    CALAVERITE(Strings.ORE_CALAVERITE_NAME, "oreGold"),
    SYLVANITE(Strings.ORE_SYLVANITE_NAME, "oreGold"),
    PUREGOLD(Strings.ORE_PUREGOLD_NAME, "oreGold"),

    MAGNETITE(Strings.ORE_MAGNETITE_NAME, "oreIron"),
    HEMATITE(Strings.ORE_HEMATITE_NAME, "oreIron"),
    SIDERITE(Strings.ORE_SIDERITE_NAME, "oreIron"),
    CHROMITE(Strings.ORE_CHROMITE_NAME, "oreIron"),
    PENTLADITE(Strings.ORE_PENTLADITE_NAME, "oreIron"),

    PYROLUSITE(Strings.ORE_PYROLUSITE_NAME, "stone"),
    CINNABAR(Strings.ORE_CINNABAR_NAME, "stone"),
    DOLOMITE(Strings.ORE_DOLOMITE_NAME, "stone"),
    SPHALERITE(Strings.ORE_SPHALERITE_NAME, "stone"),
    LIMESTONE(Strings.ORE_LIMESTONE_NAME, "stone"),
    LIME(Strings.ORE_LIME_NAME, "stone"),
    COBALTITE(Strings.ORE_COBALTITE_NAME, "stone"),

    LITHIUM(Strings.ORE_LITHIUM_NAME, "clay"),

    LAZURITE(Strings.ORE_LAZURITE_NAME, "oreLapis"),

    BERYL(Strings.ORE_BERYL_NAME, "oreEmerald"),

    LIGNITE(Strings.ORE_LIGNITE_NAME, "oreCoal"),
    SUBBITUMINOUS(Strings.ORE_SUBBITUMINOUS_NAME, "oreCoal"),
    BITUMINOUS(Strings.ORE_BITUMINOUS_NAME, "oreCoal"),
    ANTHRACITE(Strings.ORE_ANTHRACITE_NAME, "oreCoal");

    public static final EnumOreSecondary[] VALID = values();
    public final String name;
    public final String origin;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumOreSecondary(String name, String oreDictOrigin) {
        this.name = name;
        this.origin = oreDictOrigin;
    }

    public String getUnlocalizedName() {
        // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
        return name.toLowerCase().substring(3);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/secondary/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.oreSecondary, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }

}
