package num.complexwiring.util;

public enum EnumColours { //possibly can shorten it (FFFFFF is the same as FFF :D)
    WHITE("FFFFFF"),
    ORANGE("FF9933"),
    MAGENTA("AA44DD"),
    YELLOW("EEEE33"),
    LIME("77CC22"),
    PINK("EE77AA"),
    GRAY("555555"),
    LGRAY("AAAAAA"),
    CYAN("447799"),
    PURPLE("7744AA"),
    BLUE("3344CC"),
    BROWN("664433"),
    GREEN("668833"),
    RED("CC3333"),
    BLACK("131313"),
    UNKNOWN("FFFFFF");
    
    EnumColours(String col){
        hexColour = col;
    }
    
    public final String hexColour;
}
