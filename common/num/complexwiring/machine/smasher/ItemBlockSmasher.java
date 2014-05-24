package num.complexwiring.machine.smasher;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockSmasher extends ItemBlock {
    public ItemBlockSmasher(Block block) {
        super(block);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }
}
