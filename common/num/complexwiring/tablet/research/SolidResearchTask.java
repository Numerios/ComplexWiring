package num.complexwiring.tablet.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import num.complexwiring.api.research.IResearchTask;

public class SolidResearchTask implements IResearchTask {
    public final String name, desc;
    private final ItemStack goal;

    public SolidResearchTask(String name, String desc, ItemStack goal) {
        this.goal = goal;
        this.name = name;
        this.desc = desc;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("research.complexwiring.task.solid." + name);
    }

    public String getLocalizedDesc() {
        return StatCollector.translateToLocal("research.complexwiring.task.solid." + desc);
    }
}
