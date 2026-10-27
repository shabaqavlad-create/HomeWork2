package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionTest {

    @Test
    void isPositiveAssertionTest() {
        int number = -5;

        boolean actual = HomeWork2.isPositive(number);
        boolean expected = false;

        assertEquals(
                expected,
                actual,
                "Ошибка проверки isPositive для number = " + number
        );
    }

    @Test
    void removeSpecificNameAssertionTest() {
        List<String> list = List.of("Anna", "Bob", "Anna", "Mike");
        String nameToRemove = "Anna";

        List<String> actual = HomeWork2.removeSpecificName(list, nameToRemove);
        List<String> expected = List.of("Bob", "Mike");

        assertEquals(
                expected,
                actual,
                "Ошибка removeSpecificName при удалении имени: " + nameToRemove
        );
    }

    @Test
    void getGradeBoundaryAssertionTest() {
        int score = 21;

        String actual = HomeWork2.getGrade(score);
        String expected = "D";

        assertEquals(
                expected,
                actual,
                "Ошибка проверки getGrade для score = " + score
        );
    }

    @Test
    void failedAssertionTest() {
        int n = 5;

        int actual = HomeWork2.sumToN(n);
        int expected = 14; // падаем на 14, тк должно 15

        assertEquals(
                expected,
                actual,
                "Специальная проверка падения assertion для n = " + n
        );
    }

}