package num.complexwiring.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabCW extends CreativeTabs {

    private ItemStack icon = new ItemStack(Blocks.stone, 1);

    public CreativeTabCW(String name, ItemStack is) {
        super(name);
        if (is != null) {
            icon = is;
        }
    }

    @Override
    public ItemStack getIconItemStack() {
        return icon;
    }

    @Override
    public Item getTabIconItem() {
        return icon.getItem();
    }

    public void setIcon(ItemStack is) {
        icon = is;
    }
}