package com.practice.coding.crackingthecodinginterview.chapterthree;

public class Animal {

    protected Animal.Type type;
    protected int order;

    public Animal(Animal.Type type, int order) {
        this.type = type;
        this.order = order;
    }

    public enum Type {
        CAT, DOG
    }
}
