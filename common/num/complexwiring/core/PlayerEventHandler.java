package num.complexwiring.core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import num.complexwiring.lib.Reference;
import num.complexwiring.tablet.ModuleTablet;

import java.util.Random;

public class PlayerEventHandler {
    @SubscribeEvent
    public void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        onPlayerLogin(event.player);
    }

    public void onPlayerLogin(EntityPlayer player) {
        NBTTagCompound nbt = player.getEntityData();
        if (!nbt.getBoolean(Reference.MOD_ID + " " + "spawnPaper")) {
            nbt.setBoolean(Reference.MOD_ID + " " + "spawnPaper", true);
            ItemStack spawnPaper = new ItemStack(ModuleTablet.itemStartPaper);
            if (!player.inventory.addItemStackToInventory(spawnPaper)) {
                Random rand = new Random();
                EntityItem item = new EntityItem(player.worldObj, player.posX + 0.5 - rand.nextDouble(), player.posY + 0.5 - rand.nextDouble(), player.posZ + 0.5 - rand.nextDouble(), spawnPaper);
                player.worldObj.spawnEntityInWorld(item);
                item.onCollideWithPlayer(player);
            }
        }
    }
}
