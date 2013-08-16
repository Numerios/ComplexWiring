package num.complexwiring.core;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.util.EnumColours;

public class Wrapper {
    // Wrapper = Item carrying ItemStacks :)
    private ItemStack contents;
    private EnumColours colour = EnumColours.UNKNOWN;
    private ForgeDirection direction = ForgeDirection.UNKNOWN;
    private float progress = 0F;

    public Wrapper(ItemStack is, EnumColours col, ForgeDirection dir){
        this.contents = is;
        this.colour = col;
        this.direction = dir;
    }

    public ItemStack getContents() {
        //TODO: NBT!
        return new ItemStack(contents.itemID, contents.stackSize, contents.getItemDamage());
    }

    public boolean hasContents() {
        if (contents != null) {
            return true;
        } else {
            return false;
        }
    }

    public EnumColours getColour() {
        return colour;
    }

    public void setColour(EnumColours col) {
        colour = col;
    }

    public ForgeDirection getDirection() {
        return direction;
    }

    public void setDirection(ForgeDirection dir) {
        direction = dir;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float f) {
        progress = f;
    }

    public void addProgress(float f) {
        progress += f;
    }

    public void resetProgress() {
        progress = 0;
    }

    public NBTTagCompound getNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("COLOUR", colour.toInt());
        nbt.setInteger("DIRECTION", direction.ordinal());
        nbt.setFloat("PROGRESS", progress);
        nbt.setBoolean("CONTENTS_COND", hasContents());
        if (hasContents()) {
            contents.writeToNBT(nbt);
        }
        return nbt;
    }

    public void loadNBT(NBTTagCompound nbt) {
        if (nbt.getBoolean("CONTENTS_COND")) {
            contents = new ItemStack(Block.dirt, 1);
            contents.readFromNBT(nbt);
        }
        progress = nbt.getFloat("PROGRESS");
        direction = ForgeDirection.getOrientation(nbt.getInteger("DIRECTION"));
        colour = EnumColours.fromInt(nbt.getInteger("COLOUR"));
    }
    
    public static Wrapper getFromNBT(NBTTagCompound nbt){
        try {
            Wrapper wrapper = new Wrapper(new ItemStack(Block.sponge, 1), EnumColours.UNKNOWN, ForgeDirection.UNKNOWN);
            wrapper.loadNBT(nbt.getCompoundTag("WRAPPER"));
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static NBTTagCompound getWrapperNBT(Wrapper wrapper){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setCompoundTag("WRAPPER", wrapper.getNBT());
        return nbt;
    }

    @Override
    public String toString() {
        return "Wrapper contains: " + contents.toString() + " | progress: " + progress;
    }
}
