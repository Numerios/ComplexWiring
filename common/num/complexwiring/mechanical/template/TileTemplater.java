package num.complexwiring.mechanical.template;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.tile.TileEntityBase;

public class TileTemplater extends TileEntityBase implements IFacing {
    private boolean isActive, prevActive;

    @Override
    public void update() {
        if (world().isRemote) {

            if (isActive != prevActive) {
                prevActive = isActive;
                worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
            return;
        }
    }

    @Override
    public ForgeDirection getFacing() {
        return null;
    }

    @Override
    public void setFacing(ForgeDirection dir) {

    }

    public void onRightClick(EntityPlayer player) {

    }
}

