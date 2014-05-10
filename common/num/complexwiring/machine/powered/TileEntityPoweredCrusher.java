package num.complexwiring.machine.powered;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import num.complexwiring.api.base.TileEntityPoweredBase;
import num.complexwiring.api.recipe.CrusherRecipe;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityPoweredCrusher extends TileEntityPoweredBase implements ISidedInventory {

    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};

    private Random random = new Random();

    private CrusherRecipe recipe;
    private ArrayList<ItemStack> recipeOutput;
    private int processTime = 0;
    private int recipeNeededPower = 0;

    public TileEntityPoweredCrusher() {
        super(4, EnumPoweredMachine.CRUSHER.getFullUnlocalizedName());
        recipeOutput = new ArrayList<ItemStack>();
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void update() {
        super.update();

        if (!worldObj.isRemote) {
            storedEnergy = powerHandler.getEnergyStored();

            if(recipe == null) {
                if(RecipeManager.get(RecipeManager.Type.CRUSHER, getStackInSlot(0)) != null) {
                    recipe = (CrusherRecipe) RecipeManager.get(RecipeManager.Type.CRUSHER, getStackInSlot(0));
                    if(recipe.getNeededPower() <= ((int) storedEnergy)){
                        recipeNeededPower = recipe.getNeededPower();
                        recipeOutput = recipe.getCompleteOutput(random);
                        processTime = 0;

                        inventory[0].stackSize--;

                        if (getStackInSlot(0).stackSize <= 0) {
                            setInventorySlotContents(0, null);
                        }
                    } else {
                        recipe = null;
                    }
                }
            }
            if(recipe != null) {
                if(storedEnergy > 0){
                    powerHandler.useEnergy(1, USED_ENERGY, true);
                    storedEnergy = powerHandler.getEnergyStored();
                    processTime = processTime + 2;

                    if(processTime >= recipeNeededPower) {
                        endProcessing();
                    }
                } else {
                    recipe = null;
                    recipeOutput.clear();
                    recipeNeededPower = 0;
                    processTime = 0;
                }
            }
            if (ticks % 4 == 0) {
                worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                markDirty();
            }
        }
    }

    public void endProcessing(){
        if (recipeOutput != null && recipeOutput.size() > 0) {
            for (ItemStack output : recipeOutput) {
                if (output != null && output.stackSize != 0) {
                    for (int i : SLOTS_OUTPUT) {
                        if (getStackInSlot(i) == null) {
                            setInventorySlotContents(i, output);
                            break;
                        } else {
                            int adding = InventoryHelper.canMerge(getStackInSlot(i), output);
                            if (adding > 0) {
                                getStackInSlot(i).stackSize = getStackInSlot(i).stackSize + adding;
                                output.splitStack(adding);
                                if (output.stackSize == 0) break;
                            }

                        }
                    }
                }
            }
        }
        recipe = null;
        recipeOutput.clear();
        recipeNeededPower = 0;
        processTime = 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? SLOTS_BOTTOM : (slot == 1 ? SLOTS_TOP : SLOTS_OUTPUT);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack is, int side) {
        return !(slot == 2 || slot == 3) && isItemValidForSlot(slot, is);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack is, int side) {
        return (slot == 2 || slot == 3);
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);

        nbt.setShort("processTime", (short) processTime);
        nbt.setShort("recipe", (short) RecipeManager.toRecipeID(RecipeManager.Type.CRUSHER, recipe));
        nbt.setShort("recipePower", (short) recipeNeededPower);

        if (recipeOutput != null) {
            NBTTagList outputNBT = new NBTTagList();
            for (ItemStack is : recipeOutput) {
                if (is != null) {
                    NBTTagCompound itemNBT = new NBTTagCompound();
                    is.writeToNBT(itemNBT);
                    outputNBT.appendTag(itemNBT);
                }
            }
            nbt.setTag("recipeOutput", outputNBT);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        processTime = nbt.getShort("processTime");
        recipe = (CrusherRecipe) RecipeManager.fromRecipeID(RecipeManager.Type.CRUSHER, nbt.getShort("recipe"));
        recipeNeededPower = nbt.getShort("recipePower");

        recipeOutput.clear();
        NBTTagList outputNBT = (NBTTagList) nbt.getTag("currentOutput");
        for (int i = 0; i < outputNBT.tagCount(); i++) {
            NBTTagCompound itemNBT = outputNBT.getCompoundTagAt(i);
            recipeOutput.add(ItemStack.loadItemStackFromNBT(itemNBT));
        }
    }

    public int getProcessedTimeScaled(int scale) {
        if (processTime == 0 || recipeNeededPower == 0) {
            return 0;
        }
        return processTime * scale / recipeNeededPower;
    }

    public int getEnergyScaled(int scale) {
        if (storedEnergy == 0) {
            return 0;
        }
        return ((int) storedEnergy) * scale / MAX_STORED_ENERGY;
    }

    public int getStoredEnergy() {
        return (int) storedEnergy;
    }
}
