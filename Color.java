/*
 * Color.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is to printed colored text in the terminal.
 */

public class Color {
    // ANSI codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    // set color
    public static void setColor(String color) {
        System.out.print(color);
    }

    // reset color
    public static void resetColor() {
        System.out.print(RESET);
    }

    // print colored text according to the input
    public static void print(String color, String text) {
        setColor(color);
        System.out.print(text);
        resetColor();
    }
    // with arguments
    public static void print(String color, String format, Object... args) {
        setColor(color);
        System.out.printf(format, args);
        resetColor();
    }
    
    // print line
    public static void println(String color, String text) {
        print(color, text);
        System.out.println();
    }
}
