package num.complexwiring.tablet.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import num.complexwiring.api.research.IResearchTask;

public class SolidResearchTask implements IResearchTask {   //TODO: DEPEND ON RESEARCH!
    public final String name;
    private final ItemStack goal;

    public SolidResearchTask(String name, ItemStack goal) {
        this.goal = goal;
        this.name = name;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("research.complexwiring.task.solid." + name.toLowerCase() + ".name");
    }

    public String getLocalizedDesc() {
        return StatCollector.translateToLocal("research.complexwiring.task.solid." + name.toLowerCase() + ".desc");
    }
}
