package num.complexwiring.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommonProxy {

    public void init() {
    }

    public void updatePlayerInventory(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            playerMP.sendContainerToPlayer(playerMP.inventoryContainer);
        }
    }

}
