package num.complexwiring.api.prefab.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.energy.EnergyInternalHandler;
import num.complexwiring.api.energy.IEnergyInterface;
import num.complexwiring.api.energy.IEnergyItem;

/**
 * A basic TileEntity with the implementation of IEnergyInterface
 */
public abstract class TileEntityCWPoweredBase extends TileEntityInventoryBase implements IEnergyInterface {
    protected EnergyInternalHandler energyHandler;

    public TileEntityCWPoweredBase(int invSize, String name) {
        super(invSize, name);
    }

    /**
     * Charges an item from internal energy handler
     */
    public void chargeItem(ItemStack is) {
        if (this.getEnergyHandler() != null && is.getItem() instanceof IEnergyItem) {
            long charge = ((IEnergyItem) is.getItem()).charge(is, this.getEnergyHandler().getEnergy(), false);
        }
    }

    /**
     * Drains an item into the internal energy handler
     */
    public void drainItem(ItemStack is) {
        if (this.getEnergyHandler() != null && is.getItem() instanceof IEnergyItem) {
            long drain = ((IEnergyItem) is.getItem()).drain(is, this.getEnergyHandler().getRemainingSpace(), false);
        }
    }

    @Override
    public boolean canConnect(ForgeDirection dir, Object target) {
        return (target instanceof IEnergyInterface) && !(dir == null);
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);
        if (this.getEnergyHandler() != null) {
            this.getEnergyHandler().load(nbt);
        }
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);
        if (this.getEnergyHandler() != null) {
            this.getEnergyHandler().save(nbt);
        }
    }


    @Override
    public long chargeEnergy(ForgeDirection dir, long energy, boolean simulate) {
        if (this.getEnergyHandler() != null) {
            return this.getEnergyHandler().chargeEnergy(energy, simulate);
        }

        return 0;
    }

    @Override
    public long drainEnergy(ForgeDirection dir, long energy, boolean simulate) {
        if (this.getEnergyHandler() != null) {
            return this.getEnergyHandler().drainEnergy(energy, simulate);
        }

        return 0;
    }

    @Override
    public long getEnergy(ForgeDirection dir) {
        if (this.getEnergyHandler() != null) {
            return this.getEnergyHandler().getEnergy();
        }
        return 0;
    }

    @Override
    public long getCapacity(ForgeDirection dir) {
        if (this.getEnergyHandler() != null) {
            return this.getEnergyHandler().getCapacity();
        }
        return 0;
    }

    public EnergyInternalHandler getEnergyHandler() {
        return energyHandler;
    }
}
