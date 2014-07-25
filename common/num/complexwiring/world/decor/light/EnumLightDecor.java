package num.complexwiring.world.decor.light;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.core.Reference;
import num.complexwiring.core.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumLightDecor {
    LIMESTONE_ROUGH(Strings.DECOR_LIMESTONE_ROUGH_NAME, Type.ROUGH),
    DOLOMITE_ROUGH(Strings.DECOR_DOLOMITE_ROUGH_NAME, Type.ROUGH),
    ARENITE_ROUGH(Strings.DECOR_ARENITE_ROUGH_NAME, Type.ROUGH),

    LIMESTONE(Strings.DECOR_LIMESTONE_SMOOTH_NAME, Type.SMOOTH),
    DOLOMITE(Strings.DECOR_DOLOMITE_SMOOTH_NAME, Type.SMOOTH),
    ARENITE(Strings.DECOR_ARENITE_SMOOTH_NAME, Type.SMOOTH),

    LIMESTONE_BRICK(Strings.DECOR_LIMESTONE_BRICK_NAME, Type.BRICK),
    DOLOMITE_BRICK(Strings.DECOR_DOLOMITE_BRICK_NAME, Type.BRICK),
    ARENITE_BRICK(Strings.DECOR_ARENITE_BRICK_NAME, Type.BRICK),

    LIMESTONE_SMALLBRICK(Strings.DECOR_LIMESTONE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DOLOMITE_SMALLBRICK(Strings.DECOR_DOLOMITE_SMALLBRICK_NAME, Type.SMALLBRICK),
    ARENITE_SMALLBRICK(Strings.DECOR_ARENITE_SMALLBRICK_NAME, Type.SMALLBRICK),

    LIMESTONE_TILED(Strings.DECOR_LIMESTONE_TILED_NAME, Type.TILED),
    DOLOMITE_TILED(Strings.DECOR_DOLOMITE_TILED_NAME, Type.TILED),
    ARENITE_TILED(Strings.DECOR_ARENITE_TILED_NAME, Type.TILED),

    DOLOMITE_MIX(Strings.DECOR_DOLOMITE_MIX_NAME, Type.MIX);

    public static final EnumLightDecor[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumLightDecor(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "decor" from the name and makes it lowercase (ex. decorLimestone -> limestone)
        return name.substring(5, 6).toLowerCase() + name.substring(5 + 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/decor/light/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.decorLight, amount, meta);
    }

    public void registerOre() {
        if (this.type.equals(Type.SMOOTH)) {
            OreDictionary.registerOre(getUnlocalizedName(), this.getIS(1));           // limestone
            OreDictionary.registerOre("block" + getUnlocalizedName().substring(0, 1).toUpperCase() + getUnlocalizedName().substring(1), this.getIS(1));   // blockLimestone
        }
    }

    public enum Type {
        ROUGH, SMOOTH, BRICK, SMALLBRICK, TILED, MIX
    }
}
