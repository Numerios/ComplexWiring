package num.complexwiring.machine.powered;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.machine.ModuleMachine;

public enum EnumPoweredMachine {

    ORELYZER(Strings.MACHINE_POWERED_ORELYZER_NAME, Strings.MACHINE_POWERED_ORELYZER_DESC, TileEntityPoweredOrelyzer.class),
    FURNACE(Strings.MACHINE_POWERED_FURNACE_NAME, Strings.MACHINE_POWERED_FURNACE_DESC, TileEntityPoweredFurnace.class),
    CRUSHER(Strings.MACHINE_POWERED_CRUSHER_NAME, Strings.MACHINE_POWERED_CRUSHER_DESC, TileEntityPoweredCrusher.class);

    public static final EnumPoweredMachine[] VALID = values();
    public final String name, desc;
    public final Class<? extends TileEntityInventoryBase> tile;
    public final int meta = this.ordinal();
    public Icon icon;

    EnumPoweredMachine(String name, String desc, Class<? extends TileEntityInventoryBase> tile) {
        this.name = name;
        this.desc = "§o" + desc;
        this.tile = tile;
    }

    public String getUnlocalizedName() {
        // removes the "machine" and "Powered" from the name and makes it lowercase (ex. machinePoweredExample -> example)
        return name.toLowerCase().substring(7 + 7);
    }

    public void registerIcon(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
    }

    public ItemStack getIS(int amount) {
        return new ItemStack(ModuleMachine.machinePowered, amount, meta);
    }

    public void registerTile() {
        GameRegistry.registerTileEntity(this.tile, "powered." + this.getUnlocalizedName());
    }

    public String getFullUnlocalizedName() {
        return ModuleMachine.machinePowered.getUnlocalizedName() + "." + this.getUnlocalizedName();
    }
}
