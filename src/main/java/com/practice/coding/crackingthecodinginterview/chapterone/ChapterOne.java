package com.practice.coding.crackingthecodinginterview.chapterone;

import java.util.*;

public class ChapterOne {

    // 1.1 IsUnique
    // Implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structures?
    public static boolean isUnique(String input) {
        // Initialize a hashset to check for duplicates
        Set<Character> charCheck = new HashSet<>();

        // lowercase input such that casing does not matter
        input = input.toLowerCase();
        // Iterate through each character of string and store into hashset
        for (int i = 0; i < input.length(); i++) {
            Character currentChar = input.charAt(i);
            // if character exists in hashset then there are duplicate characters
            if (charCheck.contains(currentChar)) return false;
            else charCheck.add(currentChar);
        }
        return true;
    }

    // 1.2
    // Given two strings, write a method to decide if one is a permutation of the other.
    public static boolean checkPermutation(String inputA, String inputB) {
        // Initialize a hashmap to store characters as keys and counts as values
        Map<Character, Integer> characterCountMap = new HashMap<>();
        // Lowercase input so casing does not matter
        inputA = inputA.toLowerCase();
        inputB = inputB.toLowerCase();
        // Iterate through each character in inputA and populate the hashmap / increment counts
        for (int i = 0; i < inputA.length(); i++)
            characterCountMap.merge(inputA.charAt(i), 1, Integer::sum);
        // Iterate through each character in inputB and decrement count if key exist
        for (int i = 0; i < inputB.length(); i++) {
            Character currentChar = inputB.charAt(i);
            if (characterCountMap.containsKey(currentChar))
                characterCountMap.merge(currentChar, -1, Integer::sum);
            // if key does not exist, then both strings are not permutation of one another
            else return false;
        }
        // Check hashmap's values and if all values are 0, then strings are permutations
        // if not, then not permutations
        for (Integer count : characterCountMap.values()) {
            if (count != 0) return false;
        }
        return true;
    }

    // 1.3
    // Write a method to replace all spaces in a string with '%20'.
    // You may assume that the string has sufficient space at the end to hold the additional characters,
    // and that you are given the "true" length of the string
    public static String urlify(char[] inputString) {
        // Iterate through char array from front and back to find the point where the real string and extra padding
        // spaces are separated
        int frontPointer = 0;
        int backPointer = inputString.length - 1;
        while (frontPointer < backPointer) {
            // for every space found in front, two spaces are allocated from the back
            if (inputString[frontPointer++] == ' ') backPointer -= 2;
        }

        // starting from the found index from previous step, iterate to the front of the string
        // keep a pointer to check current position from the back of the string so characters
        // can be moved to the back
        int insertToIndex = inputString.length - 1;
        for (int i = backPointer; i >= 0; i--) {
            char currentChar = inputString[i];
            // if a space is found, replace with %20
            if (currentChar == ' ') {
                inputString[insertToIndex--] = '0';
                inputString[insertToIndex--] = '2';
                inputString[insertToIndex--] = '%';
            } else {
                // if a character is found, move it
                inputString[insertToIndex--] = inputString[i];
            }

            // if insertToIndex is less than or equal to i, then all swaps are completed
            if (insertToIndex <= i) break;
        }
        return new String(inputString);
    }

    // 1.4
    // Given a string, write a function to check if it is a permutation of a palindrome.
    // A palindrome is a word or phrase that is the same forwards and backwards.
    // A permutation is a rearrangement of letters.
    // The palindrome does not need to be limited to just dictionary words.
    public static boolean isPalindromePermutation(String input) {
        input = input.toLowerCase();
        // initialize a HashMap to count each letter's occurrence
        Map<Character, Integer> characterCounts = new HashMap<>();
        // iterate through each character and add it into hashmap
            // do not add spaces into hashmap
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar != ' ')
                characterCounts.merge(input.charAt(i), 1, Integer::sum);
        }

        int oddCounts = 0;
        // loop through the values of hashmap
        for (Integer count : characterCounts.values()) {
            // a palindrome should have either an even number of all characters
            // or an even number of characters other than one character which has an odd count
            if (count % 2 == 1) oddCounts++;
            if (oddCounts > 1) return false;
        }
        return true;
    }

    // 1.5
    // There are three types of edits that can be performed on strings:
    // insert a character, remove a character, or replace a character.
    // Given two strings, write a function to check if they are one edit (or zero edits) away.
    public static boolean oneAway(String inputA, String inputB) {
        inputA = inputA.toLowerCase();
        inputB = inputB.toLowerCase();
        // Initialize HashMap for inputA
        Map<Character, Integer> inputACounts = new HashMap<>();
        // Populate hashmap with character counts of inputA
        for (int i = 0; i < inputA.length(); i++) inputACounts.merge(inputA.charAt(i), 1, Integer::sum);
        // Iterate through each character in inputB
        // For each character, decrease the counts of character in hashmap
        for (int i = 0; i < inputB.length(); i++) inputACounts.merge(inputB.charAt(i), -1, Integer::sum);

        int positiveCount = 0;
        int negativeCount = 0;
        // If insert: only one character will have a negative count of 1
        // If remove: only one character will have a positive count of 1
        // If substitute: only one character will have a positive count of 1
        // and one character will have negative count of 1
        // Or all of the values are 0 (zero edits)
        for (Integer count : inputACounts.values()) {
            if (count > 0) positiveCount += count;
            else if (count < 0) negativeCount += count;

            if (positiveCount > 1 || negativeCount < -1) return false;
        }
        return true;
    }

    // 1.6
    // Implement a method to perform basic string compression using the counts of repeated characters.
    // For example, the string aabcccccaaa would become a2blc5a3.
    // If the "compressed" string would not become smaller than the original string, your method should return
    // the original string. You can assume the string has only uppercase and lowercase letters (a - z).
    public static String stringCompression(String input) {
        // Initialize a stringbuilder for building out compressed string
        StringBuilder encoded = new StringBuilder();
        // Initialize an int for tracking current position of stringbuilder
        int trackingIndex = 0;
        // Initialize an int for counting occurrence of current tracking character
        int trackingCount = 1;
        // Iterate through each character of input
        for (int i = 0; i < input.length(); i++) {
            // if character equals to current tracking char then increment int
            // if character not equal to tracking char, then add current tracking char + occurrence int
            // then swap tracking char to current char
            char currentChar = input.charAt(i);
            if (i == 0) {
                encoded.append(currentChar)
                        .append(trackingCount);
                trackingIndex = 1;
            } else {
                char previousChar = encoded.charAt(trackingIndex - 1);
                if (currentChar == previousChar) {
                    trackingCount++;
                    encoded.setCharAt(trackingIndex,  (char) (trackingCount + '0'));
                } else {
                    trackingCount = 1;
                    encoded.append(currentChar)
                            .append(trackingCount);
                    trackingIndex += 2;
                }
            }
        }
        return encoded.toString();
    }

    // 1.7
    // Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
    // write a method to rotate the image by 90 degrees. Can you do this in place?
    public static void rotateMatrix(int[][] matrix) {
        // Use the first array of the matrix as a distributing point for each layer
        // Only go up to N / 2 layer. Eg. 4x4 matrix, go up to the 2nd layer
        for (int layer = 0; layer < matrix.length / 2; layer++) {
            for (int col = layer; col < matrix[0].length - (1 + layer); col++) {
                // move element from top to right
                int temp = matrix[layer][col];
                matrix[layer][col] = matrix[col][matrix.length - (1 + layer)];
                matrix[col][matrix.length - (1 + layer)] = temp;
                // move element from top to bottom
                temp = matrix[layer][col];
                matrix[layer][col] = matrix[matrix.length - (1 + layer)][matrix[0].length - (1 + col)];
                matrix[matrix.length - (1 + layer)][matrix[0].length - (1 + col)] = temp;
                // move element from top to left
                temp = matrix[layer][col];
                matrix[layer][col] = matrix[matrix.length - (1 + col)][layer];
                matrix[matrix.length - (1 + col)][layer] = temp;
            }
        }
    }

    // 1.8
    // Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
    public static void zeroMatrix(int[][] matrix) {
        boolean firstCellZeroed = false;
        // iterate through first row/col to find if we need to zero out the first cell
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[0][i] == 0 || matrix[i][0] == 0) {
                firstCellZeroed = true;
                break;
            }
        }

        // iterate through matrix and if element is 0, set the row/col of cell to respective first row/col
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        // iterate through first row/col and if element is 0, set the entire row/col to zero
        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 1; row < matrix.length; row++) matrix[row][col] = 0;
            }
        }

        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 1; col < matrix[0].length; col++) matrix[row][col] = 0;
            }
        }

        if (matrix[0][0] == 0) {
            for (int i = 1; i < matrix.length; i++) {
                matrix[0][i] = 0;
                matrix[i][0] = 0;
            }
        }

        if (firstCellZeroed) matrix[0][0] = 0;

    }

    // 1.9
    // Assume you have a method isSubstring which checks if one word is a substring of another.
    // Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one call to isSubstring
    // (e.g.,"waterbottle" is a rotation of"erbottlewat")
    public static boolean isRotation(String s1, String s2) {
        // Convert input strings to lowercase to avoid case sensitive logic
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        // concatenate s2 with itself and check if s1 is a substring of s2
        // if s1 is, then s2 is a rotation
        String concat = s2 + s2;
        if (concat.contains(s1)) return true;

        return false;
    }
}
