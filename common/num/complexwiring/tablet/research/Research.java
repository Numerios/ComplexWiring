package num.complexwiring.tablet.research;

import net.minecraft.util.StatCollector;
import num.complexwiring.api.research.IResearch;
import num.complexwiring.api.research.IResearchTask;

import java.util.ArrayList;
import java.util.Arrays;

public class Research implements IResearch {
    public final String name, desc;
    public final Tier tier;
    private ArrayList<IResearchTask> tasks = new ArrayList<IResearchTask>();

    public Research(Tier tier, String name, String desc, IResearchTask... researchTasks) {
        this.tier = tier;
        this.name = name;
        this.desc = desc;
        tasks.addAll(Arrays.asList(researchTasks));
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("research.complexwiring.research." + name.toLowerCase());
    }

    public String getLocalizedDesc() {
        return StatCollector.translateToLocal("research.complexwiring.research." + desc.toLowerCase());
    }

    public ArrayList<IResearchTask> getResearchTasks() {
        return tasks;
    }

    public enum Tier {
        TIER_0, TIER_1;
    }
}
