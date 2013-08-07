package num.complexwiring.block;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IConnectable;
import num.complexwiring.api.IItemWire;
import num.complexwiring.core.ItemNetwork;
import num.complexwiring.util.MCVector3;

public class TileItemWire extends TileEntity implements IItemWire {
  public ItemNetwork itemNetwork;
  
    public TileItemWire() {
        super();
    }

    public boolean getConnection(ForgeDirection side, IConnectable origin) {
        TileEntity tile = MCVector3.get((TileEntity) origin).getNeighbour(side).toTile();
        if (tile instanceof IItemWire) {
            return true;
        } else if (tile instanceof IInventory) {
            return true;
        } else {
            return false;
        }
    }
    

    @Override
    public boolean canUpdate() {
        return false;
    }   

    @Override
    public ItemNetwork getNetwork() {
        if(itemNetwork == null){
            itemNetwork = new ItemNetwork(this);
        }
        
        return itemNetwork;
    }

    @Override
    public void setNetwork(ItemNetwork network) {
        itemNetwork = network;
    }
    
    @Override
    public void invalidate(){
        if(!worldObj.isRemote){
            getNetwork().split(this);
        }
        super.invalidate();
    }

    @Override
    public void refreshNetwork() {
        if(!worldObj.isRemote){
            for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS){
                TileEntity tile = MCVector3.get(this).getNeighbour(side).toTile();
                
                if(tile instanceof IItemWire){
                    getNetwork().merge(((IItemWire)tile).getNetwork());
                }
            }
            getNetwork().refresh();
        }
    }
}
