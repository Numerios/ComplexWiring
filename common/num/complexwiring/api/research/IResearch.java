package num.complexwiring.api.research;

import java.util.ArrayList;

/**
 * An interface that is implemented for each and every research
 */
public interface IResearch {
    public String getLocalizedName();

    public String getLocalizedDesc();

    public ArrayList<IResearchTask> getResearchTasks();
}
