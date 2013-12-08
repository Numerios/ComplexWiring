package num.complexwiring.world.ore.secondary.vanilla;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreSecondaryVanilla {

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

    LIGNITE(Strings.ORE_LIGNITE_NAME, "oreCoal"),
    SUBBITUMINOUS(Strings.ORE_SUBBITUMINOUS_NAME, "oreCoal"),
    BITUMINOUS(Strings.ORE_BITUMINOUS_NAME, "oreCoal"),
    ANTHRACITE(Strings.ORE_ANTHRACITE_NAME, "oreCoal");

    public static final EnumOreSecondaryVanilla[] VALID = values();
    public final String name;
    public final String origin;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumOreSecondaryVanilla(String name, String oreDictOrigin) {
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
        return new ItemStack(ModuleWorld.oreSecondaryVanilla, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
