package num.complexwiring.api.vec;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * The ultimate class for coordinates in three-dimensional space. Uses doubles.
 */
public class Vector3 implements Cloneable {
    public double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector3(double d) {
        this(d, d, d);
    }

    public Vector3(NBTTagCompound nbt) {
        this(nbt.getDouble("X"), nbt.getDouble("Y"), nbt.getDouble("Z"));
    }

    public Vector3(ForgeDirection dir) {
        this(dir.offsetX, dir.offsetY, dir.offsetZ);
    }

    @Override
    public Vector3 clone() {
        return new Vector3(this.x, this.y, this.z);
    }

    public Vector3(Vec3 mcvec3) {
        this.x = mcvec3.xCoord;
        this.y = mcvec3.yCoord;
        this.z = mcvec3.zCoord;
    }

    public static Vector3 get(TileEntity tile) {
        return new Vector3(tile.xCoord, tile.yCoord, tile.zCoord);
    }

    public static Vector3 get(Entity entity) {
        return new Vector3(entity.posX, entity.posY, entity.posZ);
    }

    public static Vector3 getCenter(TileEntity tile) {
        return new Vector3(tile.xCoord + 0.5, tile.yCoord + 0.5, tile.zCoord + 0.5);
    }

    // TODO: getCenter(Entity) - needs to offset properly!

    /**
     * Converts Vector3 to Vector2 by dropping the Y coord.
     */
    public Vector2 toVector2(){
        return new Vector2(this.x, this.z);
    }
    
    public Vector3 add(double d) {
        this.x += d;
        this.y += d;
        this.z += d;
        return this;
    }

    public Vector3 add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3 add(Vector3 other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3 scale(double d) {
        return this.multiply(d);
    }

    public Vector3 multiply(double d) {
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }

    public Vector3 multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vector3 multiply(Vector3 other) {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        return this;
    }

    public Vector3 invert() {
        return this.multiply(-1);
    }

    public Vector3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public TileEntity toTile(World world) {
        return world.getBlockTileEntity(this.getX(), this.getY(), this.getZ());
    }

    public int getX() {
        return (int) Math.floor(this.x);
    }

    public int getY() {
        return (int) Math.floor(this.y);
    }

    public int getZ() {
        return (int) Math.floor(this.z);
    }

    public Vector3 floor() {
        return this.set(Math.floor(this.x), Math.floor(this.y), Math.floor(this.z));
    }

    public Vector3 ceil() {
        return this.set(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
    }

    public Vector3 round() {
        return this.set(Math.round(this.x), Math.round(this.y), Math.round(this.z));
    }

    public double mag() {
        return Math.sqrt(this.magSqrt());
    }

    public double magSqrt() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double distance(Vector3 other) {
        return this.add(other.clone().invert()).mag();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setDouble("X", this.x);
        nbt.setDouble("Y", this.y);
        nbt.setDouble("Z", this.z);
        return nbt;
    }

    public Vector3 step(ForgeDirection dir) {
        return this.add(new Vector3(dir));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3))
            return false;

        Vector3 other = (Vector3) obj;
        return ((this.x == other.x) && (this.y == other.y) && (this.z == other.z));
    }

    @Override
    public String toString() {
        return "[x: " + this.x + ", y: " + this.y + ", z: " + this.z + "]";
    }
}
