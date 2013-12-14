package num.complexwiring.world.ore.primary;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOrePrimary {
    COPPER(Strings.ORE_COPPER_NAME, 1, 0, 64, 16, 8),
    TIN(Strings.ORE_TIN_NAME, 1, 0, 64, 12, 6),            //TODO: CONFIGS + REAL VALUES!
    SILVER(Strings.ORE_SILVER_NAME, 2, 0, 64, 10, 8),      //TODO: RETRO WORLDGEN!
    LEAD(Strings.ORE_LEAD_NAME, 2, 0, 64, 8, 12),
    ALUMINIUM(Strings.ORE_ALUMINIUM_NAME, 2, 16, 48, 4, 4),
    URANIUM(Strings.ORE_URANIUM_NAME, 2, 16, 48, 2, 1),
    TUNGSTEN(Strings.ORE_TUNGSTEN_NAME, 3, 0, 24, 2, 3),
    TITANIUM(Strings.ORE_TITANIUM_NAME, 3, 16, 48, 2, 3);

    public static final EnumOrePrimary[] VALID = values();
    public final String name;
    public final int harvestLevel, minY, maxY, chance, clusterSize;
    public final int meta = this.ordinal();
    public Icon icon;

    private EnumOrePrimary(String name, int harvestLevel, int minY, int maxY, int chance, int clusterSize) {
        this.name = name;
        this.harvestLevel = harvestLevel;
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
        MinecraftForge.setBlockHarvestLevel(ModuleWorld.orePrimary, this.meta, "pickaxe", harvestLevel);
    }
}

