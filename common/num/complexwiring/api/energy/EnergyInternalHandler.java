package num.complexwiring.api.energy;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Basic implementation of IEnergyInternalHandler
 */
public class EnergyInternalHandler implements IEnergyInternalHandler {
    protected long energy;
    protected long capacity;
    protected long maxCharge;
    protected long maxDrain;

    public EnergyInternalHandler(long capacity) {
        this(capacity, capacity, capacity);
    }

    public EnergyInternalHandler(long capacity, long maxTransfer) {
        this(capacity, maxTransfer, maxTransfer);
    }

    public EnergyInternalHandler(long capacity, long maxCharge, long maxDrain) {
        this.capacity = capacity;
        this.maxCharge = maxCharge;
        this.maxDrain = maxDrain;
    }

    public NBTTagCompound save(NBTTagCompound nbt) {
        nbt.setLong("energy", Math.max(this.getEnergy(), 0));
        return nbt;
    }

    public EnergyInternalHandler load(NBTTagCompound nbt) {
        this.energy = nbt.getLong("energy");
        if (energy > capacity) {
            energy = capacity;
        }
        return this;
    }

    public void setMaxTransfer(long maxTransfer) {
        setMaxCharge(maxTransfer);
        setMaxDrain(maxTransfer);
    }

    public long getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(long maxCharge) {
        this.maxCharge = maxCharge;
    }

    public long getMaxDrain() {
        return maxDrain;
    }

    public void setMaxDrain(long maxDrain) {
        this.maxDrain = maxDrain;
    }

    public long getRemainingSpace() {
        return this.getCapacity() - this.getEnergy();
    }

    public boolean isFull() {
        return this.getCapacity() == this.getEnergy();
    }

    public boolean isEmpty() {
        return this.getEnergy() == 0;
    }

    @Override
    public long chargeEnergy(long energy, boolean simulate) {
        long energyCharged = Math.min(this.getRemainingSpace(), Math.min(this.getMaxDrain(), energy));
        if (!simulate) {
            this.energy += energyCharged;
        }
        return energyCharged;
    }

    @Override
    public long drainEnergy(long energy, boolean simulate) {
        long energyDrained = Math.min(this.getEnergy(), Math.min(this.getMaxDrain(), energy));
        if (!simulate) {
            this.energy -= energyDrained;
        }
        return energyDrained;
    }

    @Override
    public long getEnergy() {
        return energy;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "EnergyInternalHandler" + "[" + this.getEnergy() + " energy out of " + this.getCapacity() + "; maxCharge" + this.getMaxCharge() + ", maxDrain" + this.getMaxDrain() + "]";
    }
}
