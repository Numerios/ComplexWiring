package num.complexwiring.mechanical.storagebox;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.mechanical.ModuleMechanical;

public enum EnumStorageBox {
    BASIC(Strings.STORAGEBOX_BASIC_NAME, Strings.STORAGEBOX_BASIC_DESC, TileStorageBox.TileStorageBoxBasic.class, 32),
    ADVANCED(Strings.STORAGEBOX_ADVANCED_NAME, Strings.STORAGEBOX_ADVANCED_DESC, TileStorageBox.TileStorageBoxAdvanced.class, 128);

    public static final EnumStorageBox[] VALID = values();
    public final String name, desc;
    public final Class<? extends TileStorageBox> tile;
    public final int meta = this.ordinal();
    public IIcon front, side, top_bottom;
    public int capacity;

    EnumStorageBox(String name, String desc, Class<? extends TileStorageBox> tile, int capacity) {
        this.name = name;
        this.desc = "§o" + desc;
        this.tile = tile;
        this.capacity = capacity;
    }

    public String getUnlocalizedName() {
        // removes the "storageBox" from the name and makes it lowercase (ex. storageBoxBasic -> basic)
        return name.toLowerCase().substring(10);
    }

    public void registerIcon(IIconRegister ir) {
        front = ir.registerIcon(Reference.TEXTURE_PATH + name + "_front");
        side = ir.registerIcon(Reference.TEXTURE_PATH + name + "_side");
        top_bottom = ir.registerIcon(Reference.TEXTURE_PATH + name + "_top_bottom");
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleMechanical.storageBox, amount, meta);
    }

    public String getFullUnlocalizedName() {
        return ModuleMechanical.storageBox.getUnlocalizedName() + "." + this.getUnlocalizedName();
    }

    public void registerTile() {
        GameRegistry.registerTileEntity(tile, "storagebox." + this.getUnlocalizedName());
    }
}
