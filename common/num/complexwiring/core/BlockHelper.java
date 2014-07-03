package num.complexwiring.core;

import net.minecraft.block.*;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHelper {

    public static int rotateBlock(Block block, int metadata) {
        if (block instanceof BlockDropper || block instanceof BlockHopper || block instanceof BlockDispenser || block instanceof BlockPistonBase) {
            int[] rotationMatrix = {1, 2, 3, 4, 5, 0};
            metadata = ForgeDirection.getOrientation(rotationMatrix[metadata]).ordinal();
        }

        if (block instanceof BlockSlab) {
            metadata = (metadata + 8) % 16;
        }

        if (block instanceof BlockStairs) {
            metadata++;
            if (metadata > 7) metadata = 0;
        }

        if (block instanceof BlockLog) {
            metadata = (metadata + 4) % 16;
        }

        if (block instanceof BlockFurnace || block instanceof BlockChest || block instanceof BlockEnderChest) {
            switch (metadata) {
                case 2:
                    metadata = 5;
                    break;
                case 5:
                    metadata = 3;
                    break;
                case 3:
                    metadata = 4;
                    break;
                case 4:
                    metadata = 2;
                    break;
            }
        }

        if (block instanceof BlockRailBase) {
            metadata++;
            if (metadata > 9) metadata = 0;
        }

        if (block instanceof BlockPumpkin) {
            metadata++;
            if (metadata > 3) metadata = 0;
        }

        if (block instanceof BlockSign) {
            metadata = (metadata + 1) % 16;
        }

        if (block instanceof BlockLever) {
            metadata++;
            if (metadata <= 8) {
                if (metadata == 8) metadata = 0;
            } else {
                if (metadata == 16) metadata = 8;
            }
        }

        if (block instanceof BlockRedstoneDiode) {
            int upper = metadata & 0xC;
            int lower = metadata & 0x3;
            metadata = upper + (lower + 3) % 4;
        }

        return metadata;
    }
}
