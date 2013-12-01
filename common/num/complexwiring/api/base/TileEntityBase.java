package num.complexwiring.api.base;

import net.minecraft.tileentity.TileEntity;

/**
 * A basic TileEntity with tick counting and a setup method (first time the TE is updated)
 */
public abstract class TileEntityBase extends TileEntity {
    protected long ticks = 0;

    @Override
    public void updateEntity() {
        if (worldObj == null) {
            return;
        }
        if (ticks == 0) {
            setup();
        }
        if (ticks >= Long.MAX_VALUE) {
            ticks = 1;
        }
        update();
    }

    /**
     * This method is called only once when the TileEntity is initialized
     */
    public void setup() {
    }

    /**
     * This method is called every tick, please use this instead of updateEntity()
     */
    public void update() {
    }
}
