package com.practice.coding.crackingthecodinginterview.chaptertwo;

import java.util.HashSet;
import java.util.Set;

public class ChapterTwo {

    // 2.1
    // Write code to remove duplicates from an unsorted linked list.
    // FOLLOW UP How would you solve this problem if a temporary buffer is not allowed?
    public static <T> LinkedListNode<T> removeDups(LinkedListNode<T> head) {
        // Create HashSet to keep track of visited values
        Set<T> visitedValues = new HashSet<>();
        // keep a pointer on current node of linked list and traverse through linked list
        // also keep a pointer on previous node for deleting duplicated values
        LinkedListNode<T> previous = head;
        visitedValues.add(previous.getValue());
        LinkedListNode<T> current = previous.getNext();
        while (current != null) {
            if (visitedValues.contains(current.getValue())) {
                // if current node's value exist in hashset, adjust previous node's next to point to current node's next
                previous.setNext(current.getNext());
                current = current.getNext();
            } else {
                // if current node's value does not exist in hashset, add it
                visitedValues.add(current.getValue());
                previous = current;
                current = current.getNext();
            }

        }
        return head;
    }

    public static <T> LinkedListNode<T> removeDupsWithoutTempBuffer(LinkedListNode<T> head) {
        // Create two pointers, one to keep track of current node, and one to lookahead
        LinkedListNode<T> current = head;
        LinkedListNode<T> lookahead = head;
        // Iterate through LinkedList using current and iterate through the entire list using lookahead
        // for each movement done on current.
        while (current != null) {
            lookahead = current;
            // if lookahead's next value is equal to current, then remove next reference
            while (lookahead != null && lookahead.getNext() != null) {
                if (lookahead.getNext().getValue().equals(current.getValue())) {
                    lookahead.setNext(lookahead.getNext().getNext());
                } else {
                    lookahead = lookahead.getNext();
                }
            }
            current = current.getNext();
        }

        return head;
    }

    // 2.2
    // Implement an algorithm to find the kth to last element of a singly linked list.
    public static <T> LinkedListNode<T> findKthToLastElement(LinkedListNode<T> head, int k) {
        // Initialize two pointers, one fast pointer and one slow pointer, both starting at head
        // slow pointer will be kth distance away from fast pointer
        LinkedListNode<T> fastPointer = head;
        LinkedListNode<T> slowPointer = head;
        int kDistance = 0;

        while (fastPointer != null) {
            if (kDistance >= k) {
                slowPointer = slowPointer.getNext();
            }
            // Once fast pointer is kth distance away from slow pointer, start to move slow pointer
            fastPointer = fastPointer.getNext();
            kDistance += 1;
        }

        return slowPointer;
    }

    // 2.3
    // Implement an algorithm to delete a node in the middle
    // (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list,
    // given only access to that node.
    public static <T> void deleteMiddleNode(LinkedListNode<T> node) {
        // keep track of two pointers, previous and current where previous is provided node and current is one next
        LinkedListNode<T> previous = node;
        LinkedListNode<T> current = node.getNext();
        // for every node after provided node, swap current node's value to previous node value
        while (current != null) {
            previous.setValue(current.getValue());
            // one current node's next is null, use previous to delete current node
            if (current.getNext() == null) {
                previous.setNext(null);
                break;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
    }

    // 2.4
    // Write code to partition a linked list around a value x,
    // such that all nodes less than x come before all nodes greater than or equal to x.
    // If x is contained within the list, the values of x only need to be after the elements less than x.
    // The partition element x can appear anywhere in the "right partition";
    // it does not need to appear between the left and right partitions.
    public static <T extends Comparable<T>> LinkedListNode<T> partition(LinkedListNode<T> head, T value) {
        // Create two segments of linked list
        // First segment consists all elements less than value
        // Second segment consists all element greater than and equal to value
        // keep track of both head and tail of segments
        LinkedListNode<T> lessThanHead = null;
        LinkedListNode<T> lessThanTail = null;
        LinkedListNode<T> greaterThanHead = null;
        LinkedListNode<T> greaterThanTail = null;

        LinkedListNode<T> current = head;
        while (current != null) {
            if (current.getValue().compareTo(value) < 0) {
                Object[] newPointers = partitionHelper(lessThanHead, lessThanTail, current.getValue());
                lessThanHead = (LinkedListNode<T>) newPointers[0];
                lessThanTail = (LinkedListNode<T>) newPointers[1];
            } else {
                Object[] newPointers = partitionHelper(greaterThanHead, greaterThanTail, current.getValue());
                greaterThanHead = (LinkedListNode<T>) newPointers[0];
                greaterThanTail = (LinkedListNode<T>) newPointers[1];
            }
            current = current.getNext();
        }

        // stitch together segments to create array
        if (lessThanTail == null) {
            return greaterThanHead;
        } else {
            lessThanTail.setNext(greaterThanHead);
            return lessThanHead;
        }
    }

    public static <T> Object[] partitionHelper(LinkedListNode<T> head, LinkedListNode<T> tail, T value) {
        if (head == null) {
            head = new LinkedListNode<>(value);
            tail = head;
        } else {
            LinkedListNode<T> next = new LinkedListNode<>(value);
            tail.setNext(next);
            tail = tail.getNext();
        }
        return new Object[] {head, tail};
    }

    // 2.5
    // You have two numbers represented by a linked list, where each node contains a single digit.
    // The digits are stored in reverse order, such that the 1 's digit is at the head of the list.
    // Write a function that adds the two numbers and returns the sum as a linked list.
    public static LinkedListNode<Integer> sumList(LinkedListNode<Integer> firstNum, LinkedListNode<Integer> secondNum) {
        // Initialize two pointers to track firstNum and secondNum
        LinkedListNode<Integer> firstNumPointer = firstNum;
        LinkedListNode<Integer> secondNumPointer = secondNum;

        LinkedListNode<Integer> result = null;
        LinkedListNode<Integer> resultPointer = null;
        Integer carry = 0;
        // Iterate through next pointers together and add the two values from firstNum and secondNum
        while (firstNumPointer != null || secondNumPointer != null) {
            int valueSum = 0;

            // Sum up values with carry depending on situation
            if (firstNumPointer != null && secondNumPointer != null) {
                valueSum = firstNumPointer.getValue() + secondNumPointer.getValue() + carry;
                firstNumPointer = firstNumPointer.getNext();
                secondNumPointer = secondNumPointer.getNext();
            } else if (secondNumPointer != null) {
                // if firstNum becomes null, add carry to next value of secondNum
                valueSum = secondNumPointer.getValue() + carry;
                secondNumPointer = secondNumPointer.getNext();
            } else {
                // if secondNum becomes null, add carry to next value of firstNum
                valueSum = firstNumPointer.getValue() + carry;
                firstNumPointer = firstNumPointer.getNext();
            }

            // Find carry value and reminder values
            if (valueSum > 9) {
                carry = valueSum / 10;
                valueSum %= 10;
            } else {
                carry = 0;
            }

            // add result to result
            LinkedListNode<Integer> temp = new LinkedListNode<>(valueSum);
            if (result == null) {
                result = temp;
                resultPointer = result;
            } else {
                resultPointer.setNext(temp);
                resultPointer = resultPointer.getNext();
            }

        }

        // if carry is still left over, create new node for result
        if (carry != 0) resultPointer.setNext(new LinkedListNode<>(carry));

        return result;
    }

    // 2.5
    // FOLLOW UP
    // Suppose the digits are stored in forward order. Repeat the above problem.
    public static LinkedListNode<Integer> sumListFollowUp(LinkedListNode<Integer> firstNum, LinkedListNode<Integer> secondNum) {
        // Find length of the two linkedList
        int firstNumLength = firstNum.length();
        int secondNumLength = secondNum.length();
        int diff = 0;
        boolean isFirstNumShorter = false;
        if (firstNumLength > secondNumLength) {
            diff = firstNumLength - secondNumLength;
        } else if (firstNumLength < secondNumLength) {
            diff = secondNumLength - firstNumLength;
            isFirstNumShorter = true;
        }
        // Recursively call a helper method which returns the carry of the sum of the next two numbers
        LinkedListNode<Integer> result = findCarry(firstNum, secondNum, diff, isFirstNumShorter);
        if (result.getValue() > 9) {
            LinkedListNode<Integer> newHead = new LinkedListNode<>(Math.floorDiv(result.getValue(), 10));
            result.setValue(result.getValue() % 10);
            newHead.setNext(result);
            result = newHead;
        }
        return result;
    }

    public static LinkedListNode<Integer> findCarry(LinkedListNode<Integer> nextFirstNum, LinkedListNode<Integer> nextSecondNum,
                                    int diff, boolean isFirstNumShorter) {
        // Base Cases:
        // if nextFirstNum and nextSecondNum is null, return null
        if (nextFirstNum == null && nextSecondNum == null) return null;

        // else
        else {
            // if diff not zero, then call longer node + findCarry().getValue() / 10 while decrementing diff as argument
            if (diff != 0) {
                if (isFirstNumShorter) {
                    LinkedListNode<Integer> recursiveOutcome = findCarry(nextFirstNum, nextSecondNum.getNext(), diff - 1, true);
                    LinkedListNode<Integer> result = new LinkedListNode<>(nextSecondNum.getValue() +
                            Math.floorDiv(recursiveOutcome.getValue(), 10));
                    recursiveOutcome.setValue(recursiveOutcome.getValue() % 10);
                    result.setNext(recursiveOutcome);
                    return result;
                } else {
                    LinkedListNode<Integer> recursiveOutcome = findCarry(nextFirstNum.getNext(), nextSecondNum, diff - 1, false);
                    LinkedListNode<Integer> result = new LinkedListNode<>(nextFirstNum.getValue() +
                            Math.floorDiv(recursiveOutcome.getValue(), 10));
                    recursiveOutcome.setValue(recursiveOutcome.getValue() % 10);
                    result.setNext(recursiveOutcome);
                    return result;
                }
            } else {
                // if diff is zero, add current two node values + findCarry().getValue() / 10 [need to null check]
                LinkedListNode<Integer> recursiveOutcome = findCarry(nextFirstNum.getNext(), nextSecondNum.getNext(), 0, isFirstNumShorter);
                LinkedListNode<Integer> result = null;
                if (recursiveOutcome == null) {
                    result = new LinkedListNode<>(nextFirstNum.getValue() + nextSecondNum.getValue());
                } else {
                    result = new LinkedListNode<>(nextFirstNum.getValue() + nextSecondNum.getValue() +
                            Math.floorDiv(recursiveOutcome.getValue(), 10));
                    recursiveOutcome.setValue(recursiveOutcome.getValue() % 10);
                    result.setNext(recursiveOutcome);
                }

                return result;
            }
        }
    }

    // 2.6
    // Implement a function to check if a linked list is a palindrome.
    public static boolean isPalindrome(LinkedListNode<Character> head) {
        LinkedListNode<Character> headCopy = new LinkedListNode<>(head.getValue());
        headCopy.setNext(head.getNext());
        return isPalindromeHelper(headCopy, head, new boolean[] {false});
    }

    public static boolean isPalindromeHelper(LinkedListNode<Character> front, LinkedListNode<Character> back, boolean[] isCompleted) {
        if (back == null) {
            return true;
        }

        // Iterate all the way to the end of linked list while keeping reference to front
        boolean result = isPalindromeHelper(front, back.getNext(), isCompleted);
        // if front and back intersects, means backtracking is done so can return result without undergoing logic
        if (isCompleted[0]) return result;
        // if front and back values are the same, move front to the next value and recursion stack will take care of back
        if (front.getValue() == back.getValue()) {
            LinkedListNode<Character> temp = front.getNext();
            if (temp == back) isCompleted[0] = true;
            front.setValue(temp.getValue());
            return result;
        } else {
            return false;
        }
    }

    // 2.7
    // Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
    // Note that the intersection is defined based on reference, not value.
    // That is, if the kth node of the first linked list is the exact same node (by reference)
    // as the jth node of the second linked list, then they are intersecting.
    public static LinkedListNode<?> hasIntersection(LinkedListNode<?> listA, LinkedListNode<?> listB) {
        // find length of the two lists
        int lengthA = listA.length();
        int lengthB = listB.length();
        // iterate the longest list until current node has n nodes left where n is the length of the shorter list.
        LinkedListNode<?> longerList = lengthA >= lengthB ? listA : listB;
        LinkedListNode<?> shorterList = lengthA >= lengthB ? listB : listA;
        int diff = Math.abs(lengthA - lengthB);
        while (diff-- != 0) longerList = longerList.getNext();
        // then iterate the two lists together, comparing each node with one another.
        while (longerList != null) {
            // if equal then intersection
            if (longerList == shorterList) return longerList;
            // if not then no intersection
            else {
                longerList = longerList.getNext();
                shorterList = shorterList.getNext();
            }
        }
        return null;
    }

    // 2.8
    // Given a circular linked list, implement an algorithm that returns the node at the
    // beginning of the loop.
    public static LinkedListNode<?> isCircular(LinkedListNode<?> list) {
        // Use Floyd circular list detection algorithm
        // Start off with two pointers from the head
        LinkedListNode<?> slowPointer = list;
        LinkedListNode<?> fastPointer = list;
        // Move the first pointer one step and the second pointer two steps after each iteration
        while (fastPointer != null) {
            if (fastPointer.getNext() != null) fastPointer = fastPointer.getNext().getNext();
            else fastPointer = null;
            slowPointer = slowPointer.getNext();
            // If first pointer points to the same node as second pointer, then there is a loop
            // Stop the iteration
            if (fastPointer == slowPointer) break;
        }
        // if the faster pointer is null when outside of loop, then the list is not circular
        if (fastPointer == null) return null;
        // reset one of the pointers to the start of the list and then move each pointer one step at a time
        // the next node they meet together is the start of the loop
        fastPointer = list;
        while (fastPointer != slowPointer) {
            fastPointer = fastPointer.getNext();
            slowPointer = slowPointer.getNext();
        }
        return fastPointer;
    }



}
