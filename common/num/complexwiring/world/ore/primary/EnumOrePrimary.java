package num.complexwiring.world.ore.primary;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOrePrimary {
    COPPER(Strings.ORE_COPPER_NAME, 0, 64, 5, 15),
    TIN(Strings.ORE_TIN_NAME, 0, 64, 3, 12),            //TODO: CONFIGS + REAL VALUES!
    SILVER(Strings.ORE_SILVER_NAME, 0, 64, 2, 10),      //TODO: RETRO WORLDGEN!
    LEAD(Strings.ORE_LEAD_NAME, 0, 64, 4, 8),
    ALUMINIUM(Strings.ORE_ALUMINIUM_NAME, 16, 48, 1, 10),
    URANIUM(Strings.ORE_URANIUM_NAME, 16, 48, 2, 1),
    TUNGSTEN(Strings.ORE_TUNGSTEN_NAME, 0, 24, 1, 5),
    TITANIUM(Strings.ORE_TITANIUM_NAME, 16, 48, 1, 5);

    public static final EnumOrePrimary[] VALID = values();
    public final String name;
    public final int minY, maxY, chance, clusterSize;
    public final int meta = this.ordinal();
    public Icon icon;

    private EnumOrePrimary(String name, int minY, int maxY, int chance, int clusterSize) {
        this.name = name;
        this.minY = minY;
        this.maxY = maxY;
        this.chance = chance;
        this.clusterSize = clusterSize;
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

