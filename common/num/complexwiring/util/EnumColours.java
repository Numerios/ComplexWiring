package num.complexwiring.util;

import java.awt.Color;

public enum EnumColours {
    BLACK(0, "131313"), DBLUE(1, "0000AA"), DGREEN(2, "00AA00"), DAQUA(3, "00AAAA"), DRED(4, "AA0000"), PURPLE(5,
            "AA00AA"), ORANGE(6, "FFAA00"), GREY(7, "AAAAAA"), DGREY(8, "555555"), BLUE(9, "5555FF"), LIME(10, "55FF55"), AQUA(
            11, "55FFFF"), RED(12, "FF5555"), PINK(13, "FF55FF"), YELLOW(14, "FFFF55"), WHITE(15, "FFFFFF"), UNKNOWN(
            -1, "800000");

    EnumColours(int i, String hex) {
        intColor = i;
        hexColor = hex;
    }

    public static EnumColours fromInt(int id) {
        if (id >= 0 && id < VALID_COLOURS.length) {
            return VALID_COLOURS[id];
        }
        return UNKNOWN;
    }

    public int toInt() {
        return intColor;
    }

    public String toChat() {
        return "§" + Integer.toHexString(intColor);
    }

    public String getHex() {
        return hexColor;
    }

    public Color getColor() {
        return new Color(Integer.parseInt(hexColor, 16));
    }

    public float[] getColor_F() {
        Color color = getColor();
        float[] rgb = { color.getRed(), color.getGreen(), color.getGreen() };

        return rgb;
    }

    private final int intColor;
    private final String hexColor;
    private static final EnumColours[] VALID_COLOURS = { BLACK, DBLUE, DGREEN, DAQUA, DRED, PURPLE, ORANGE, GREY, DGREY,
            BLUE, LIME, AQUA, RED, PINK, YELLOW, WHITE };
   // private static final EnumColours[] MC_COLOURS = { WHITE, ORANGE, MAGENTA, LBLUE, YELLOW, LIME, PINK, GREY, LGREY, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK, UNKNOWN};
}
