package num.complexwiring.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;

public class GuiHelper {                //TODO Use the following method everywhere

    public static void drawCenteredString(FontRenderer fontRenderer, String string, int width, int height, int colour) {
        fontRenderer.drawString(string, width / 2 - fontRenderer.getStringWidth(string) / 2, height, colour);
    }

    public static void drawLocaleCenteredString(FontRenderer fontRenderer, String string, int width, int height, int colour) {
        fontRenderer.drawString(StatCollector.translateToLocal(string), width / 2 - fontRenderer.getStringWidth(StatCollector.translateToLocal(string)) / 2, height, colour);
    }
}
