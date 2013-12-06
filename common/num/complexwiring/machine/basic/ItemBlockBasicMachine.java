package num.complexwiring.machine.basic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;

public class ItemBlockBasicMachine extends ItemBlock {
    public ItemBlockBasicMachine(int ID) {
        super(ID);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleMachine.machineBasic.getUnlocalizedName() + "." + EnumBasicMachine.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
        list.add(EnumBasicMachine.VALID[is.getItemDamage()].desc);
    }
}
