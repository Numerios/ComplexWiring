package num.complexwiring.api.energy;

public interface IEnergyInternalHandler {
    /**
     * Adds energy to IEnergyInternalHandler
     *
     * @param energy   Energy to be recieved
     * @param simulate If true, charge will be only simulated!
     * @return Amount of energy accepted by IEnergyInternalHandler
     */
    public long chargeEnergy(long energy, boolean simulate);

    /**
     * Removes energy from IEnergyInternalHandler
     *
     * @param energy   Energy to be removed
     * @param simulate If true, drainage will be only simulated!
     * @return Amount of energy extracted from IEnergyInternalHandler
     */
    public long drainEnergy(long energy, boolean simulate);

    /**
     * Gets the energy currently stored
     */
    public long getEnergy();

    /**
     * Gets the handler's capacity (max energy amount)
     */
    public long getCapacity();
}
