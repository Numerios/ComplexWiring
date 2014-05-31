package num.complexwiring.base;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.col.ColourRGB;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

public enum EnumMaterial {
    IRON("iron", new ColourRGB(168, 168, 168), new ItemStack(Blocks.iron_ore), new ItemStack(Items.iron_ingot), EnumDust.IRON.getIS(1), EnumNugget.IRON.getIS(1)),
    GOLD("gold", new ColourRGB(222, 222, 0), new ItemStack(Blocks.gold_ore), new ItemStack(Items.gold_ingot), EnumDust.GOLD.getIS(1), new ItemStack(Items.gold_nugget)),
    COPPER("copper", new ColourRGB(239, 155, 71), EnumOrePrimary.COPPER.getIS(1), EnumIngot.COPPER.getIS(1), EnumDust.COPPER.getIS(1), EnumNugget.COPPER.getIS(1)),
    TIN("tin", new ColourRGB(242, 242, 242), EnumOrePrimary.TIN.getIS(1), EnumIngot.TIN.getIS(1), EnumDust.TIN.getIS(1), EnumNugget.TIN.getIS(1)),
    SILVER("silver", new ColourRGB(201, 225, 237), EnumOrePrimary.SILVER.getIS(1), EnumIngot.SILVER.getIS(1), EnumDust.SILVER.getIS(1), EnumNugget.SILVER.getIS(1)),
    LEAD("lead", new ColourRGB(62, 84, 124), EnumOrePrimary.LEAD.getIS(1), EnumIngot.LEAD.getIS(1), EnumDust.LEAD.getIS(1), EnumNugget.LEAD.getIS(1));

    public static final EnumMaterial[] VALID = values();
    public final String name;
    public final ColourRGB colour;
    private final ItemStack ore, ingot, dust, nugget;

    private EnumMaterial(String name, ColourRGB colour, ItemStack ore, ItemStack ingot, ItemStack dust, ItemStack nugget) {
        this.name = name;
        this.colour = colour;
        this.ore = ore;
        this.ingot = ingot;
        this.dust = dust;
        this.nugget = nugget;
    }

    public String getOreName() {
        return "ore" + this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }

    public String getIngotName() {
        return "ingot" + this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }

    public String getDustName() {
        return "dust" + this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }

    public String getNuggetName() {
        return "nugget" + this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }

    public static EnumMaterial getMaterial(EnumDust dust) {
        return EnumMaterial.VALID[dust.meta];
    }

    public static EnumMaterial getMaterial(EnumIngot ingot) {
        return EnumMaterial.VALID[ingot.meta + 2];
    }

    public static EnumMaterial getMaterial(EnumNugget nugget) {
        return EnumMaterial.VALID[nugget.meta + 1];
    }

    public ItemStack getOre() {
        return getOre(1);
    }

    public ItemStack getOre(int amount) {
        ItemStack is = ore.copy();
        is.stackSize = amount;
        return is;
    }

    public ItemStack getIngot() {
        return getIngot(1);
    }

    public ItemStack getIngot(int amount) {
        ItemStack is = ingot.copy();
        is.stackSize = amount;
        return is;
    }

    public ItemStack getDust() {
        return getDust(1);
    }

    public ItemStack getDust(int amount) {
        ItemStack is = dust.copy();
        is.stackSize = amount;
        return is;
    }

    public ItemStack getNugget() {
        return getNugget(1);
    }

    public ItemStack getNugget(int amount) {
        ItemStack is = nugget.copy();
        is.stackSize = amount;
        return is;
    }
}