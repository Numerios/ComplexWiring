package num.complexwiring.world.decor.dark;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumDarkDecor {
    DARK_LIMESTONE_ROUGH(Strings.DECOR_DARK_LIMESTONE_ROUGH_NAME, Type.ROUGH),
    DARK_DOLOMITE_ROUGH(Strings.DECOR_DARK_DOLOMITE_ROUGH_NAME, Type.ROUGH),
    DARK_ARENITE_ROUGH(Strings.DECOR_DARK_ARENITE_ROUGH_NAME, Type.ROUGH),

    DARK_LIMESTONE(Strings.DECOR_DARK_LIMESTONE_SMOOTH_NAME, Type.SMOOTH),
    DARK_DOLOMITE(Strings.DECOR_DARK_DOLOMITE_SMOOTH_NAME, Type.SMOOTH),
    DARK_ARENITE(Strings.DECOR_DARK_ARENITE_SMOOTH_NAME, Type.SMOOTH),

    DARK_LIMESTONE_BRICK(Strings.DECOR_DARK_LIMESTONE_BRICK_NAME, Type.BRICK),
    DARK_DOLOMITE_BRICK(Strings.DECOR_DARK_DOLOMITE_BRICK_NAME, Type.BRICK),
    DARK_ARENITE_BRICK(Strings.DECOR_DARK_ARENITE_BRICK_NAME, Type.BRICK),

    DARK_LIMESTONE_SMALLBRICK(Strings.DECOR_DARK_LIMESTONE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DARK_DOLOMITE_SMALLBRICK(Strings.DECOR_DARK_DOLOMITE_SMALLBRICK_NAME, Type.SMALLBRICK),
    DARK_ARENITE_SMALLBRICK(Strings.DECOR_DARK_ARENITE_SMALLBRICK_NAME, Type.SMALLBRICK),

    DARK_LIMESTONE_TILED(Strings.DECOR_DARK_LIMESTONE_TILED_NAME, Type.TILED),
    DARK_DOLOMITE_TILED(Strings.DECOR_DARK_DOLOMITE_TILED_NAME, Type.TILED),
    DARK_ARENITE_TILED(Strings.DECOR_DARK_ARENITE_TILED_NAME, Type.TILED),

    LIMESTONE_MIX(Strings.DECOR_LIMESTONE_MIX_NAME, Type.MIX);

    public static final EnumDarkDecor[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumDarkDecor(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "decor" from the name and makes it lowercase (ex. decorDarkLimestone -> darkLimestone)
        return name.substring(5, 6).toLowerCase() + name.substring(5 + 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/decor/dark/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.decorDark, amount, meta);
    }

    public void registerOre() {
    }

    public enum Type {
        ROUGH, SMOOTH, BRICK, SMALLBRICK, TILED, MIX
    }
}
