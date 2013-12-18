package num.complexwiring.api.research;

/**
 * An interface for {@link IResearch} tasks (implemented in CW as solid ones, fluid ones, physical ones (the one where player
 * has to do something other than gather solid or liquid stuff) and psychical ones (the ones where the player must actually think)
 */
public interface IResearchTask {
    public String getLocalizedName();

    public String getLocalizedDesc();
}
