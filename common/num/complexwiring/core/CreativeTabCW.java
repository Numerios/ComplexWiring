package num.complexwiring.core;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabCW extends CreativeTabs {

    private ItemStack icon = new ItemStack(Block.stone, 1);

    public CreativeTabCW(String name, ItemStack is) {
        super(name);
        if (is != null) {
            this.icon = is;
        }
    }

    @Override
    public ItemStack getIconItemStack() {
        return this.icon;
    }
}
