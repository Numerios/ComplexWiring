package num.complexwiring.world.ore.primary;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOrePrimary {
    COPPER(Strings.ORE_COPPER_NAME),
    TIN(Strings.ORE_TIN_NAME),
    SILVER(Strings.ORE_SILVER_NAME),
    LEAD(Strings.ORE_LEAD_NAME),
    ALUMINIUM(Strings.ORE_ALUMINIUM_NAME),
    URANIUM(Strings.ORE_URANIUM_NAME),
    TUNGSTEN(Strings.ORE_TUNGSTEN_NAME),
    TITANIUM(Strings.ORE_TITANIUM_NAME);

    public static final EnumOrePrimary[] VALID = values();
    public final String name;
    public final int meta = this.ordinal();
    public Icon icon;

    private EnumOrePrimary(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
        return name.toLowerCase().substring(3);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/primary/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.orePrimary, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
    }
}

