package num.complexwiring.world.industrial.stone;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

public enum EnumStoneIndustrial {
    LIGHT("stoneLight", Type.SMOOTH),
    DARK("stoneDark", Type.SMOOTH),
    LIGHT_ROUGH("stoneLightRough", Type.ROUGH),
    DARK_ROUGH("stoneDarkRough", Type.ROUGH),
    LIGHT_BRICK("stoneLightBrick", Type.BRICK),
    DARK_BRICK("stoneDarkBrick", Type.BRICK),
    LIGHT_SMALLBRICK("stoneLightSmallbrick", Type.SMALLBRICK),
    DARK_SMALLBRICK("stoneDarkSmallbrick", Type.SMALLBRICK),
    LIGHT_COBBLE("stoneLightCobble", Type.COBBLE),
    DARK_COBBLE("stoneDarkCobble", Type.COBBLE),
    LIGHT_PAVED("stoneLightPaved", Type.PAVED),
    DARK_PAVED("stoneDarkPaved", Type.PAVED),
    LIGHT_TILED("stoneLightTiled", Type.TILED),
    DARK_TILED("stoneDarkTiled", Type.TILED),
    LIGHT_CHISELEDBRICK("stoneLightChiseledbrick", Type.CHISELEDBRICK),
    DARK_CHISELEDBRICK("stoneDarkChiseledbrick", Type.CHISELEDBRICK);

    public static final EnumStoneIndustrial[] VALID = values();
    public final String name;
    public final Type type;
    public final int meta = this.ordinal();
    public IIcon icon;

    private EnumStoneIndustrial(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getUnlocalizedName() {
        // removes the "stone" from the name and makes it lowercase (ex. stoneLight -> light)
        return name.substring(5, 5 + 1).toLowerCase() + name.substring(5 + 1);
    }

    public void registerIcon(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "world/industrial/stone/" + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleWorld.stoneIndustrial, amount, meta);
    }

    public void registerOre() {
       /* if (this.type.equals(Type.SMOOTH)) {
            OreDictionary.registerOre(getUnlocalizedName(), this.getIS(1));           // limestone
            OreDictionary.registerOre("block" + getUnlocalizedName().substring(0, 1).toUpperCase() + getUnlocalizedName().substring(1), this.getIS(1));   // blockLimestone
        }   */
    }

    public enum Type {
        ROUGH, SMOOTH, BRICK, SMALLBRICK, COBBLE, PAVED, TILED, CHISELEDBRICK
    }
}
