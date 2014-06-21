package num.complexwiring.api.prefab.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;

/**
 * A basic TileEntity with tick counting and a setup method (first time the TE is updated)
 */
public abstract class TileEntityBase extends TileEntity {
    protected long ticks = 0;
    protected Vector3 pos;

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
        ticks++;
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

    public Vector3 pos() {
        if (pos == null) {
            pos = new Vector3(this.xCoord, this.yCoord, this.zCoord);
        }
        return pos;
    }

    public World world() {
        return worldObj;
    }
}
