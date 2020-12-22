package com.java.stream.menu;

class Dish {
    private final String name;
    private final boolean vegiterian;
    private final int calories;
    private final Type type;

    Dish(String name, boolean vegiterian, int calories, Type type) {
        this.name = name;
        this.vegiterian = vegiterian;
        this.calories = calories;
        this.type = type;
    }

    String getName() {
        return name;
    }

    boolean isVegiterian() {
        return vegiterian;
    }

    int getCalories() {
        return calories;
    }

    Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
