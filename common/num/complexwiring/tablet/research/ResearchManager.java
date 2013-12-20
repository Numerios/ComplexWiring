package num.complexwiring.tablet.research;

import num.complexwiring.api.research.IResearch;

import java.util.ArrayList;
import java.util.Arrays;

public class ResearchManager {
    public static ArrayList<IResearch> researchList;

    public static void init(){
        researchList = new ArrayList<IResearch>();
    }

    public static void add(IResearch research){
        researchList.add(research);
    }

    public static void addAll(IResearch... researches){
        researchList.addAll(Arrays.asList(researches));
    }
}
