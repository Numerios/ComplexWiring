package num.complexwiring.machine;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMachine extends ItemBlock {
    public ItemBlockMachine(int ID) {
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
        return ModuleMachine.machine.getUnlocalizedName() + "." + EnumMachine.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
