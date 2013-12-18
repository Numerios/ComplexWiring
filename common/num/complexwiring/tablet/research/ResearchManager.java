package num.complexwiring.tablet.research;

import java.util.ArrayList;
import java.util.Arrays;

public class ResearchManager {
    public static ArrayList<Research> researchList;

    public static void init(){
        researchList = new ArrayList<Research>();
    }

    public static void add(Research research){
        researchList.add(research);
    }

    public static void addAll(Research... researches){
        researchList.addAll(Arrays.asList(researches));
    }
}
