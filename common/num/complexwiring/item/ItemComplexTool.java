package num.complexwiring.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.block.TileItemWire;
import num.complexwiring.core.Logger;
import num.complexwiring.core.Wrapper;
import num.complexwiring.lib.Reference;
import num.complexwiring.util.EnumColours;
import num.complexwiring.util.MCVector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        MCVector3 pos = new MCVector3(world, x, y, z);
        TileEntity tile = pos.toTile();
        if (tile instanceof TileItemWire) {
            TileItemWire wire = (TileItemWire) tile;
            wire.contents.add(new Wrapper(new ItemStack(Block.cobblestone, 1), EnumColours.UNKNOWN, ForgeDirection.DOWN));
            Logger.debug("Added cobble!");
            return true;
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumColours.AQUA.toChat() + "A DEBUG ITEM");
        list.add(EnumColours.DAQUA.toChat() + "Active mode: " + EnumColours.YELLOW.toChat()
                + " inserting random items!");
    }
}
