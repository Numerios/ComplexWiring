package num.complexwiring.machine.powered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;

public class ItemBlockPoweredMachine extends ItemBlock {
    public ItemBlockPoweredMachine(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleMachine.machinePowered.getUnlocalizedName() + "." + EnumPoweredMachine.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
        list.add(EnumPoweredMachine.VALID[is.getItemDamage()].desc);
    }

}
