package num.complexwiring.api.col;

import java.awt.*;

/**
 * A basic standard format for storing colours and for their manipulation (RGB)
 * Uses the classic format RRGGBB (toString is 0xRRGGBB)
 */
public class ColourRGB {
    public byte r, g, b;
    public int colour;

    public ColourRGB(int r, int g, int b) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        colour = this.getInt();
    }

    public ColourRGB(int colour) {
        this((colour >> 16) & 0xFF, (colour >> 8) & 0xFF, colour & 0xFF);
    }

    public ColourRGB(Color color) {
        this(color.getRed(), color.getBlue(), color.getGreen());
    }

    public static ColourRGB average(ColourRGBA... colours) {
        int rTotal = 0;
        int gTotal = 0;
        int bTotal = 0;
        int count = 0;
        for (ColourRGB colour : colours) {
            if (colour != null) {
                count++;
                rTotal += colour.r;
                gTotal += colour.g;
                bTotal += colour.b;
            }
        }
        return new ColourRGB((byte) rTotal / count, (byte) gTotal / count, (byte) bTotal / count);
    }

    public int getInt() {
        return (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | (this.b & 0xFF);
    }

    public ColourRGBA toRGBA() {
        return toRGBA(0xFF);
    }

    public ColourRGBA toRGBA(int a) {
        return new ColourRGBA(this.r, this.g, this.b, a);
    }

    public Color toAWTColor() {
        return new Color(this.r, this.g, this.b);
    }

    public ColourRGB add(ColourRGBA other) {
        this.r += other.r;
        this.g += other.g;
        this.b += other.b;
        return this;
    }

    public ColourRGB invert() {
        r = (byte) (0xFF - (r & 0xFF));
        g = (byte) (0xFF - (g & 0xFF));
        b = (byte) (0xFF - (b & 0xFF));
        return this;
    }

    @Override
    public String toString() {
        return "0x" + Integer.toHexString(this.getInt()).toUpperCase();
    }
}
