package num.complexwiring.world.industrial.wood;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumWoodIndustrial {
    LIGHT(Strings.INDUSTRIAL_WOOD, Type.SMOOTH),
    DARK(Strings.INDUSTRIAL_WOOD_DARK, Type.SMOOTH),
    LIGHT_ROUGH(Strings.INDUSTRIAL_WOOD_ROUGH, Type.ROUGH),
    DARK_ROUGH(Strings.INDUSTRIAL_WOOD_DARK_ROUGH, Type.ROUGH),
    LIGHT_TILED(Strings.INDUSTRIAL_WOOD_TILED, Type.TILED),
    DARK_TILED(Strings.INDUSTRIAL_WOOD_DARK_TILED, Type.TILED),
    LIGHT_SMALLTILED(Strings.INDUSTRIAL_WOOD_SMALLTILED, Type.SMALLTILED),
    DARK_SMALLTILED(Strings.INDUSTRIAL_WOOD_DARK_SMALLTILED, Type.SMALLTILED),
    LIGHT_DOUBLE(Strings.INDUSTRIAL_WOOD_DOUBLE, Type.DOUBLE),
    DARK_DOUBLE(Strings.INDUSTRIAL_WOOD_DARK_DOUBLE, Type.DOUBLE),
    LIGHT_SINGLE(Strings.INDUSTRIAL_WOOD_SINGLE, Type.SINGLE),
    DARK_SINGLE(Strings.INDUSTRIAL_WOOD_DARK_SINGLE, Type.SINGLE),
    LIGHT_CHISELED(Strings.INDUSTRIAL_WOOD_CHISELED, Type.CHISELED),
    DARK_CHISELED(Strings.INDUSTRIAL_WOOD_DARK_CHISELED, Type.CHISELED),
    LIGHT_PAVED(Strings.INDUSTRIAL_WOOD_PAVED, Type.PAVED),
    DARK_PAVED(Strings.INDUSTRIAL_WOOD_DARK_PAVED, Type.PAVED);

    public static final EnumWoodIndustrial[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumWoodIndustrial(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "wood" from the name and makes it lowercase (ex. woodLight -> light)
        return name.substring(4, 4 + 1).toLowerCase() + name.substring(4 + 1);
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/industrial/wood/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.woodIndustrial, amount, meta);
    }

    public void registerOre() {
     /*   if (this.type.equals(Type.CLASSIC)) {
            OreDictionary.registerOre(getUnlocalizedName(), this.getIS(1));           // limewood
            OreDictionary.registerOre("block" + getUnlocalizedName().substring(0, 1).toUpperCase() + getUnlocalizedName().substring(1), this.getIS(1));   // blockLimewood
        }  */
    }

    public enum Type {
        SMOOTH, ROUGH, TILED, SMALLTILED, DOUBLE, SINGLE, CHISELED, PAVED
    }
}
