package num.complexwiring.creativetab;

import net.minecraft.creativetab.CreativeTabs;

public class ModCreativeTabs extends CreativeTabs {
    @SuppressWarnings("unused")
    private int id;
    private String name = "";

    public ModCreativeTabs(int creativeID, String tabName) {
        super(creativeID, tabName);
        this.name = tabName;
        this.id = creativeID;
    }

    @Override
    public String getTranslatedTabLabel() {
        return this.name;
    }

}
