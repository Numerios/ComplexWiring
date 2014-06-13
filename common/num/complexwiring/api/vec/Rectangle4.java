package num.complexwiring.api.vec;
/**
 * The ultimate class for regions in two-dimensional space. Uses two {@link num.complexwiring.api.vec.Vector2}.
 */
public class Rectangle4 {
    public Vector2 min, max;

    public Rectangle4(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
    }

    public Rectangle4(double min, double max) {
        this(new Vector2(min), new Vector2(max));
    }

    public Rectangle4(double minX, double minY, double maxX, double maxY) {
        this(new Vector2(minX, minY), new Vector2(maxX, maxY));
    }

    @Override
    public Rectangle4 clone() {
        return new Rectangle4(min.clone(), max.clone());
    }

    public Rectangle4 set(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public Rectangle4 add(double d) {
        this.min.add(d);
        this.max.add(d);
        return this;
    }

    public Rectangle4 add(Vector2 vec2) {
        this.min.add(vec2);
        this.max.add(vec2);
        return this;
    }

    public Rectangle4 scale(double d) {
        return this.multiply(d);
    }

    public Rectangle4 multiply(double d) {
        this.min.multiply(d);
        this.max.multiply(d);
        return this;
    }

    public Rectangle4 multiply(Vector2 vec2) {
        this.min.multiply(vec2);
        this.max.multiply(vec2);
        return this;
    }

    public Rectangle4 invert() {
        return this.multiply(-1);
    }

    public Rectangle4 floor() {
        return this.set(min.floor(), max.floor());
    }

    public Rectangle4 ceil() {
        return this.set(min.ceil(), max.ceil());
    }

    public Rectangle4 round() {
        return this.set(min.round(), max.round());
    }

    public boolean intersects(Vector2 vec2) {
        return vec2.x > this.min.x && vec2.x < this.max.x &&
                vec2.y > this.min.y && vec2.y < this.max.y;
    }

    public boolean intersects(Rectangle4 other) {
        return max.x - 1E-6 > other.min.x && other.max.x - 1E-6 > min.x &&
                max.y - 1E-6 > other.min.y && other.max.y - 1E-6 > min.y;
    }

    public Vector2 getCenter() {
        return min.add(max).multiply(0.5);
    }

    public Rectangle4 expand(double d) {
        min.add(-d);
        max.add(+d);
        return this;
    }

    public Rectangle4 expand(Vector2 vec2) {
        min.add(vec2.invert());
        max.add(vec2);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle4))
            return false;

        Rectangle4 other = (Rectangle4) obj;
        return this.min == other.min && this.max == other.max;
    }

    @Override
    public String toString() {
        return "/Rectangle4/ - min: " + this.min + ", max:  " + this.max + "]";
    }
}
