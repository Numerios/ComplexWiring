package num.complexwiring.world.ore.decor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreDecor {
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
    LIMESTONE_DOLOMITE_MIX(Strings.DECOR_LIMESTONE_DOLOMITE_MIX_NAME, Type.MIX),
    DOLOMITE_ARENITE_MIX(Strings.DECOR_DOLOMITE_ARENITE_MIX_NAME, Type.MIX),
    ARENITE_LIMESTONE_MIX(Strings.DECOR_ARENITE_LIMESTONE_MIX_NAME, Type.MIX),
    ;

    public static final EnumOreDecor[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public Icon icon;

    private EnumOreDecor(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "decor" from the name and makes it lowercase (ex. decorLimestone -> limestone)
        return name.toLowerCase().substring(5);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/ore/decor/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.oreDecor, amount, meta);
    }

    public void registerOre() {
        if (this.type.equals(Type.SMOOTH) || this.type.equals(Type.ROUGH)) {
            OreDictionary.registerOre("stone", this.getIS(1));
        }
        OreDictionary.registerOre(getUnlocalizedName(), this.getIS(1));
    }

    private enum Type {
        ROUGH, SMOOTH, BRICK, SMALLBRICK, MIX;

        private Type() {
        }
    }
}
