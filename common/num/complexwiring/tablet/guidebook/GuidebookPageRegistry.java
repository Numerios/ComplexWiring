package num.complexwiring.tablet.guidebook;

import num.complexwiring.api.tablet.GuidebookPage;

import java.util.HashMap;

public class GuidebookPageRegistry {
    public static HashMap<Integer, Page> pageMap = new HashMap<Integer, Page>();
    public static enum Page{
        PAGE_ONE(new GuidebookPage("page.one")),
        PAGE_TWO(new GuidebookPage("page.two")),
        PAGE_THREE(new GuidebookPage("page.three")),
        PAGE_FOUR(new GuidebookPage("page.four"));
        public GuidebookPage page;

        private Page(GuidebookPage page){
            this.page = page;
        }
    }

    public static void init(){
        int n = 0;
        for (Page page : Page.values()){
            pageMap.put(n, page);
            n++;
        }
    }
}
