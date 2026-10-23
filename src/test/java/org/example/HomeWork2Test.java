package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

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

    @Test
    void findMaxTest() {
        int [] numbers = new int[5];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 201) - 100;
        }

        int actual = HomeWork2.findMax(numbers);
        int expected = numbers[0];
        for (int num : numbers) {
            if (num > expected) {
                expected = num;
            }
        }

        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void reverseTest() {
        String[] words = new String[5];

        for (int i = 0; i < words.length; i++) {
            words[i] = String.valueOf((int) (Math.random() * 100));
        }

        String[] actual = HomeWork2.reverse(words);
        String[] expected = new String[words.length];

        int rev = 0;
        for (int i = words.length -1; i >= 0; i--) {
            expected[rev] = words[i];
            rev++ ;
        }

        System.out.println("Original: " + Arrays.toString(words));
        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        if (Arrays.equals(actual, expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(5)
    void blastOffTest() {
        int start = (int) (Math.random() * 10) + 1;

        String actual = HomeWork2.blastOff(start);
        String expected = "";

        for (int i = start; i >= 1; i--) {
            expected = expected + i + " ";
        }

        expected = expected + "Поехали!";

        System.out.println("Start: " + start);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(5)
    void sumToNTest() {
        int n = (int) (Math.random() * 100) + 1;

        int actual = HomeWork2.sumToN(n);

        int expected = 0;
        for (int i = 1; i <= n; i++) {
            expected = expected + i;
        }
        System.out.println("Start: " + n);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(5)
    void hasBugTest() {
        String[] variants = {"Info", "Warning", "Error", "Bug", "Test"};
        String[] messages = new String[5];

        for (int i = 0; i < messages.length; i++) {
            int randomIndex = (int) (Math.random() * variants.length);
            messages[i] = variants[randomIndex];
        }

        boolean actual = HomeWork2.hasBug(messages);
        boolean expected = false;
        for (String message : messages) {
            if (message.equalsIgnoreCase("Bug")) {
                expected = true;
            }
        }

        System.out.println("Messages: " + Arrays.toString(messages));
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual == expected) {
            System.out.println("TEST PASSED");
        }  else {
            System.out.println("TEST FAILED");
        }

    }


    static Arguments[] randomRanges() {
        Arguments[] ranges = new Arguments[5];

        for (int i = 0; i < ranges.length; i++) {
            int start = (int) (Math.random() * 50);
            int end = start + (int) (Math.random() * 50);

            ranges[i] = Arguments.of(start, end);
        }

        return ranges;
    }

    @ParameterizedTest
    @MethodSource("randomRanges")
    void getEvenInRangeTest(int start, int end) {

        String actual = HomeWork2.getEvenInRange(start, end);

        String expected = "";

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (!expected.isEmpty()) {
                    expected = expected + " ";
                }
                expected = expected + i;
            }
        }


        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    static List<List<Integer>> randomLists() {
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            List<Integer> numbers = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                numbers.add((int) (Math.random() * 100) + 1);
            }

            lists.add(numbers);
        }

        return lists;
    }

    @ParameterizedTest
    @MethodSource("randomLists")
    void calcAverageTest(List<Integer> list) {

        double actual = HomeWork2.calcAverage(list);

        int sum = 0;
        for (int number : list) {
            sum = number + sum;
        }

        double expected = (double) sum / list.size();

        System.out.println("List: " + list);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
        if (actual == expected) {
            System.out.println("TEST PASSED");
        }  else {
            System.out.println("TEST FAILED");
        }

    }

    static Arguments[] randomNames() {
        Arguments[] data = new Arguments[5];

        String[] variants = {"Anna", "Bob", "Mike", "Kate", "Alex"};

        for (int i = 0; i < data.length; i++) {
            List<String> names = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                int randomIndex = (int) (Math.random() * variants.length);
                names.add(variants[randomIndex]);
            }

            int removeIndex = (int) (Math.random() * variants.length);
            String nameToRemove = variants[removeIndex];

            data[i] = Arguments.of(names, nameToRemove);
        }

        return data;
    }


        @ParameterizedTest
        @MethodSource("randomNames")
        void removeSpecificNameTest(List<String> list, String nameToRemove) {

            List<String> actual = HomeWork2.removeSpecificName(list, nameToRemove);

            List<String> expected = new ArrayList<>();
            for (String name : list){
                if (!name.equals(nameToRemove)) {
                    expected.add(name);
                }
            }

            System.out.println("Original: " + list);
            System.out.println("Remove: " + nameToRemove);
            System.out.println("Actual: " + actual);
            System.out.println("Expected: " + expected);
            if (actual.equals(expected)) {
                System.out.println("TEST PASSED");
            } else {
                System.out.println("TEST FAILED");
            }
        }
    }