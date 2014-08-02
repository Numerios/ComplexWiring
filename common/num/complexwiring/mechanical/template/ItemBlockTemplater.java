package num.complexwiring.mechanical.template;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockTemplater extends ItemBlock {
    public ItemBlockTemplater(Block block) {
        super(block);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }
}
