package num.complexwiring.world.ore.primary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOrePrimary {
    // overworld ores
    COPPER(Strings.ORE_COPPER_NAME, 1, 36, 96, 20, 10, true),
    TIN(Strings.ORE_TIN_NAME, 1, 40, 64, 12, 8, true),
    SILVER(Strings.ORE_SILVER_NAME, 2, 16, 48, 6, 8, true),
    LEAD(Strings.ORE_LEAD_NAME, 2, 16, 48, 6, 12, true),
    NICKEL(Strings.ORE_NICKEL_NAME, 2, 0, 32, 10, 2, true),
    ALUMINIUM(Strings.ORE_ALUMINIUM_NAME, 3, 0, 32, 8, 8, true),
    TUNGSTEN(Strings.ORE_TUNGSTEN_NAME, 3, 0, 24, 4, 2, true),
    // nether ores
    TITANIUM(Strings.ORE_TITANIUM_NAME, 3, 16, 96, 8, 2, false),
    ZINC(Strings.ORE_ZINC_NAME, 3, 0, 100, 8, 10, false),
    COBALT(Strings.ORE_COBALT_NAME, 3, 32, 64, 12, 4, false);

    public static final EnumOrePrimary[] VALID = values();
    public final String name;
    public final int harvestLevel, minY, maxY, clusterNum, clusterSize;
    public final boolean isOverworld;   // true means spawn in overworld, false means spawn in the nether
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumOrePrimary(String name, int harvestLevel, int minY, int maxY, int clusterNum, int clusterSize, boolean isOverworld) {
        this.name = name;
        this.harvestLevel = harvestLevel;
        this.minY = minY;
        this.maxY = maxY;
        this.clusterNum = clusterNum;
        this.clusterSize = clusterSize;
        this.isOverworld = isOverworld;
    }

    public String getUnlocalizedName() {
        // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
        return name.toLowerCase().substring(3);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/primary/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.orePrimary, amount, meta);
    }

    public void registerOre() {
        OreDictionary.registerOre(this.name, this.getIS(1));
        ModuleWorld.orePrimary.setHarvestLevel("pickaxe", harvestLevel, this.meta);
    }
}

