package com.practice.coding.crackingthecodinginterview.chapterthree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;
import java.util.StringJoiner;

public class ChapterThreeTests {

    @Test
    @DisplayName("Three in One")
    void threeInOneTest() {
        ChapterThree.ThreeStackArray<Integer> stack = new ChapterThree.ThreeStackArray<>(5);
        // Test normal pushes
        stack.pushA(1);
        Assertions.assertEquals(stack.peekA(), 1);
        stack.pushB(5);
        Assertions.assertEquals(stack.peekB(), 5);
        stack.pushC(3);
        Assertions.assertEquals(stack.peekC(), 3);
        stack.pushC(4);
        Assertions.assertEquals(stack.peekC(), 4);
        stack.pushC(2);
        Assertions.assertEquals(stack.peekC(), 2);

        // Test resize
        stack.pushA(11);
        stack.pushB(15);
        stack.pushC(12);
        stack.pushC(14);
        Assertions.assertEquals(stack.peekA(), 11);
        Assertions.assertEquals(stack.peekB(), 15);
        Assertions.assertEquals(stack.peekC(), 14);

        // Test pop
        Assertions.assertEquals(stack.popA(), 11);
        Assertions.assertEquals(stack.peekA(), 1);
        Assertions.assertEquals(stack.popB(), 15);
        Assertions.assertEquals(stack.peekB(), 5);
        Assertions.assertEquals(stack.popC(), 14);
        Assertions.assertEquals(stack.popC(), 12);
        Assertions.assertEquals(stack.peekC(), 2);
    }

    @Test
    @DisplayName("Stack Min")
    void stackMinTest() {
        ChapterThree.MinStack<Integer> minStack = new ChapterThree.MinStack<>();
        minStack.push(10);
        Assertions.assertEquals(minStack.getMin(), 10);
        minStack.push(5);
        Assertions.assertEquals(minStack.getMin(), 5);
        minStack.pop();
        Assertions.assertEquals(minStack.getMin(), 10);
        minStack.push(9);
        minStack.push(14);
        Assertions.assertEquals(minStack.getMin(), 9);
        minStack.pop();
        Assertions.assertEquals(minStack.getMin(), 9);
        minStack.pop();
        Assertions.assertEquals(minStack.getMin(), 10);
    }

    @Test
    @DisplayName("Stack of Plates")
    void stackOfPlatesTest() {
        ChapterThree.SetOfStack<Integer> stack = new ChapterThree.SetOfStack<>(3);
        for (int i = 0; i < 30; i++) stack.push(i);
        Assertions.assertEquals(stack.peek(), 29);
        stack.pop();
        Assertions.assertEquals(stack.peek(), 28);
        Assertions.assertEquals(stack.popAt(1), 2);
        Assertions.assertEquals(stack.popAt(1), 1);
        Assertions.assertEquals(stack.popAt(1), 0);
        Assertions.assertEquals(stack.popAt(9), 28);
        Assertions.assertEquals(stack.popAt(8), 26);
        stack.push(31);
        stack.push(32);
        Assertions.assertEquals(stack.peek(), 32);
        Assertions.assertEquals(stack.pop(), 32);
        Assertions.assertEquals(stack.peek(), 31);
    }

    @Test
    @DisplayName("Queue via Stack")
    void queueViaStackTest() {
        ChapterThree.MyQueue<Integer> queue = new ChapterThree.MyQueue<>();
        Assertions.assertTrue(queue.isEmpty());
        for (int i = 1; i <= 10; i++) queue.add(i);
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals(queue.peek(), 1);
        for (int i = 1; i <= 5; i++) Assertions.assertEquals(queue.remove(), i);
        for (int i = 20; i <= 25; i++) queue.add(i);
        for (int i = 6; i <= 10; i++) Assertions.assertEquals(queue.remove(), i);
        Assertions.assertEquals(queue.peek(), 20);
    }

    @Test
    @DisplayName("Sort Stack")
    void sortStackTest() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) stack.push(i);
        ChapterThree.sortStack(stack);
        StringJoiner sj = new StringJoiner(",");
        while (!stack.isEmpty()) sj.add("" + stack.pop());
        Assertions.assertEquals(sj.toString(), "0,1,2,3,4,5,6,7,8,9");

        int[] sorted = new int[50];
        for (int i = 0; i < 50; i++) {
            int random = (int) (Math.random() * 100);
            sorted[i] = random;
            stack.push(random);
        }

        Arrays.sort(sorted);
        StringJoiner sortedResult = new StringJoiner(",");
        for (int num : sorted) sortedResult.add(num + "");
        sj = new StringJoiner(",");
        ChapterThree.sortStack(stack);
        while (!stack.isEmpty()) sj.add(stack.pop() + "");
        Assertions.assertEquals(sj.toString(), sortedResult.toString());
    }

    @Test
    @DisplayName("Animal Shelter")
    void animalShelterTest() {
        ChapterThree.AdoptionService adoptionService = new ChapterThree.AdoptionService();
        String[] receiveOrder = new String[] {
                "cat", "cat", "cat",
                "dog", "cat", "dog",
                "dog", "cat", "cat"
        };
        for (String animal : receiveOrder) adoptionService.receiveAnimal(animal);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.CAT);
        Assertions.assertEquals(adoptionService.adopt("dog").type, Animal.Type.DOG);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.CAT);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.CAT);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.CAT);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.DOG);
        Assertions.assertEquals(adoptionService.adopt("cat").type, Animal.Type.CAT);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.DOG);
        Assertions.assertEquals(adoptionService.adopt().type, Animal.Type.CAT);
    }
}
