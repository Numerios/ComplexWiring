package num.complexwiring.client.gui;

import num.complexwiring.core.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiTooltip {

    protected Rectangle location;
    protected List<GuiTooltipText> texts;

    public GuiTooltip(Rectangle location){
        this.location = location;
        texts = new ArrayList<GuiTooltipText>();
    }

    public void addText(String text){
        texts.add(new GuiTooltipText(text));
    }

    public void addText(GuiTooltipText text){
        texts.add(text);
    }

    public GuiTooltipText getText(int line){
        return texts.get(line);
    }

    public List<String> getTexts(boolean shift){
        Logger.debug("Shift key > " + shift);
        List<String> list = new ArrayList<String>();
        for(GuiTooltipText tooltip : texts){
            if(tooltip.getText(shift) != null){
                list.add(tooltip.getText(shift));
            }
        }
        return list;
    }

    public boolean isHover(int mouseX, int mouseY){
        return location.contains(mouseX, mouseY);
    }
}
