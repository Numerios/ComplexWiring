package num.complexwiring.core;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import num.complexwiring.lib.Reference;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ItemNetworkRegistry implements ITickHandler{
    
    private static ItemNetworkRegistry instance = new ItemNetworkRegistry();
    
    private Set<ItemNetwork> itemNetworks = new HashSet<ItemNetwork>();
    
    public ItemNetworkRegistry(){
        TickRegistry.registerTickHandler(this, Side.SERVER);
    }
    
    public static ItemNetworkRegistry getInstance(){
        return instance;
    }
    
    public void registerNetwork(ItemNetwork network){
        itemNetworks.add(network);
    }
    
    public void removeNetwork(ItemNetwork network){
        if(itemNetworks.contains(network)){
            itemNetworks.remove(network);
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        return;
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        return;    
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {
        return Reference.MOD_NAME + " " + "Item Networks";
    }
    
    @Override
    public String toString(){
        return itemNetworks.toString();
    }

}
