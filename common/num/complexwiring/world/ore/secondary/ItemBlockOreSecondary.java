package num.complexwiring.world.ore.secondary;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class ItemBlockOreSecondary extends ItemBlock {
    public ItemBlockOreSecondary(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

}