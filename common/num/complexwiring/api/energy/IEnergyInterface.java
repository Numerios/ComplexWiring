package num.complexwiring.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergyInterface extends IConnectable {
    /**
     * Adds energy to IEnergyInterface
     *
     * @param dir      Direction of the incoming energy
     * @param energy   Energy to be recieved
     * @param simulate If true, charge will be only simulated!
     * @return Amount of energy accepted by IEnergyInterface
     */
    public long chargeEnergy(ForgeDirection dir, long energy, boolean simulate);

    /**
     * Removes energy from IEnergyInterface
     *
     * @param dir      Direction of the outgoing energy
     * @param energy   Energy to be removed
     * @param simulate If true, drainage will be only simulated!
     * @return Amount of energy extracted from IEnergyInterface
     */
    public long drainEnergy(ForgeDirection dir, long energy, boolean simulate);

    /**
     * Gets the energy currently stored
     */
    public long getEnergy(ForgeDirection dir);

    /**
     * Gets the interface's capacity (max energy amount)
     */
    public long getCapacity(ForgeDirection dir);
}
