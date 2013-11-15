package num.complexwiring.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.block.TileItemWire;
import num.complexwiring.core.Logger;
import num.complexwiring.core.Wrapper;
import num.complexwiring.lib.Reference;
import num.complexwiring.util.EnumColours;

import java.util.List;

public class ItemComplexTool extends Item {

    public ItemComplexTool(int ID) {
        super(ID);
        setUnlocalizedName("complexTool");
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.TEXTURE_PATH + "complexTool");
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side,
                             float targetX, float targetY, float targetZ) {
        Vector3 pos = new Vector3(x, y, z);
        TileEntity tile = pos.toTile(world);
        if (tile instanceof TileItemWire) {
            TileItemWire wire = (TileItemWire) tile;
            wire.acceptWrapper(new Wrapper(new ItemStack(Block.cobblestone, 1), EnumColours.UNKNOWN, ForgeDirection.UNKNOWN));
            Logger.debug("COMPLEX TOOL manually added one wrapper to the network with 1x Cobblestone in a proper way");
            return true;
        }
        return false;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumColours.AQUA.toChat() + "A DEBUG ITEM");
        list.add(EnumColours.DAQUA.toChat() + "Active mode:" + EnumColours.YELLOW.toChat()
                + " inserting random* items!");
        list.add(EnumColours.WHITE.toChat() + "* all inserted items are cobble");
    }
}
