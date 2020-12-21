package com.java.stream_test;

class Dish {
    private final String name;
    private final boolean vegiterian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegiterian, int calories, Type type) {
        this.name = name;
        this.vegiterian = vegiterian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegiterian() {
        return vegiterian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
