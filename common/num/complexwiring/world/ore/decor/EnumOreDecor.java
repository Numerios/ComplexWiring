package num.complexwiring.world.ore.decor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumOreDecor {
    LIMESTONE(Strings.DECOR_LIMESTONE_NAME, Type.STONE),
    DOLOMITE(Strings.DECOR_DOLOMITE_NAME, Type.STONE),
    ARENITE(Strings.DECOR_ARENITE_NAME, Type.STONE);

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
        return new ItemStack(ModuleWorld.orePrimary, amount, meta);
    }

    public void registerOre() {
        if (this.type.equals(Type.STONE)) {
            OreDictionary.registerOre("stone", this.getIS(1));
        } else if (this.type.equals(Type.COBBLESTONE)) {
            OreDictionary.registerOre("cobblestone", this.getIS(1));
        }
    }

    private enum Type {
        STONE, COBBLESTONE, BRICK, SMALLBRICK, PAVER;

        private Type() {
        }
    }
}
