package num.complexwiring.world.industrial.wood;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

public enum EnumWoodIndustrial {
    LIGHT("woodLight", Type.SMOOTH),
    DARK("woodDark", Type.SMOOTH),
    LIGHT_ROUGH("woodLightRough", Type.ROUGH),
    DARK_ROUGH("woodDarkRough", Type.ROUGH),
    LIGHT_TILED("woodLightTiled", Type.TILED),
    DARK_TILED("woodDarkTiled", Type.TILED),
    LIGHT_SMALLTILED("woodLightSmalltiled", Type.SMALLTILED),
    DARK_SMALLTILED("woodDarkSmalltiled", Type.SMALLTILED),
    LIGHT_DOUBLE("woodLightDouble", Type.DOUBLE),
    DARK_DOUBLE("woodDarkDouble", Type.DOUBLE),
    LIGHT_SINGLE("woodLightSingle", Type.SINGLE),
    DARK_SINGLE("woodDarkSingle", Type.SINGLE),
    LIGHT_CHISELED("woodLightChiseledbrick", Type.CHISELED),
    DARK_CHISELED("woodDarkChiseledbrick", Type.CHISELED),
    LIGHT_PAVED("woodLightPaved", Type.PAVED),
    DARK_PAVED("woodDarkPaved", Type.PAVED);

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
        ROUGH, SMOOTH, TILED, SMALLTILED, DOUBLE, SINGLE, CHISELED, PAVED
    }
}
