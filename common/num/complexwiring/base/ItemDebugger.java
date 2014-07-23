package num.complexwiring.base;

import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.lib.Reference;

public class ItemDebugger extends Item {

    public ItemDebugger() {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.debugger");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        if (chunk != null) {
            for (int nowX = x - 10; nowX <= x + 10; nowX++) {
                for (int nowZ = z - 10; nowZ <= z + 10; nowZ++) {
                    for (int nowY = y - 10; nowY <= y + 10; nowY++) {
                        Block block = world.getBlock(nowX, nowY, nowZ);
                        if (block instanceof BlockStone || block instanceof BlockDirt || block instanceof BlockGrass || block instanceof BlockGravel || block instanceof BlockLiquid) {
                            world.setBlock(nowX, nowY, nowZ, Blocks.air);
                        }
                    }
                }
            }
        }

        return false;
    }

    public void registerOres() {
        OreDictionary.registerOre("CW:debugger", new ItemStack(ModuleBase.debugger));
    }

}
