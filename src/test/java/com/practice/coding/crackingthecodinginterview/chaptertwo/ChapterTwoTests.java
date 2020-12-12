package com.practice.coding.crackingthecodinginterview.chaptertwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class ChapterTwoTests {

    @Test
    @DisplayName("Remove Dups")
    void removeDupsTest() {
        LinkedListNode<String> head = LinkedListNode.initializeLinkedList(Arrays.asList("apple", "pie", "with", "more", "apple"));
        Assertions.assertEquals(ChapterTwo.removeDups(head).toString(), "apple,pie,with,more");
        head = LinkedListNode.initializeLinkedList(Arrays.asList("apple", "pie", "with", "more", "apple"));
        Assertions.assertEquals(ChapterTwo.removeDupsWithoutTempBuffer(head).toString(), "apple,pie,with,more");

        head = LinkedListNode.initializeLinkedList(Arrays.asList("how", "many", "dups", "dups", "dups", "can", "i", "dups"));
        Assertions.assertEquals(ChapterTwo.removeDups(head).toString(), "how,many,dups,can,i");
        head = LinkedListNode.initializeLinkedList(Arrays.asList("how", "many", "dups", "dups", "dups", "can", "i", "dups"));
        Assertions.assertEquals(ChapterTwo.removeDupsWithoutTempBuffer(head).toString(), "how,many,dups,can,i");
    }

    @Test
    @DisplayName("Return Kth to Last")
    void returnKthToLastTest() {
        LinkedListNode<Integer> head = LinkedListNode.initializeLinkedList(Arrays.asList(1,2,3,4,5,6));
        LinkedListNode<Integer> nodeToBeFound = head.getNext().getNext().getNext().getNext();
        Assertions.assertEquals(ChapterTwo.findKthToLastElement(head, 2), nodeToBeFound);

        nodeToBeFound = head.getNext().getNext().getNext().getNext().getNext();
        Assertions.assertEquals(ChapterTwo.findKthToLastElement(head, 1), nodeToBeFound);

        nodeToBeFound = head;
        Assertions.assertEquals(ChapterTwo.findKthToLastElement(head, 6), nodeToBeFound);
    }

    @Test
    @DisplayName("Delete Middle Node")
    void deleteMiddleNodeTest() {
        LinkedListNode<Integer> head = LinkedListNode.initializeLinkedList(Arrays.asList(1,2,3,4,5));
        LinkedListNode<Integer> nodeToBeDeleted = head.getNext().getNext().getNext();
        ChapterTwo.deleteMiddleNode(nodeToBeDeleted);
        Assertions.assertEquals(head.toString(), "1,2,3,5");

        nodeToBeDeleted = head.getNext().getNext();
        ChapterTwo.deleteMiddleNode(nodeToBeDeleted);
        Assertions.assertEquals(head.toString(), "1,2,5");

        nodeToBeDeleted = head.getNext();
        ChapterTwo.deleteMiddleNode(nodeToBeDeleted);
        Assertions.assertEquals(head.toString(), "1,5");
    }

    @Test
    @DisplayName("Partition")
    void partitionTest() {
        LinkedListNode<Integer> head = LinkedListNode.initializeLinkedList(Arrays.asList(5,1,8,4,9,3));
        LinkedListNode<Integer> partitionedByFour = LinkedListNode.initializeLinkedList(Arrays.asList(1,3,5,8,4,9));
        Assertions.assertEquals(ChapterTwo.partition(head, 4).toString(), partitionedByFour.toString());

        head = LinkedListNode.initializeLinkedList(Arrays.asList(6,3));
        partitionedByFour = LinkedListNode.initializeLinkedList(Arrays.asList(3,6));
        Assertions.assertEquals(ChapterTwo.partition(head, 4).toString(), partitionedByFour.toString());

        head = LinkedListNode.initializeLinkedList(Collections.singletonList(8));
        partitionedByFour = LinkedListNode.initializeLinkedList(Collections.singletonList(8));
        Assertions.assertEquals(ChapterTwo.partition(head, 4).toString(), partitionedByFour.toString());
    }

    @Test
    @DisplayName("Sum List")
    void sumListTest() {
        LinkedListNode<Integer> num1 = LinkedListNode.initializeLinkedList(Arrays.asList(7, 1, 6));
        LinkedListNode<Integer> num2 = LinkedListNode.initializeLinkedList(Arrays.asList(5, 9, 2));
        LinkedListNode<Integer> sum = LinkedListNode.initializeLinkedList(Arrays.asList(2, 1, 9));
        Assertions.assertEquals(ChapterTwo.sumList(num1, num2).toString(), sum.toString());

        num1 = LinkedListNode.initializeLinkedList(Arrays.asList(9, 9, 9));
        num2 = LinkedListNode.initializeLinkedList(Collections.singletonList(1));
        sum = LinkedListNode.initializeLinkedList(Arrays.asList(0, 0, 0, 1));
        Assertions.assertEquals(ChapterTwo.sumList(num1, num2).toString(), sum.toString());

        num1 = LinkedListNode.initializeLinkedList(Collections.singletonList(6));
        num2 = LinkedListNode.initializeLinkedList(Collections.singletonList(4));
        sum = LinkedListNode.initializeLinkedList(Arrays.asList(0, 1));
        Assertions.assertEquals(ChapterTwo.sumList(num1, num2).toString(), sum.toString());

        num1 = LinkedListNode.initializeLinkedList(Arrays.asList(7, 1, 6));
        num2 = LinkedListNode.initializeLinkedList(Arrays.asList(5, 9, 2));
        sum = LinkedListNode.initializeLinkedList(Arrays.asList(1,3,0,8));
        Assertions.assertEquals(ChapterTwo.sumListFollowUp(num1, num2).toString(), sum.toString());

        num1 = LinkedListNode.initializeLinkedList(Arrays.asList(9, 9, 9));
        num2 = LinkedListNode.initializeLinkedList(Collections.singletonList(1));
        sum = LinkedListNode.initializeLinkedList(Arrays.asList(1, 0, 0, 0));
        Assertions.assertEquals(ChapterTwo.sumListFollowUp(num1, num2).toString(), sum.toString());

        num1 = LinkedListNode.initializeLinkedList(Collections.singletonList(6));
        num2 = LinkedListNode.initializeLinkedList(Collections.singletonList(4));
        sum = LinkedListNode.initializeLinkedList(Arrays.asList(1, 0));
        Assertions.assertEquals(ChapterTwo.sumListFollowUp(num1, num2).toString(), sum.toString());
    }

    @Test
    @DisplayName("Palindrome")
    void palindromeTest() {
        LinkedListNode<Character> string = LinkedListNode.initializeLinkedList(Arrays.asList('a', 'b', 'a'));
        Assertions.assertTrue(ChapterTwo.isPalindrome(string));

        string = LinkedListNode.initializeLinkedList(Arrays.asList('a', 'b', 'b', 'a'));
        Assertions.assertTrue(ChapterTwo.isPalindrome(string));

        string = LinkedListNode.initializeLinkedList(Arrays.asList('a', 'b', 'c', 'd', 'a'));
        Assertions.assertFalse(ChapterTwo.isPalindrome(string));

        string = LinkedListNode.initializeLinkedList(Arrays.asList('a', 'b', 'b', 'd'));
        Assertions.assertFalse(ChapterTwo.isPalindrome(string));
    }

    @Test
    @DisplayName("Intersection")
    void intersectionTest() {
        LinkedListNode<Integer> listA = LinkedListNode.initializeLinkedList(Arrays.asList(4, 5, 6));
        LinkedListNode<Integer> listB = LinkedListNode.initializeLinkedList(Arrays.asList(1, 3, 4));
        LinkedListNode<Integer> intersectingNode = listA.getNext();
        listB.getNext().getNext().setNext(intersectingNode);
        Assertions.assertEquals(ChapterTwo.hasIntersection(listA, listB), intersectingNode);

        listA = LinkedListNode.initializeLinkedList(Arrays.asList(3, 5, 1));
        listB = LinkedListNode.initializeLinkedList(Arrays.asList(3, 5, 1));
        Assertions.assertNotEquals(ChapterTwo.hasIntersection(listA, listB), listA);
        Assertions.assertNotEquals(ChapterTwo.hasIntersection(listA, listB), listB);
    }

    @Test
    @DisplayName("Loop Detection")
    void loopDetecionTest() {
        LinkedListNode<Integer> listA = LinkedListNode.initializeLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        listA.getNext().getNext().getNext().getNext().setNext(listA);
        Assertions.assertEquals(ChapterTwo.isCircular(listA), listA);

        listA = LinkedListNode.initializeLinkedList(Arrays.asList(4, 8, 10, 2, 3, 4));
        LinkedListNode<Integer> startOfLoop = listA.getNext().getNext();
        startOfLoop.getNext().getNext().getNext().setNext(startOfLoop);
        Assertions.assertEquals(ChapterTwo.isCircular(listA), startOfLoop);

        listA = LinkedListNode.initializeLinkedList(Arrays.asList(5,9,10,3,2,1,10));
        Assertions.assertNotEquals(ChapterTwo.isCircular(listA), listA.getNext().getNext());
    }


}
