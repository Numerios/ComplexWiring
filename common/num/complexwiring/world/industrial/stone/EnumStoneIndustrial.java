package num.complexwiring.world.industrial.stone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.world.ModuleWorld;

public enum EnumStoneIndustrial {
    LIGHT(Strings.INDUSTRIAL_STONE, Type.SMOOTH),
    DARK(Strings.INDUSTRIAL_STONE_DARK, Type.SMOOTH),
    LIGHT_ROUGH(Strings.INDUSTRIAL_STONE_ROUGH, Type.ROUGH),
    DARK_ROUGH(Strings.INDUSTRIAL_STONE_DARK_ROUGH, Type.ROUGH),
    LIGHT_BRICK(Strings.INDUSTRIAL_STONE_BRICK, Type.BRICK),
    DARK_BRICK(Strings.INDUSTRIAL_STONE_DARK_BRICK, Type.BRICK),
    LIGHT_SMALLBRICK(Strings.INDUSTRIAL_STONE_SMALLBRICK, Type.SMALLBRICK),
    DARK_SMALLBRICK(Strings.INDUSTRIAL_STONE_DARK_SMALLBRICK, Type.SMALLBRICK),
    LIGHT_COBBLE(Strings.INDUSTRIAL_STONE_COBBLE, Type.COBBLE),
    DARK_COBBLE(Strings.INDUSTRIAL_STONE_DARK_COBBLE, Type.COBBLE),
    LIGHT_PAVED(Strings.INDUSTRIAL_STONE_PAVED, Type.PAVED),
    DARK_PAVED(Strings.INDUSTRIAL_STONE_DARK_PAVED, Type.PAVED),
    LIGHT_TILED(Strings.INDUSTRIAL_STONE_TILED, Type.TILED),
    DARK_TILED(Strings.INDUSTRIAL_STONE_DARK_TILED, Type.TILED),
    LIGHT_CHISELED(Strings.INDUSTRIAL_STONE_CHISELEDBRICK, Type.CHISELED),
    DARK_CHISELED(Strings.INDUSTRIAL_STONE_DARK_CHISELED, Type.CHISELED);

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

    @SideOnly(Side.CLIENT)
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
        ROUGH, SMOOTH, BRICK, SMALLBRICK, COBBLE, PAVED, TILED, CHISELED
    }
}
