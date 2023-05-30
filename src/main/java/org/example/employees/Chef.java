package org.example.employees;

public interface Chef {

    String favoriteFood = "Hamburger";

    default void cook(String food) {
        System.out.println("I am now cooking " + food);
    }

    default String cleanUp() {
        return "I am done cleaning up.";
    }

    default String getFavoriteFood() {
        return favoriteFood;
    }
}
