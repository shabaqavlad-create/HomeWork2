package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class HomeWork2Test {

    @BeforeEach
    void beforeTest() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void afterTest() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    void isEvenTest() {
        int number = (int) (Math.random() * 100) + 1;

        boolean actual = HomeWork2.isEven(number);
        boolean expected = number % 2 == 0;

        System.out.println("Number: " + number);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(20)
    void checkAccessTest() {
        int age = (int) (Math.random() * 100);

        String actual = HomeWork2.checkAccess(age);
        String expected = age > 18 ? "Allowed" : "Denied";

        System.out.println("Age: " + age);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    static Integer[] randomScores() {
        Integer[] scores = new Integer[5];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = (int) (Math.random() * 101);
        }

        return scores;
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void getGradeTest(int score) {

        String actual = HomeWork2.getGrade(score);
        String expected;

        if (score >= 0 && score <= 20) {
            expected = "E";
        }
        else if (score >= 21 && score <= 40) {
            expected = "D";
        }
else if (score >= 41 && score <= 60) {
            expected = "C";
        }
else if (score >= 61 && score <= 80) {
            expected = "B";
        }
else {
            expected = "A";
        }

        System.out.println("Score: " + score);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

    }

    @Test
    void isPositiveTest() {
        int number = (int) (Math.random() * 201) - 100;

        boolean actual = HomeWork2.isPositive(number);
        boolean expected = number >= 0 ? true : false;

        System.out.println("Number: " + number);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

}
