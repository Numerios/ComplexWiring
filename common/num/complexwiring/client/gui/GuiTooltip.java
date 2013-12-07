package num.complexwiring.client.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiTooltip {

    protected Rectangle location;
    protected List<String> texts;

    public GuiTooltip(Rectangle location) {
        this.location = location;
        texts = new ArrayList<String>();
    }

    public void addText(String... text) {
        for (String str : text) {
            texts.add(str);
        }
    }

    public String getText(int i) {
        return texts.get(i);
    }

    public String setText(int i, String text) {
        return texts.set(i, text);
    }

    public List<String> getTexts() {
        return texts;
    }

    public boolean isHover(int mouseX, int mouseY) {
        return location.contains(mouseX, mouseY);
    }
}
