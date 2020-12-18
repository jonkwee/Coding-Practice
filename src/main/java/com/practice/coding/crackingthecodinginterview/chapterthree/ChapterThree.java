package com.practice.coding.crackingthecodinginterview.chapterthree;

import java.util.*;

public class ChapterThree {

    // 3.1
    // Describe how you could use a single array to implement three stacks.
    static class ThreeStackArray<T> {

        private T[] array;
        private int stackAIndex;
        private int stackBIndex;
        private boolean stackCIsLeft;
        private int stackCDistance;

        public ThreeStackArray(int initialArraySize) {
            if (initialArraySize < 3) throw new IllegalArgumentException("Initial Array Size must be 3 or greater!");
            this.array = (T[]) new Object[initialArraySize];
            stackAIndex = 0;
            stackBIndex = array.length - 1;
            stackCDistance = 0;
        }

        public Object peekA() { return array[stackAIndex - 1]; }
        public Object peekB() { return array[stackBIndex + 1]; }
        public Object peekC() { return array[stackCIsLeft ?
                (array.length / 2) + stackCDistance : (array.length / 2) - (stackCDistance - 1)]; }

        public boolean isEmptyA() { return stackAIndex == 0; }
        public boolean isEmptyB() { return stackBIndex == array.length; }
        public boolean isEmptyC() { return stackCDistance == 0; }

        public void pushA(T item) {
            // if stackA is overlapping with stackC, resize
            if (stackAIndex == (array.length / 2) + (stackCDistance - 1) && !stackCIsLeft) {
                resizeArray();
            }
            array[stackAIndex++] = item;
        }

        public void pushB(T item) {
            // if stackB is overlapping with stackC, resize
            if (stackBIndex == (array.length / 2) + stackCDistance && stackCIsLeft) {
                resizeArray();
            }
            array[stackBIndex--] = item;
        }

        public void pushC(T item) {
            // if stackC overlaps with stackA or stackC overlaps with stackB, resize
            if ((stackAIndex == (array.length / 2) + (stackCDistance - 1) && !stackCIsLeft) ||
                    (stackBIndex == (array.length / 2) + stackCDistance && stackCIsLeft)) {
                resizeArray();
            }
            if (stackCIsLeft) {
                array[(array.length / 2) - stackCDistance] = item;
                stackCIsLeft = false;
                stackCDistance++;
            } else {
                array[(array.length / 2) + stackCDistance] = item;
                stackCIsLeft = true;
            }
        }

        public T popA() {
            if (stackAIndex == 0) throw new EmptyStackException();
            return (T) array[--stackAIndex];
        }

        public T popB() {
            if (stackBIndex == array.length - 1) throw new EmptyStackException();
            return (T) array[++stackBIndex];
        }

        public T popC() {
            if (stackCDistance == 0) throw new EmptyStackException();
            T returnable = null;
            if (stackCIsLeft) {
                returnable = (T) array[(array.length / 2) + stackCDistance];
                stackCIsLeft = false;
            } else {
                returnable = (T) array[(array.length / 2) - (stackCDistance - 1)];
                stackCIsLeft = true;
                stackCDistance--;
            }
            return returnable;
        }

        private void resizeArray() {
            int newSize = array.length * 2;
            T[] newArray = (T[]) new Object[newSize];
            // Move all elements from stackA
            for (int i = 0; i < stackAIndex; i++) newArray[i] = array[i];
            // Move all elements from stackB
            int arrayDiff = newSize - array.length;
            for (int i = array.length - 1; i > stackBIndex; i--) newArray[i + arrayDiff] = array[i];
            stackBIndex = arrayDiff + (stackBIndex);
            // Move all elements from stackC
            for (int i = 0; i <= stackCDistance; i++) {
                if (i == 0) newArray[newSize / 2] = array[array.length / 2];
                else {
                    if ((stackCIsLeft && i == stackCDistance) || i < stackCDistance) {
                        newArray[(newSize / 2) + i] = array[(array.length / 2) + i];
                    }
                    if ((!stackCIsLeft && i == stackCDistance) || i < stackCDistance) {
                        newArray[(newSize / 2) - i] = array[(array.length / 2) - i];
                    }
                }
            }
            this.array = newArray;
        }

        @Override
        public String toString() {
            return Arrays.toString(array);
        }

    }

    // 3.2
    // How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
    // Push, pop and min should all operate in 0(1) time.
    static class MinStackElement<T extends Comparable> {
        private T element;
        private T min;

        public MinStackElement(T element, T min) {
            this.element = element;
            this.min = min;
        }

        public void setElement(T element) { this.element = element; }
        public void setMin(T min) { this.min = min; }
        public T getElement() { return this.element; }
        public T getMin() { return this.min; }
    }

    static class MinStack<T extends Comparable> {
        private Stack<MinStackElement<T>> stack = new Stack<>();
        private T currentMin = null;

        public T peek() {
            if (stack.isEmpty()) throw new EmptyStackException();
            return stack.peek().element;
        }

        public boolean isEmpty() { return stack.isEmpty(); }

        public void push(T item) {
            if (stack.isEmpty()) {
                currentMin = item;
            } else {
                if (item.compareTo(currentMin) < 0) {
                    currentMin = item;
                }
            }
            stack.push(new MinStackElement<>(item, currentMin));
        }

        public T pop() {
            if (stack.isEmpty()) throw new EmptyStackException();
            MinStackElement<T> element = stack.pop();
            if (element.min == currentMin && !stack.isEmpty()) currentMin = stack.peek().getMin();
            return element.getElement();
        }

        public T getMin() {
            return currentMin;
        }
    }

    // 3.3
    // Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
    // Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
    // threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
    // composed of several stacks and should create a new stack once the previous one exceeds capacity.
    // SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
    // (that is, pop() should return the same values as it would if there were just a single stack).
    // FOLLOW UP
    // Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
    static class SetOfStack<T> {
        private T[] array;
        private final int stackCapacity;
        private int currentIndex;
        private int[] stackIndicies;

        public SetOfStack(int stackCapacity) {
            this.stackCapacity = stackCapacity;
            this.array = (T[]) new Object[stackCapacity];
            this.currentIndex = 0;
            this.stackIndicies = new int[] {currentIndex};
        }

        public T peek() {
            if (currentIndex == 0) throw new EmptyStackException();
            return (T) array[currentIndex - 1];
        }

        public boolean isEmpty() { return currentIndex == 0; }

        public T pop() {
            currentIndex--;
            T returnable = array[currentIndex];
            array[currentIndex] = null;
            stackIndicies[stackIndicies.length - 1] = currentIndex - 1;
            return returnable;
        }

        public void push(T item) {
            if (currentIndex == array.length) {
                T[] newArray = (T[]) new Object[array.length + stackCapacity];
                System.arraycopy(array, 0, newArray, 0, array.length);
                this.array = newArray;

                int[] newStackIndices = new int[stackIndicies.length + 1];
                System.arraycopy(stackIndicies, 0, newStackIndices, 0, stackIndicies.length);
                this.stackIndicies = newStackIndices;
                newStackIndices[newStackIndices.length - 1] = currentIndex;
            } else {
                stackIndicies[stackIndicies.length - 1] = currentIndex;
            }
            array[currentIndex++] = item;
        }

        // Pops from a specified stack and if stack after pop is empty,
        // compact values to be more space efficient.
        // stackNumber starts from 1
        public T popAt(int stackNumber) {
            int maxIndexAtStack = (stackNumber * stackCapacity) - 1;
            if (currentIndex <= maxIndexAtStack) {
                return pop();
            } else {
                T returnable = array[stackIndicies[stackNumber - 1]];
                if (stackIndicies[stackNumber - 1] % stackCapacity == 0) {
                    // compact values
                    int[] compactStackIndicies = new int[stackIndicies.length - 1];
                    for (int i = 0; i < stackIndicies.length; i++) {
                        if (i != stackNumber - 1) {
                            if (i < stackNumber - 1) {
                                compactStackIndicies[i] = stackIndicies[i];
                            } else {
                                compactStackIndicies[i - 1] = stackIndicies[i];
                            }
                        }
                    }
                    stackIndicies = compactStackIndicies;
                } else {
                    stackIndicies[stackNumber - 1] -= 1;
                }
                return returnable;
            }
        }
    }

    // 3.4
    // Implement a MyQueue class which implements a queue using two stacks.
    static class MyQueue<T> {

        // Stack one is used as the tail of the queue, adding elements in
        private final Stack<T> stackOne = new Stack<>();

        // Stack two is used as the head of the queue, retrieving elements from
        private final Stack<T> stackTwo = new Stack<>();

        public T peek() {
            if (this.isEmpty()) return null;
            if (stackTwo.isEmpty()) {
                while (!stackOne.isEmpty()) stackTwo.push(stackOne.pop());
            }
            return stackTwo.peek();
        }

        public boolean isEmpty() { return stackOne.isEmpty() && stackTwo.isEmpty(); }

        public void add(T item) { stackOne.push(item); }

        public T remove() {
            if (this.isEmpty()) return null;
            if (stackTwo.isEmpty()) {
                while (!stackOne.isEmpty()) stackTwo.push(stackOne.pop());
            }
            return stackTwo.pop();
        }
    }

    // 3.5
    // Write a program to sort a stack such that the smallest items are on the top.
    // You can use an additional temporary stack, but you may not copy the elements into any other data structure
    // (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
    public static <T extends Comparable<T>> void sortStack(Stack<T> stack) {
        // Maintain a temp stack for temporary storage while popping values off main stack.
        // Outcome of temp stack should have largest element on the top of stack.
        Stack<T> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            // Pop each element from main stack
            T mainStackElement = stack.pop();
            // if temp stack is empty, push element in
            if (tempStack.isEmpty()) {
                tempStack.push(mainStackElement);
            } else {
                // if temp stack is not empty, peek temp stack.
                // if element is smaller than popped element from main stack, the push element onto temp stack
                // if not, then keep popping elements of temp stack onto main stack until condition satisfy.
                // After, push everything back in.
                int poppedCounter = 0;
                while (!tempStack.isEmpty() && tempStack.peek().compareTo(mainStackElement) > 0) {
                    stack.push(tempStack.pop());
                    poppedCounter += 1;
                }
                tempStack.push(mainStackElement);
                while (poppedCounter > 0) {
                    tempStack.push(stack.pop());
                    poppedCounter -= 1;
                }
            }
        }

        // push everything from temp stack to main stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

    }

    // 3.6
    // An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis.
    // People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
    // or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
    // They cannot select which specific animal they would like.
    // Create the data structures to maintain this system and implement operations
    // such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
    // You may use the built-in Linked list data structure.
    static class AdoptionService {
        // Maintain two queues for each animal type
        private Queue<Animal> dogQueue = new LinkedList<>();
        private Queue<Animal> catQueue = new LinkedList<>();
        private int receiveCounter = 1;

        public void receiveAnimal(String animal) {
            if ("dog".equalsIgnoreCase(animal)) {
                dogQueue.add(new Animal(Animal.Type.DOG, receiveCounter++));
            } else if ("cat".equalsIgnoreCase(animal)) {
                catQueue.add(new Animal(Animal.Type.CAT, receiveCounter++));
            } else {
                throw new IllegalArgumentException("We only accept dogs or cats!");
            }
        }

        // No preference so return the oldest animal in system
        public Animal adopt() {
            if (!catQueue.isEmpty() && !dogQueue.isEmpty()) {
                Animal oldestCat = catQueue.peek();
                Animal oldestDog = dogQueue.peek();
                return (oldestDog.order > oldestCat.order) ? catQueue.poll() : dogQueue.poll();
            } else if (!catQueue.isEmpty()) {
                return catQueue.poll();
            } else {
                return dogQueue.poll();
            }
        }

        public Animal adopt(String animal) {
            if ("dog".equalsIgnoreCase(animal)) {
                return dogQueue.poll();
            } else if ("cat".equalsIgnoreCase(animal)) {
                return catQueue.poll();
            } else {
                throw new IllegalArgumentException("You can only adopt a dog or cat!");
            }
        }

    }


}
