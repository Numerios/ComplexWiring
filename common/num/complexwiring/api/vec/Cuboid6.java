package num.complexwiring.api.vec;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * The ultimate class for regions in three-dimensional space. Uses two {@link num.complexwiring.api.vec.Vector3}.
 */
public class Cuboid6 implements Cloneable {
    public Vector3 min, max;

    public Cuboid6(Vector3 min, Vector3 max) {
        this.min = min;
        this.max = max;
    }

    public Cuboid6(double min, double max) {
        this(new Vector3(min), new Vector3(max));
    }

    public Cuboid6(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this(new Vector3(minX, minY, minZ), new Vector3(maxX, maxY, maxZ));
    }

    public AxisAlignedBB toAABB() {
        return AxisAlignedBB.getBoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
    }

    @Override
    public Cuboid6 clone() {
        return new Cuboid6(min.clone(), max.clone());
    }

    public Rectangle4 toRectangle() {
        return new Rectangle4(min.toVector2(), max.toVector2());
    }

    public Cuboid6 set(Vector3 min, Vector3 max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public Cuboid6 add(double d) {
        this.min.add(d);
        this.max.add(d);
        return this;
    }

    public Cuboid6 add(Vector3 vec3) {
        this.min.add(vec3);
        this.max.add(vec3);
        return this;
    }

    public Cuboid6 scale(double d) {
        return this.multiply(d);
    }

    public Cuboid6 multiply(double d) {
        this.min.multiply(d);
        this.max.multiply(d);
        return this;
    }

    public Cuboid6 multiply(Vector3 vec3) {
        this.min.multiply(vec3);
        this.max.multiply(vec3);
        return this;
    }

    public Cuboid6 invert() {
        return this.multiply(-1);
    }

    public Cuboid6 floor() {
        return this.set(min.floor(), max.floor());
    }

    public Cuboid6 ceil() {
        return this.set(min.ceil(), max.ceil());
    }

    public Cuboid6 round() {
        return this.set(min.round(), max.round());
    }

    public void setBlockBounds(Block block) {
        block.setBlockBounds((float) min.x, (float) min.y, (float) min.z, (float) max.x, (float) max.y, (float) max.z);
    }

    public boolean intersects(Vector3 vec3) {
        return vec3.x > this.min.x && vec3.x < this.max.x &&
                vec3.y > this.min.y && vec3.y < this.max.y &&
                vec3.z > this.min.z && vec3.z < this.max.z;
    }

    public boolean intersects(Cuboid6 other) {
        return max.x - 1E-6 > other.min.x && other.max.x - 1E-6 > min.x &&
                max.y - 1E-6 > other.min.y && other.max.y - 1E-6 > min.y &&
                max.z - 1E-6 > other.min.z && other.max.z - 1E-6 > min.z;
    }

    public Vector3 getCenter() {
        return min.add(max).multiply(0.5);
    }

    public Cuboid6 expand(double d) {
        min.add(-d);
        max.add(+d);
        return this;
    }

    public Cuboid6 expand(Vector3 vec3) {
        min.add(vec3.invert());
        max.add(vec3);
        return this;
    }

    public List getEntities(World world) {
        return world.getEntitiesWithinAABB(Entity.class, this.toAABB());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cuboid6))
            return false;

        Cuboid6 other = (Cuboid6) obj;
        return this.min == other.min && this.max == other.max;
    }

    @Override
    public String toString() {
        return "/Cuboid6/ - min: " + this.min + ", max:  " + this.max + "]";
    }
}
