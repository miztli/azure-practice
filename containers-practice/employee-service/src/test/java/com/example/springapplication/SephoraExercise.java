package com.example.springapplication;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SephoraExercise {

    @Test
    public void arrayDuplication() {
        final Integer[] numbers = { 2, 2, 5, 6, 78, 100, 6, 8 };
        final Set<Integer> numbersSet = new HashSet<>(Arrays.asList(numbers));

        System.out.println("result: " + numbersSet);
    }
}
