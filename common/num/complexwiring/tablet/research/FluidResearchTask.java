package num.complexwiring.tablet.research;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import num.complexwiring.api.research.IResearchTask;

public class FluidResearchTask implements IResearchTask{
    public final String name, desc;
    private final FluidStack goal;

    public FluidResearchTask(String name, String desc, FluidStack goal) {
        this.goal = goal;
        this.name = name;
        this.desc = desc;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("research.complexwiring.task.fluid." + name.toLowerCase());
    }

    public String getLocalizedDesc() {
        return StatCollector.translateToLocal("research.complexwiring.task.fluid." + desc.toLowerCase());
    }
}
