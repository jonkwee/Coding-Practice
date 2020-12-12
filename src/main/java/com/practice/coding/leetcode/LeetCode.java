package com.practice.coding.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode {

    public static String countOfAtoms(String formula) {
        Object[] result = findElementCounts(0, formula);
        TreeMap<String, Integer> elementCounts = (TreeMap<String, Integer>) result[0];

        StringBuilder sortedResult = new StringBuilder();
        for (String s : elementCounts.navigableKeySet()) {
            sortedResult.append(s);
            if (elementCounts.get(s) > 1) sortedResult.append(elementCounts.get(s));
        }

        return sortedResult.toString();
    }
    // Create a recursive function to be called when the start of a parenthesis is found
    // Looks through the formula from a starting index and count elements until closing parenthesis is found
    // If another opening parenthesis is found, do a recursive call but once returned,
    // move index up by one after loop finishes current iteration to prevent multiple calls ending at once.
    // Function should return a Map of key as element and value as counts and ending index
    public static Object[] findElementCounts(int startingIndex, String formula) {
        // Initialize local tree map to keep track of elements in current scope
        TreeMap<String, Integer> elementCounts = new TreeMap<>();
        // loop starts from starting index so if called recursively, need to increment index when '(' is found
        for (int i = startingIndex; i < formula.length(); i++) {
            char currentChar = formula.charAt(i);
            // if currentChar is '(', do recursive call
            if (currentChar == '(') {
                Object[] innerScopeProduct = findElementCounts(i + 1, formula);
                TreeMap<String, Integer> innerScopeCounts = (TreeMap<String, Integer>) innerScopeProduct[0];
                Integer endingIndex = (Integer) innerScopeProduct[1];

                // lookahead from endingIndex to check for any number
                int multiplier = 1;
                if (endingIndex  < formula.length() && Character.isDigit(formula.charAt(endingIndex))) {
                    Object[] numericResult = extractNumericSubstring(endingIndex, formula);
                    multiplier = Integer.parseInt((String) numericResult[0]);
                    endingIndex = (Integer) numericResult[1];
                }
                    // Add inner scope elements to current elements, multiplied by multiplier if any
                for (Map.Entry<String, Integer> entry : innerScopeCounts.entrySet()) {
                    elementCounts.merge(entry.getKey(), entry.getValue() * multiplier, Integer::sum);
                }

                i = endingIndex - 1;
            } else if (currentChar == ')') {
                // return current map and ending index (one index after ')')
                return new Object[] {elementCounts, i + 1};
            } else {
                // if currentChar is not '('
                Object[] elementResult = extractElementSubstring(i, formula);
                String element = (String) elementResult[0];
                Integer endingIndex = (Integer) elementResult[1];
                Integer elementCount = 1;
                if (endingIndex < formula.length() && Character.isDigit(formula.charAt(endingIndex))) {
                    Object numericResult[] = extractNumericSubstring(endingIndex, formula);
                    elementCount = Integer.parseInt((String) numericResult[0]);
                    endingIndex = (Integer) numericResult[1];
                }

                // Add element an count to map
                elementCounts.merge(element,
                        elementCount,
                        Integer::sum);

                i = endingIndex - 1;
            }
        }
        return new Object[] {elementCounts, formula.length()};
    }

    // Create function to extract substring of continuous numbers starting from starting index
    // Returns the substring and the ending index
    public static Object[] extractNumericSubstring(int startingIndex, String formula) {
        // starting index is the index of the first number
        Integer endingIndex = startingIndex + 1;
        while (endingIndex < formula.length() && Character.isDigit(formula.charAt(endingIndex))) {
        endingIndex++;
    }
    return new Object[] {formula.substring(startingIndex, endingIndex), endingIndex};
}

    // Create function to extract substring of element starting from starting index
    // Returns the substring and the ending index
    public static Object[] extractElementSubstring(int startingIndex, String formula) {
        Integer endingIndex = startingIndex + 1;
        while (endingIndex < formula.length() && Character.isLetter(formula.charAt(endingIndex))
                && Character.isLowerCase(formula.charAt(endingIndex))) {
            endingIndex++;
        }
        return new Object[] {formula.substring(startingIndex, endingIndex), endingIndex};
    }
}
