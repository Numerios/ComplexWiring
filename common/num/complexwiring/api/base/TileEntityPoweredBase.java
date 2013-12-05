package num.complexwiring.api.base;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityPoweredBase extends TileEntityInventoryBase implements IPowerReceptor {
    protected PowerHandler powerHandler;
    protected static final int MIN_ENERGY = 0;
    protected static final int MAX_ENERGY = 2;
    protected static final int MIN_ACTIVATION_ENERGY = 0;
    protected static final int MAX_STORED_ENERGY = 2000;
    protected float storedEnergy;
    protected static final PowerHandler.Type type = PowerHandler.Type.MACHINE;

    public TileEntityPoweredBase(int inventorySize, String name) {
        super(inventorySize, name);
    }

    @Override
    public void setup() {
        powerHandler = createPowerHandler(type);
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

    protected PowerHandler createPowerHandler(PowerHandler.Type type){
        PowerHandler powerHandler = new PowerHandler(this, type);
        powerHandler.configure(MIN_ENERGY, MAX_ENERGY, MIN_ACTIVATION_ENERGY, MAX_STORED_ENERGY);
        powerHandler.configurePowerPerdition(0,0);
        powerHandler.setPerdition(new PowerHandler.PerditionCalculator(0F));
        return powerHandler;
    }
}
