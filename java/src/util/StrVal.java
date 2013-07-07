package util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * String Utility Class. Various helper functions lie here.
 *
 * @author Alex Hughes
 */
public class StrVal {

    /**
     * This function removes all quotes characters(')
     *
     * @param input
     * @return
     */
    public static String sntS(String input) {
        StringBuilder r = new StringBuilder(input.length());
        r.setLength(input.length());

        int current = 0;
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (cur != '\'' && cur != '\"') {
                r.setCharAt(current++, cur);
            }
        }
        return r.toString();
    }

    /**
     * Creates an alias for the feed, making use of the title. Rules: No special
     * characters are allowed. Spaces are converted to dashes Also, max string
     * length is 255 characters
     *
     * @param aTitle
     * @return
     */
    public static String createAlias(String aTitle) {
        //regular expression that only allows alphanumerics and whitespace
        String alias = aTitle.replaceAll("[^a-zA-Z0-9 ]+", "");
        alias = alias.replaceAll(" ", "-");
        
        if (alias.length() >= 255) {
            alias = alias.substring(0, 255);
        }

        return alias;
    }

    /**
     * This function returns the result of the subtraction of our desired limit
     * and the string length.
     *
     * @param input
     * @param aLimit
     * @return
     */
    public static int measureString(String input, int aLimit) {
        int result = input.length() - aLimit;
        return result;
    }

    /**
     * This function returns whether a string has a valid length.
     *
     * @param input
     * @param aLimit
     * @return
     */
    public static boolean validStringLength(String input, int aLimit) {
        boolean isValid;

        if (aLimit - input.length() >= 0) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean hasDuplicates(ArrayList<String> aStrings, String aString) {
        boolean hasDuplicates = false;

        for (int i = 0; i < aStrings.size(); i++) {
            if (aStrings.get(i).equalsIgnoreCase(aString)) {
                hasDuplicates = true;
                break;
            }
        }
        return hasDuplicates;
    }

    public static String SqlStringToString(String aSqlDate) {
        String date = "";
        String day = null, month = null, year = null;

        Scanner scanner = new Scanner(aSqlDate);
        scanner.useDelimiter("-");

        while (scanner.hasNext()) {
            year = scanner.next();
            month = scanner.next();
            day = scanner.next();
        }
        date = day + "/" + month + "/" + year;

        return date;
    }
}
