package num.complexwiring.api.base;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityPoweredBase extends TileEntityInventoryBase implements IPowerReceptor {
    protected static final int USED_ENERGY = 2;
    protected static final int MIN_ACTIVATION_ENERGY = 0;
    protected static final int MAX_STORED_ENERGY = 4000;
    protected static final PowerHandler.Type type = PowerHandler.Type.MACHINE;
    protected PowerHandler powerHandler;
    protected float storedEnergy;

    public TileEntityPoweredBase(int inventorySize, String name) {
        super(inventorySize, name);
        powerHandler = createPowerHandler(type);
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void update() {
        super.update();

        storedEnergy = powerHandler.getEnergyStored();
        powerHandler.update();
        powerHandler.setEnergy(storedEnergy);
    }

    @Override
    public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side) {
        return powerHandler.getPowerReceiver();
    }

    @Override
    public void doWork(PowerHandler workProvider) {
    }

    @Override
    public World getWorld() {
        return worldObj;
    }

    protected PowerHandler createPowerHandler(PowerHandler.Type type) {
        PowerHandler powerHandler = new PowerHandler(this, type);
        powerHandler.configure(USED_ENERGY, USED_ENERGY, MIN_ACTIVATION_ENERGY, MAX_STORED_ENERGY);
        powerHandler.configurePowerPerdition(0, 0);
        powerHandler.setPerdition(new PowerHandler.PerditionCalculator(0F));
        return powerHandler;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        powerHandler.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        powerHandler.readFromNBT(nbt);
        storedEnergy = powerHandler.getEnergyStored();
    }
}