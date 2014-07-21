package num.complexwiring.mechanical.storagebox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.mechanical.ModuleMechanical;

import java.util.List;

public class ItemBlockStorageBox extends ItemBlock {
    public ItemBlockStorageBox(Block block) {
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
        return ModuleMechanical.storageBox.getUnlocalizedName() + "." + EnumStorageBox.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean bool) {
        list.add(EnumStorageBox.VALID[is.getItemDamage()].tooltip);
    }
}
