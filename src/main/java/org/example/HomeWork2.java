package org.example;

import java.util.ArrayList;
import java.util.List;

public class HomeWork2 {
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    //Задача 2: разработать метод с сигнатурой public static String checkAccess(int age).
    // Метод возвращает Allowed, если число строго больше 18, и Denied — если меньше.

    public static String checkAccess(int age) {
        if (age > 18) {
            return "Allowed";
        }
        else {
            return "Denied";
        }
    }

    //Задача 3: разработать метод с сигнатурой public static boolean isPositive(int n).
    // Метод должен возвращать true, если переданное число больше или равно нулю,
    // и false, если переданное число меньше нуля.
    // Проверка внутри метода должна происходить с помощью тернарного оператора.

    public static boolean isPositive(int n) {
        return n >= 0 ? true : false;
    }

    //Задача 4: разработать метод с сигнатурой public static String getGrade(int score).
    // Метод возвращает строку, соответствующую строгому вхождению в границы:
    //
    //0–20: E;
    //21–40: D;
    //41–60: C;
    //61–80: B;
    //81–100: A.
    //Если переданное число не входит в границы — вернуть строку Error.

    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) {
            return "E";
        }
        else if (score >= 21 && score <= 40) {
            return "D";
        }
        else if (score >= 41 && score <= 60) {
            return "C";
        }
        else if (score >= 61 && score <= 80) {
            return "B";
        }
        else if (score >= 81 && score <= 100) {
            return "A";
        }
        else {
            return "Error";
        }
    }

    //Задача 5: разработать метод с сигнатурой public static String blastOff(int start).
    // Метод принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1
    // и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).

    public static String blastOff(int start) {
        String result = "";
        for (int i = start; i >= 1; i--) {
            result = result + i + " ";
        }
        result = result + "Поехали!";
        return result;
    }

    //Задача 6: разработать метод с сигнатурой publiс static int sumToN(int n).
    // Метод возвращает сумму всех целых чисел от 1 до n.

    public static int sumToN(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++ ) {
            result = result + i;
        }
        return result;
    }

    //Задача 7: разработать метод с сигнатурой publiс static boolean hasBug(String[] messages).
    // Метод принимает массив строк и возвращает true, если хотя бы одна строка в массиве равна Bug.
    // Сравнение можно выполнять без учёта регистра.

    public static boolean hasBug(String[] messages) {
        for (String message : messages) {
            if (message.equalsIgnoreCase("Bug")) {
                return true;
            }
        }
        return false;
    }

    //Задача 8: разработать метод с сигнатурой publiс static getEvenInRange(int start, int end).
    // Метод принимает границы диапазона и возвращает строку, состоящую только из чётных чисел внутри этого промежутка (включая границы),
    // разделённых пробелом. Перед первым и после последнего числа пробел не ставится. Например: (2, 5) -> “2 4”

    public static String getEvenInRange(int start, int end) {
        String result = "";
        for (int number = start; number <= end; number++ ){
            if (number % 2 == 0){
                if (!result.isEmpty()){
                    result = result + " ";
                }
                result = result + number;
            }
        }
        return result;
    }

    //Задача 9: разработать метод с сигнатурой publiс static public int findMax(int[] arr).
    // Метод находит и возвращает самое большое число в переданном массиве.
    public static int findMax(int[] arr) {
        int result = arr[0];

        for (int number : arr) {
            if (number > result) {
                result = number ;
            }
        }
        return result;
    }

    //Задача 10: разработать метод с сигнатурой publiс static String[] reverse(String[] arr).
    // Метод возвращает новый массив, в котором элементы исходного массива расположены в обратном порядке.
    // Например, {“One”, “Two”, “Zero”} -> {“Zero”, “Two”, “One}.
    public static String[] reverse(String[] arr) {
        String [] result = new String[arr.length];
        int rev = 0;

        for (int i = arr.length - 1; i >= 0; i--){
            result[rev] = arr[i] ;
            rev++;
        }
        return result ;
    }

    //Задача 11: разработать метод с сигнатурой publiс static calcAverage(List<Integer> list).
    //Метод вычисляет и возвращает среднее арифметическое всех чисел в списке.
    public static double calcAverage(List<Integer> list) {
        int sum = 0;

        for (int number : list) {
            sum = number + sum ;
        }
        double result = (double) sum / list.size();
        return result ;
    }

    //Задача 12: разработать метод с сигнатурой
    // publiс static List<String> removeSpecificName(List<String> list, String nameToRemove).
    // Метод принимает список и имя, которое нужно исключить.
    // Возвращает новый список, не содержащий указанного имени.

    public static List<String> removeSpecificName(List<String> list, String nameToRemove){
        List<String> result = new ArrayList<>();

        for (String name : list) {
            if (!name.equals(nameToRemove)) {
                result.add(name);

            }
        }
        return result ;
    }

}
