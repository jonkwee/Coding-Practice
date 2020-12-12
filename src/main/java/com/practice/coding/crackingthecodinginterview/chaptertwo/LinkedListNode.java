package com.practice.coding.crackingthecodinginterview.chaptertwo;

import java.util.Collection;
import java.util.StringJoiner;

public class LinkedListNode<T> {

    private T value;
    private LinkedListNode<T> next;

    public LinkedListNode(T value) {
        this.value = value;
    }

    public LinkedListNode() {}


    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int length() {
        LinkedListNode<T> temp = this;
        int length = 0;
        while (temp != null) {
            temp = temp.getNext();
            length++;
        }
        return length;
    }

    @Override
    public String toString() {
        StringJoiner sb = new StringJoiner(",");
        LinkedListNode<T> current = this;
        while (current != null) {
            sb.add(current.getValue().toString());
            current = current.getNext();
        }
        return sb.toString();
    }

    public static <T> LinkedListNode<T> initializeLinkedList(Collection<T> collection) {
        LinkedListNode<T> head = null;
        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = null;
        for (T value : collection) {
            if (head == null) {
                head = new LinkedListNode<>(value);
                current = head;
            } else {
                current = new LinkedListNode<>(value);
                previous.setNext(current);
            }

            previous = current;
        }
        return head;
    }

}
