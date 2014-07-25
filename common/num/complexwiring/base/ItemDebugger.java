package num.complexwiring.base;

import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import num.complexwiring.core.Reference;
import num.complexwiring.core.Strings;

import java.util.List;

public class ItemDebugger extends Item {
    public ItemDebugger() {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.debugger");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int playerX, int playerY, int playerZ, int side, float hitX, float hitY, float hitZ) {
        Chunk chunk = world.getChunkFromBlockCoords(playerX, playerZ);
        if (chunk != null) {
            for (int ax = 0; ax < 16; ax++) {
                for (int az = 0; az < 16; az++) {
                    for (int ay = 0; ay < 128; ay++) {
                        int x = playerX - (playerX % 16) + ax;
                        int y = ay; // there is no need to do anything with playerY, but this variable is for clarity
                        int z = playerZ - (playerZ % 16) + az;

                        Block block = world.getBlock(x, y, z);
                        if (block instanceof BlockStone || block instanceof BlockDirt || block instanceof BlockGrass || block instanceof BlockGravel || block instanceof BlockLiquid || block instanceof BlockSand || block instanceof BlockSandStone) {
                            world.setBlock(x, y, z, Blocks.air);
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
        list.add("§o" + Strings.DEBUGGER_TOOLTIP);
    }
}
