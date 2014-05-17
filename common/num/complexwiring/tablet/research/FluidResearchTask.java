package num.complexwiring.tablet.research;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import num.complexwiring.api.research.IResearchTask;

public class FluidResearchTask implements IResearchTask {
    public final String name;
    private final FluidStack goal;

    public FluidResearchTask(String name, FluidStack goal) {
        this.goal = goal;
        this.name = name;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("research.complexwiring.task.fluid." + name.toLowerCase() + ".name");
    }

    public String getLocalizedDesc() {
        return StatCollector.translateToLocal("research.complexwiring.task.fluid." + name.toLowerCase() + ".desc");
    }
}
