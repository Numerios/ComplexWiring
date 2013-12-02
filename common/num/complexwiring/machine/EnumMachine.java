package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

public enum EnumMachine {
    ORELYZER(Strings.MACHINE_ORELYZER_NAME, Strings.MACHINE_ORELYZER_DESC, TileEntityOrelyzer.class);
    public static final EnumMachine[] VALID = values();
    public final String name, desc;
    public final Class<? extends TileEntityInventoryBase> tile;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumMachine(String name, String desc, Class<? extends TileEntityInventoryBase> tile) {
        this.name = name;
        this.desc = desc;
        this.tile = tile;
    }

    public String getUnlocalizedName() {
        // removes the "machine" from the name and makes it lowercase (ex. machineBasic -> machine)
        return name.toLowerCase().substring(7);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleMachine.machine, amount, meta);
    }

    public void registerTile() {
        GameRegistry.registerTileEntity(this.tile, this.getUnlocalizedName());
    }

    public String getFullUnlocalizedName() {
        return ModuleMachine.machine.getUnlocalizedName() + "." + this.getUnlocalizedName();
    }
}
