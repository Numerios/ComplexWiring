package num.complexwiring.world.ore.primary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.core.Reference;
import num.complexwiring.core.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOrePrimary {
    // overworld ores
    COPPER(Strings.ORE_COPPER_NAME, 1, 42, 74, 9, 11, true), //99
    TIN(Strings.ORE_TIN_NAME, 1, 34, 66, 11, 6, true),  //66
    SILVER(Strings.ORE_SILVER_NAME, 2, 16, 48, 6, 8, true), //48
    LEAD(Strings.ORE_LEAD_NAME, 2, 20, 52, 8, 9, true), //72
    NICKEL(Strings.ORE_NICKEL_NAME, 2, 8, 40, 5, 5, true), //25
    ALUMINIUM(Strings.ORE_ALUMINIUM_NAME, 3, 22, 54, 8, 6, true), //48
    TUNGSTEN(Strings.ORE_TUNGSTEN_NAME, 3, 0, 24, 6, 3, true), //18
    // nether ores
    TITANIUM(Strings.ORE_TITANIUM_NAME, 3, 0, 128, 9, 2, false), //18
    ZINC(Strings.ORE_ZINC_NAME, 3, 0, 128, 3, 16, false), //48
    COBALT(Strings.ORE_COBALT_NAME, 3, 0, 128, 10, 4, false); //40

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

