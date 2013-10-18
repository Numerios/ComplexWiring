package num.complexwiring.api;

/**
 * A basic format for storing of and manipulating with colours.
 * Uses the format RRGGBBAA (toString is 0xRRGGBBAA)
 */
public class ColourRGBA {
    public byte r, g, b, a;
    public int colour;

    public ColourRGBA(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
        colour = this.getInt();
    }

    public ColourRGBA(int colour) {
        this((colour >> 24) & 0xFF, (colour >> 16) & 0xFF, (colour >> 8) & 0xFF, colour & 0xFF);
    }

    public int getInt() {
        return (this.r & 0xFF) << 24 | (this.g & 0xFF) << 16 | (this.b & 0xFF) << 8 | (this.a & 0xFF);
    }

    public ColourRGBA add(ColourRGBA other) {
        this.r += other.r;
        this.g += other.g;
        this.b += other.b;
        this.a += other.a;
        return this;
    }

    public ColourRGBA invert() {
        r = (byte) (0xFF - (r & 0xFF));
        g = (byte) (0xFF - (g & 0xFF));
        b = (byte) (0xFF - (b & 0xFF));
        a = (byte) (0xFF - (a & 0xFF));
        return this;
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
    public String toString() {
        return "0x" + Integer.toHexString(this.getInt()).toUpperCase();
    }
}
