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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Tag;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("webinar")
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

    @RepeatedTest(10)
    void isEvenTest() {
        int number = 4;

        boolean actual = HomeWork2.isEven(number);
        boolean expected = true;
        assertEquals(
                expected,
                actual,
                "Ошибка проверки isEven для number = " + number
        );
    }

    @RepeatedTest(20)
    void checkAccessTest() {
        int age = 19;

        String actual = HomeWork2.checkAccess(age);
        String expected = "Allowed";

        assertEquals(
                expected,
                actual,
                "Ошибка проверки checkAccessTest для age = " + age
        );
    }

    static Arguments[] gradeData() {
        return new Arguments[] {
                Arguments.of(0, "E"),
                Arguments.of(20, "E"),
                Arguments.of(21, "D"),
                Arguments.of(40, "D"),
                Arguments.of(41, "C"),
                Arguments.of(60, "C"),
                Arguments.of(61, "B"),
                Arguments.of(80, "B"),
                Arguments.of(81, "A"),
                Arguments.of(100, "A")
        };
    }

    @ParameterizedTest
    @MethodSource("gradeData")
    void getGradeTest(int score, String expected) {

        String actual = HomeWork2.getGrade(score);

        assertEquals(
                expected,
                actual,
                "Ошибка проверки getGrade для score = " + score
        );
    }

    @RepeatedTest(10)
    void isPositiveTest() {
        int number = 0;

        boolean actual = HomeWork2.isPositive(number);
        boolean expected = true;

        assertEquals(
                expected,
                actual,
                "Ошибка проверки isPositive для number = " + number);
    }

    @RepeatedTest(10)
    void findMaxTest() {
        int [] numbers = {5, -10, 42, 7, 0};

        int actual = HomeWork2.findMax(numbers);
        int expected = 42;
        assertEquals(
                expected,
                actual,
                "Ошибка проверки findMax для numbers = " + Arrays.toString(numbers));
    }

    @RepeatedTest(10)
    void reverseTest() {
        String[] words = {"One", "Two", "Three"};

        String[] actual = HomeWork2.reverse(words);
        String[] expected = {"Three", "Two", "One"};

        assertArrayEquals(
                expected,
                actual,
                "Ошибка проверки reverse для words = " + Arrays.toString(words)
        );
    }

    @RepeatedTest(10)
    void blastOffTest() {
        int start = 5;

        String actual = HomeWork2.blastOff(start);
        String expected = "5 4 3 2 1 Поехали!";

        assertEquals(
                expected,
                actual,
                "Ошибка проверки blastOff для start = " + start);
    }

    @RepeatedTest(10)
    void sumToNTest() {
        int n = 5;

        int actual = HomeWork2.sumToN(n);
        int expected = 15;

        assertEquals(
                expected,
                actual,
                "Ошибка проверки sumToN для n = " + n);
    }

    @RepeatedTest(10)
    void hasBugTest() {
        String[] messages = {"Info", "Warning", "Error", "Bug", "Test"};

        boolean actual = HomeWork2.hasBug(messages);
        boolean expected = true;
        assertEquals(
                expected,
                actual,
                "Ошибка проверки hasBug для messages = " + Arrays.toString(messages));
    }


    static Arguments[] evenRangeData() {
        return new Arguments[] {
                Arguments.of(2, 5, "2 4"),
                Arguments.of(1, 6, "2 4 6"),
                Arguments.of(0, 0, "0"),
                Arguments.of(1, 1, ""),
                Arguments.of(2, 2, "2"),
                Arguments.of(3, 7, "4 6"),
                Arguments.of(10, 15, "10 12 14"),
                Arguments.of(20, 21, "20"),
                Arguments.of(5, 10, "6 8 10"),
                Arguments.of(8, 12, "8 10 12")
        };
    }

    @ParameterizedTest
    @MethodSource("evenRangeData")
    void getEvenInRangeTest(int start, int end, String expected) {

        String actual = HomeWork2.getEvenInRange(start, end);
        assertEquals(
                expected,
                actual,
                "Ошибка проверки getEvenInRange для start = "
                        + start + ", end = " + end
        );
    }

    static Arguments[] averageData() {
        return new Arguments[] {
                Arguments.of(List.of(1, 2, 3), 2.0),
                Arguments.of(List.of(2, 3), 2.5),
                Arguments.of(List.of(10, 20, 30), 20.0),
                Arguments.of(List.of(5, 5, 5, 5), 5.0),
                Arguments.of(List.of(1, 2, 3, 4), 2.5),
                Arguments.of(List.of(100), 100.0),
                Arguments.of(List.of(0, 10), 5.0),
                Arguments.of(List.of(-5, 5), 0.0),
                Arguments.of(List.of(10, 15, 20, 25), 17.5),
                Arguments.of(List.of(3, 6, 9, 12, 15), 9.0)
        };
    }

    @ParameterizedTest
    @MethodSource("averageData")
    void calcAverageTest(List<Integer> list, double expected) {

        double actual = HomeWork2.calcAverage(list);

        assertEquals(
                expected,
                actual,
                0.0001,
                "Ошибка проверки calcAverage для list = " + list
        );
    }

    static Arguments[] removeNameData() {
        return new Arguments[] {
                Arguments.of(
                        List.of("Anna", "Bob", "Anna", "Mike"),
                        "Anna",
                        List.of("Bob", "Mike")
                ),
                Arguments.of(
                        List.of("Bob", "Bob", "Mike"),
                        "Bob",
                        List.of("Mike")
                ),
                Arguments.of(
                        List.of("Kate", "Alex"),
                        "Mike",
                        List.of("Kate", "Alex")
                ),
                Arguments.of(
                        List.of("Alex"),
                        "Alex",
                        List.of()
                ),
                Arguments.of(
                        List.of("Anna", "Bob"),
                        "Mike",
                        List.of("Anna", "Bob")
                ),
                Arguments.of(
                        List.of("Mike", "Mike", "Mike"),
                        "Mike",
                        List.of()
                ),
                Arguments.of(
                        List.of("Anna", "Kate", "Alex"),
                        "Kate",
                        List.of("Anna", "Alex")
                ),
                Arguments.of(
                        List.of("Bob", "Alex", "Bob"),
                        "Alex",
                        List.of("Bob", "Bob")
                ),
                Arguments.of(
                        List.of("Anna", "Mike", "Kate", "Mike"),
                        "Mike",
                        List.of("Anna", "Kate")
                ),
                Arguments.of(
                        List.of("Alex", "Bob", "Kate"),
                        "Anna",
                        List.of("Alex", "Bob", "Kate")
                )
        };
    }


    @ParameterizedTest
    @MethodSource("removeNameData")
    void removeSpecificNameTest(
            List<String> list,
            String nameToRemove,
            List<String> expected
    )
    {
        List<String> actual =
                HomeWork2.removeSpecificName(list, nameToRemove);

        assertEquals(
                expected,
                actual,
                "Ошибка проверки removeSpecificName для list = "
                        + list + ", nameToRemove = " + nameToRemove
        );
    }
    }