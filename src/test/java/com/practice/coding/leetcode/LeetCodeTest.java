package com.practice.coding.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LeetCodeTest {

    @Test
    @DisplayName("Number of Atoms")
    void numberOfAtomsTest() {
        String input = "H20";
        Assertions.assertEquals(LeetCode.countOfAtoms(input), input);

        input = "Mg(OH)2";
        Assertions.assertEquals(LeetCode.countOfAtoms(input), "H2MgO2");

        input = "K4(ON(SO3)2)2";
        Assertions.assertEquals(LeetCode.countOfAtoms(input), "K4N2O14S4");

        input = "Be32";
        Assertions.assertEquals(LeetCode.countOfAtoms(input), input);
    }
}
