package num.complexwiring.api.prefab;

import net.minecraftforge.common.util.ForgeDirection;

public interface IFacing {
    public ForgeDirection getFacing();

    public void setFacing(ForgeDirection dir);
}
