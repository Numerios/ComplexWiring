package num.complexwiring.world.decor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumDecor {
    LIMESTONE_ROUGH(Strings.DECOR_LIMESTONE_ROUGH_NAME, Type.ROUGH),
    DOLOMITE_ROUGH(Strings.DECOR_DOLOMITE_ROUGH_NAME, Type.ROUGH),
    ARENITE_ROUGH(Strings.DECOR_ARENITE_ROUGH_NAME, Type.ROUGH),
    LIMESTONE(Strings.DECOR_LIMESTONE_SMOOTH_NAME, Type.SMOOTH),
    DOLOMITE(Strings.DECOR_DOLOMITE_SMOOTH_NAME, Type.SMOOTH),
    ARENITE(Strings.DECOR_ARENITE_SMOOTH_NAME, Type.SMOOTH),
    DARKDOLOMITE(Strings.DECOR_DARKDOLOMITE_NAME, Type.SMOOTH),
    LIMESTONE_BRICK(Strings.DECOR_LIMESTONE_BRICK_NAME, Type.BRICK),
    DOLOMITE_BRICK(Strings.DECOR_DOLOMITE_BRICK_NAME, Type.BRICK),
    ARENITE_BRICK(Strings.DECOR_ARENITE_BRICK_NAME, Type.BRICK),
    DARKDOLOMITE_BRICK(Strings.DECOR_DARKDOLOMITE_BRICK_NAME, Type.BRICK),
    LIMESTONE_SMALLBRICK(Strings.DECOR_LIMESTONE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DOLOMITE_SMALLBRICK(Strings.DECOR_DOLOMITE_SMALLBRICK_NAME, Type.SMALLBRICK),
    ARENITE_SMALLBRICK(Strings.DECOR_ARENITE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DARKDOLOMITE_SMALLBRICK(Strings.DECOR_DARKDOLOMITE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DOLOMITE_DARKDOLOMITE_MIX(Strings.DECOR_DOLOMITE_DARKDOLOMITE_MIX_NAME, Type.MIX);

    public static final EnumDecor[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumDecor(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "decor" from the name and makes it lowercase (ex. decorLimestone -> limestone)
        return name.substring(5, 6).toLowerCase() + name.substring(5 + 1);
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/decor/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.decor, amount, meta);
    }

    public void registerOre() {
        if (this.type.equals(Type.SMOOTH)) {
            OreDictionary.registerOre(getUnlocalizedName(), this.getIS(1));           // limestone
            OreDictionary.registerOre("block" + getUnlocalizedName().substring(0, 1).toUpperCase() + getUnlocalizedName().substring(1), this.getIS(1));   // blockLimestone
        }
    }

    public enum Type {
        ROUGH, SMOOTH, BRICK, SMALLBRICK, MIX
    }
}
