package num.complexwiring.client.gui;

public class GuiTooltipText {
    protected String text;
    protected boolean displayOnShift;
    protected boolean ignoreShift;

    public GuiTooltipText(String text){
        this.text = text;
        ignoreShift = true;
        displayOnShift = false;
    }

    public GuiTooltipText(String text, boolean displayOnShift){
        this.text = text;
        this.displayOnShift = displayOnShift;
        this.ignoreShift = false;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(boolean shift) {
        if(ignoreShift) return text;

        if (shift == displayOnShift) return text;
        return null;
    }
}
