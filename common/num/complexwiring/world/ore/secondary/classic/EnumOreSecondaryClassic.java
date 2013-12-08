package num.complexwiring.world.ore.secondary.classic;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreSecondaryClassic {

    CHALCOCITE(Strings.ORE_CHALCOCITE_NAME, "oreCopper"),
    BORNITE(Strings.ORE_BORNITE_NAME, "oreCopper"),
    CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, "oreCopper"),
    MALACHITE(Strings.ORE_MALACHITE_NAME, "oreCopper"),

    CASSITERITE(Strings.ORE_CASSITERITE_NAME, "oreTin"),
    STANNITE(Strings.ORE_STANNITE_NAME, "oreTin"),

    ARGENTITE(Strings.ORE_ARGENTITE_NAME, "oreSilver"),
    CHLORARGYRITE(Strings.ORE_CHLORARGYRITE_NAME, "oreSilver"),

    GALENA(Strings.ORE_GALENA_NAME, "oreLead"),
    CERUSSITE(Strings.ORE_CERUSSITE_NAME, "oreLead"),
    ANGLESITE(Strings.ORE_ANGLESITE_NAME, "oreLead");

    public static final EnumOreSecondaryClassic[] VALID = values();
    public final String name;
    public final String origin;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumOreSecondaryClassic(String name, String oreDictOrigin) {
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
        return new ItemStack(ModuleWorld.oreSecondaryClassic, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}
