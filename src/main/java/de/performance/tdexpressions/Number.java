package de.performance.tdexpressions;

public class Number {
    
    public static String removeDelimiter(String s) {
        s = s.replaceAll("\\.", "");
        return s;
    }

    public static int iNumber(String text){
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
    public static double dNumber(String text){
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}
