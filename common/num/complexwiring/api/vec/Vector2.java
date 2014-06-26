package num.complexwiring.api.vec;

import net.minecraft.nbt.NBTTagCompound;

/**
 * The ultimate class for coordinates in two-dimensional space. Uses doubles.
 */
public class Vector2 implements Cloneable, Comparable<Vector2> {
    public double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2(double d) {
        this(d, d);
    }

    public Vector2(NBTTagCompound nbt) {
        this(nbt.getDouble("2X"), nbt.getDouble("2Y"));
    }

    @Override
    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

    public Vector2 add(double d) {
        this.x += d;
        this.y += d;
        return this;
    }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 multiply(double d) {
        this.x *= d;
        this.y *= d;
        return this;
    }

    public Vector2 multiply(Vector2 other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public int getX() {
        return (int) Math.floor(this.x);
    }

    public int getY() {
        return (int) Math.floor(this.y);
    }

    public Vector2 floor() {
        return this.set(Math.floor(this.x), Math.floor(this.y));
    }

    public Vector2 ceil() {
        return this.set(Math.ceil(this.x), Math.ceil(this.y));
    }

    public Vector2 round() {
        return this.set(Math.round(this.x), Math.round(this.y));
    }

    public double mag() {
        return Math.sqrt(this.magSquared());
    }

    public double magSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public double distance(Vector2 other) {
        return this.add(other.clone().invert()).mag();
    }

    public double distanceSquared(Vector2 other) {
        return this.add(other.clone().invert()).magSquared();
    }

    public Vector2 invert() {
        return this.multiply(-1);
    }

    public Vector2 set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setDouble("2X", this.x);
        nbt.setDouble("2Y", this.y);
        return nbt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2))
            return false;

        Vector2 other = (Vector2) obj;
        return ((this.x == other.x) && (this.y == other.y));
    }

    @Override
    public String toString() {
        return "[x: " + this.x + ", y: " + this.y + "]";
    }

    @Override
    public int compareTo(Vector2 other) {
        double magThis = this.magSquared();
        double magOther = other.magSquared();
        if (magThis > magOther) {
            return 1;
        } else if (magThis < magOther) {
            return -1;
        }
        return 0;
    }
}
