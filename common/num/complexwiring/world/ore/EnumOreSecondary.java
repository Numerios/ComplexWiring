package num.complexwiring.world.ore;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreSecondary {
    CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, "oreCopper"),
    CASSITERITE(Strings.ORE_CASSITERITE_NAME, "oreTin"),
    ARGENTITE(Strings.ORE_ARGENTITE_NAME, "oreSilver"),
    GALENA(Strings.ORE_GALENA_NAME, "oreLead"),
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
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.oreSecondary, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }

}
