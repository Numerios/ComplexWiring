package num.complexwiring.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public class MCVector3 {
    public int x, y, z;
    public IBlockAccess world;

    public MCVector3(IBlockAccess world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MCVector3(TileEntity tile) {
        this.world = tile.worldObj;
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
    }

    public static MCVector3 get(TileEntity tile) {
        return new MCVector3(tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
    }

    public MCVector3 copy() {
        return new MCVector3(this.world, this.x, this.y, this.z);
    }

    public TileEntity toTile() {
        return this.world.getBlockTileEntity(this.x, this.y, this.z);
    }

    public MCVector3 getNeighbour(ForgeDirection side) {
        switch (side) {
            case EAST: {
                x++;
                break;
            }
            case WEST: {
                x--;
                break;
            }
            case UP: {
                y++;
                break;
            }
            case DOWN: {
                y--;
                break;
            }
            case SOUTH: {
                z++;
                break;
            }
            case NORTH: {
                z--;
                break;
            }
            default: {
                break;
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MCVector3))
            return false;

        MCVector3 vec3 = (MCVector3) obj;

        if (this.x != vec3.x)
            return false;
        if (this.y != vec3.y)
            return false;
        if (this.z != vec3.z)
            return false;
        if (this.world.equals(vec3.world) == false)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "[x: " + this.x + ", y: " + this.y + ", z: " + this.z + "]"; // [x:
                                                                            // 1,
                                                                            // y:
                                                                            // 2,
                                                                            // z:
                                                                            // 3]
    }

    @Override
    public int hashCode() {
        int nl = 31; // 2 ^ 5 - 1
        int hashCode = this.x;

        hashCode *= nl + this.y;
        hashCode *= nl + this.z;
        hashCode *= nl + this.world.hashCode();
        return hashCode;
    }
}
