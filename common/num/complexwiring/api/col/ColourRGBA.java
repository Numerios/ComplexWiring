package num.complexwiring.api.col;

import java.awt.*;

/**
 * An extended format for storing colours and for their manipulation (RGB + alpha)
 * Uses the format RRGGBBAA (toString is 0xRRGGBBAA)
 */
public class ColourRGBA extends ColourRGB {
    public byte a;

    public ColourRGBA(int r, int g, int b, int a) {
        super(r, g, b);
        this.a = (byte) a;
        colour = this.getInt();
    }

    public ColourRGBA(int colour) {
        this((colour >> 24) & 0xFF, (colour >> 16) & 0xFF, (colour >> 8) & 0xFF, colour & 0xFF);
    }

    public ColourRGBA(Color color) {
        this(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
    }

    public static ColourRGBA average(ColourRGBA... colours) {
        int rTotal = 0;
        int gTotal = 0;
        int bTotal = 0;
        int count = 0;
        for (ColourRGBA colour : colours) {
            if (colour != null) {
                count++;
                rTotal += colour.r;
                gTotal += colour.g;
                bTotal += colour.b;
            }
        }
        return new ColourRGBA((byte) rTotal / count, (byte) gTotal / count, (byte) bTotal / count, 0xFF);
    }

    @Override
    public int getInt() {
        return (this.r & 0xFF) << 24 | (this.g & 0xFF) << 16 | (this.b & 0xFF) << 8 | (this.a & 0xFF);
    }

    @Override
    public Color toAWTColor() {
        return new Color(this.r, this.g, this.b, this.a);
    }

    @Override
    public ColourRGBA add(ColourRGBA other) {
        this.r += other.r;
        this.g += other.g;
        this.b += other.b;
        this.a += other.a;
        return this;
    }

    @Override
    public ColourRGBA invert() {
        r = (byte) (0xFF - (r & 0xFF));
        g = (byte) (0xFF - (g & 0xFF));
        b = (byte) (0xFF - (b & 0xFF));
        a = (byte) (0xFF - (a & 0xFF));
        return this;
    }

    @Override
    public String toString() {
        return "0x" + Integer.toHexString(this.getInt()).toUpperCase();
    }
}
