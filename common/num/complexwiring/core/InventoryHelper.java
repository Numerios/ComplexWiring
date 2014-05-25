package num.complexwiring.core;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryHelper {
    private static Random rand;

    public static void dropIS(World world, Vector3 vec3, List<ItemStack> itemStacks) {
        dropIS(world, vec3, itemStacks.toArray(new ItemStack[itemStacks.size()]));
    }

    public static void dropIS(World world, Vector3 vec3, ItemStack... itemStacks) {
        for (ItemStack is : itemStacks)
            if (is != null) {
                rand = new Random();

                float offsetX = rand.nextFloat() + 0.15F;
                float offsetY = rand.nextFloat() + 0.15F;
                float offsetZ = rand.nextFloat() + 0.15F;

                while (is.stackSize > 0) {
                    int amount = rand.nextInt(is.stackSize + 1);

                    is.stackSize -= amount;

                    EntityItem item = new EntityItem(world, vec3.getX() + offsetX, vec3.getY() + offsetY, vec3.getZ() + offsetZ, is.copy());

                    if (is.hasTagCompound()) {
                        item.getEntityItem().setTagCompound((NBTTagCompound) is.getTagCompound().copy());
                    }

                    float motion = 0.05F;
                    item.motionX = ((float) rand.nextGaussian() * motion);
                    item.motionY = ((float) rand.nextGaussian() * motion + 0.1F);
                    item.motionZ = ((float) rand.nextGaussian() * motion);
                    world.spawnEntityInWorld(item);
                }
            }
    }


    public static void dropInventory(World world, Vector3 vec3) {
        TileEntity tile = vec3.toTile(world);
        if (tile != null) {
            if (tile instanceof IInventory) {
                IInventory inv = (IInventory) tile;
                ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
                for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
                    ItemStack slotIS = inv.getStackInSlot(slot);
                    if (slotIS != null) {
                        itemStacks.add(slotIS);
                    }
                }
                if (!itemStacks.isEmpty()) {
                    dropIS(world, vec3, itemStacks);             //possibly too much code!
                }
            }
        }
    }

    public static int canMerge(ItemStack is1, ItemStack is2) {
        if (is1 == null || is2 == null) {
            return 0;
        }
        if (!is1.isItemEqual(is2)) {
            return 0;
        }
        return Math.min(is1.getMaxStackSize() - is1.stackSize, is2.stackSize);
    }
}
