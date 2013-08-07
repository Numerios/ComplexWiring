package num.complexwiring.api;

import num.complexwiring.core.ItemNetwork;

public interface IItemWire extends IWire {
    public ItemNetwork getNetwork();
    public void setNetwork(ItemNetwork network);
    public void refreshNetwork();
}
