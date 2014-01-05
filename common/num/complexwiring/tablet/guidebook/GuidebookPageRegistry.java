package num.complexwiring.tablet.guidebook;

import num.complexwiring.api.research.IResearch;
import num.complexwiring.api.tablet.GuidebookPage;
import num.complexwiring.tablet.research.ResearchManager;

import java.util.HashMap;

public class GuidebookPageRegistry {
    public static HashMap<Integer, GuidebookPage> pageMap = new HashMap<Integer, GuidebookPage>();
    public static int MAX_PAGE;

    public static enum EnumPage {
        PAGE_ONE(new GuidebookPage("page.one")),
        PAGE_TWO(new GuidebookPage("page.two")),
        PAGE_THREE(new GuidebookPage("page.three")),
        PAGE_FOUR(new GuidebookPage("page.four"));
        public GuidebookPage page;

        private EnumPage(GuidebookPage page) {
            this.page = page;
        }
    }

    public static void init() {
        int n = 0;
        for (EnumPage enumPage : EnumPage.values()) {
            pageMap.put(n, enumPage.page);
            n++;
        }
        for (IResearch research : ResearchManager.researchList){
            pageMap.put(n, new GuidebookPage(research));
            n++;
        }
        if (n % 2 != 0) {  //insert a blank page to make the count even
            pageMap.put(n, new GuidebookPage(""));
            n++;
        }
        MAX_PAGE = n;
    }
}
