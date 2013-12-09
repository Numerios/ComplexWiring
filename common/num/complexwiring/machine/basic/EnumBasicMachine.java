package num.complexwiring.machine.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.machine.ModuleMachine;

public enum EnumBasicMachine {
    ORELYZER(Strings.MACHINE_BASIC_ORELYZER_NAME, Strings.MACHINE_BASIC_ORELYZER_DESC, TileEntityBasicOrelyzer.class);
    public static final EnumBasicMachine[] VALID = values();
    public final String name, desc;
    public final Class<? extends TileEntityInventoryBase> tile;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumBasicMachine(String name, String desc, Class<? extends TileEntityInventoryBase> tile) {
        this.name = name;
        this.desc = "§o" + desc;
        this.tile = tile;
    }

    public String getUnlocalizedName() {
        // removes the "machine" and "Basic" from the name and makes it lowercase (ex. machineBasic -> machine)
        return name.toLowerCase().substring(7 + 5);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleMachine.machineBasic, amount, meta);
    }

    public void registerTile() {
        GameRegistry.registerTileEntity(this.tile, "basic." + this.getUnlocalizedName());
    }

    public String getFullUnlocalizedName() {
        return ModuleMachine.machineBasic.getUnlocalizedName() + "." + this.getUnlocalizedName();
    }
}
