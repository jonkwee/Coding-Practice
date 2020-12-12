package com.practice.coding.crackingthecodinginterview.chapterone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChapterOneTests {

    @Test
    @DisplayName("IsUnique")
    void isUniqueTest() {
        Assertions.assertTrue(ChapterOne.isUnique(""));
        Assertions.assertTrue(ChapterOne.isUnique("a"));
        Assertions.assertTrue(ChapterOne.isUnique("abcdef"));
        Assertions.assertTrue(ChapterOne.isUnique("123mac"));

        Assertions.assertFalse(ChapterOne.isUnique("apple"));
        Assertions.assertFalse(ChapterOne.isUnique("911isanumber"));
        Assertions.assertFalse(ChapterOne.isUnique("aTest"));
    }

    @Test
    @DisplayName("CheckPermutation")
    void checkPermutationTest() {
        Assertions.assertTrue(ChapterOne.checkPermutation("", ""));
        Assertions.assertTrue(ChapterOne.checkPermutation("abc", "bac"));
        Assertions.assertTrue(ChapterOne.checkPermutation("Abc", "bac"));

        Assertions.assertFalse(ChapterOne.checkPermutation("", "false"));
        Assertions.assertFalse(ChapterOne.checkPermutation("true", "false"));
        Assertions.assertFalse(ChapterOne.checkPermutation("apple", "grape"));
    }

    @Test
    @DisplayName("Urlify")
    void urlifyTest() {
        Assertions.assertEquals(ChapterOne.urlify("Mr John Smith    ".toCharArray()), "Mr%20John%20Smith");
        Assertions.assertEquals(ChapterOne.urlify("M1 chips are quite fast        ".toCharArray()),
                "M1%20chips%20are%20quite%20fast");
        Assertions.assertEquals(ChapterOne.urlify("      ".toCharArray()), "%20%20");
        Assertions.assertNotEquals(ChapterOne.urlify("this should not work      ".toCharArray()),
                "this%2should%20not%20work");
    }

    @Test
    @DisplayName("Palindrome Permutation")
    void palindromePermutationTest() {
        Assertions.assertTrue(ChapterOne.isPalindromePermutation("Raccaer"));
        Assertions.assertTrue(ChapterOne.isPalindromePermutation("Racecar"));
        Assertions.assertTrue(ChapterOne.isPalindromePermutation("Tact Coa"));
        Assertions.assertTrue(ChapterOne.isPalindromePermutation("aaa"));

        Assertions.assertFalse(ChapterOne.isPalindromePermutation("Notapalindrome"));
        Assertions.assertFalse(ChapterOne.isPalindromePermutation("Test"));
    }

    @Test
    @DisplayName("One Away")
    void oneAwayTest() {
        Assertions.assertTrue(ChapterOne.oneAway("car", "bar"));
        Assertions.assertTrue(ChapterOne.oneAway("apple", "aple"));
        Assertions.assertTrue(ChapterOne.oneAway("man", "mane"));
        Assertions.assertTrue(ChapterOne.oneAway("Test", "tes"));
        Assertions.assertTrue(ChapterOne.oneAway("oneaway", "oneaway"));
        Assertions.assertTrue(ChapterOne.oneAway("pale", "ple"));
        Assertions.assertTrue(ChapterOne.oneAway("pales", "pale"));
        Assertions.assertTrue(ChapterOne.oneAway("pale", "bale"));

        Assertions.assertFalse(ChapterOne.oneAway("car", "warm"));
        Assertions.assertFalse(ChapterOne.oneAway("java", "javascript"));
        Assertions.assertFalse(ChapterOne.oneAway("intellij", "inteij"));
        Assertions.assertFalse(ChapterOne.oneAway("pale", "bake"));
    }

    @Test
    @DisplayName("String Compression")
    void stringCompressionTest() {
        Assertions.assertEquals(ChapterOne.stringCompression("aaabbcccc"), "a3b2c4");
        Assertions.assertEquals(ChapterOne.stringCompression("abccbbaaa"), "a1b1c2b2a3");

        Assertions.assertNotEquals(ChapterOne.stringCompression("abbbccaaa"), "a4b3c2");
        Assertions.assertNotEquals(ChapterOne.stringCompression("abcabcabc"), "a3b3c3");
    }

    @Test
    @DisplayName("Rotate Matrix")
    void rotateMatrixTest() {
        int[][] matrix = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] rotatedOutcome = new int[][] {
                {13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7 ,3},
                {16, 12, 8, 4},
        };
        ChapterOne.rotateMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) Assertions.assertArrayEquals(matrix[i], rotatedOutcome[i]);

        matrix = new int[][] {
                {1,2,3,4,5,6},
                {7,8,9,10,11,12},
                {13,14,15,16,17,18},
                {19,20,21,22,23,24},
                {25,26,27,28,29,30},
                {31,32,33,34,35,36}
        };

        rotatedOutcome = new int[][] {
                {31,25,19,13,7,1},
                {32,26,20,14,8,2},
                {33,27,21,15,9,3},
                {34,28,22,16,10,4},
                {35,29,23,17,11,5},
                {36,30,24,18,12,6}
        };

        ChapterOne.rotateMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) Assertions.assertArrayEquals(matrix[i], rotatedOutcome[i]);
    }

    @Test
    @DisplayName("Zero Matrix")
    void zeroMatrixTest() {
        int[][] inputMatrix = new int[][] {
                {1,0,1,1},
                {1,1,0,1},
                {1,1,1,1},
                {1,1,1,0}
        };
        int[][] zeroMatrix = new int[][] {
                {0,0,0,0},
                {0,0,0,0},
                {1,0,0,0},
                {0,0,0,0}
        };
        ChapterOne.zeroMatrix(inputMatrix);
        for (int i = 0; i < inputMatrix.length; i++) Assertions.assertArrayEquals(inputMatrix[i], zeroMatrix[i]);

        inputMatrix = new int[][] {
                {0,1,1,0},
                {1,1,1,1},
                {1,1,1,1},
                {0,1,1,0}
        };

        zeroMatrix = new int[][] {
                {0,0,0,0},
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };

        ChapterOne.zeroMatrix(inputMatrix);
        for (int i = 0; i < inputMatrix.length; i++) Assertions.assertArrayEquals(inputMatrix[i], zeroMatrix[i]);

        inputMatrix = new int[][] {
                {0,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };

        zeroMatrix = new int[][] {
                {0,0,0,0},
                {0,1,1,1},
                {0,1,1,1},
                {0,1,1,1}
        };

        ChapterOne.zeroMatrix(inputMatrix);
        for (int i = 0; i < inputMatrix.length; i++) Assertions.assertArrayEquals(inputMatrix[i], zeroMatrix[i]);
    }

    @Test
    @DisplayName("IsRotation")
    void isRotationTest() {
        Assertions.assertTrue(ChapterOne.isRotation("rotation", "tionrota"));
        Assertions.assertTrue(ChapterOne.isRotation("testing", "testing"));
        Assertions.assertTrue(ChapterOne.isRotation("aba", "baa"));
        Assertions.assertTrue(ChapterOne.isRotation("VaniLla", "anillav"));

        Assertions.assertFalse(ChapterOne.isRotation("cat", "tac"));
    }
}
