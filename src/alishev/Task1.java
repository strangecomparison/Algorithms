package alishev;

/*
дан произвольный набор цифр, составить из него наибольшее число
 */

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        int[] digits = { 1, 3, 7, 9, 9, 5};
        String s = maxNumberFromDigits(digits);
        System.out.println(s);
    }

    private static String maxNumberFromDigits(int[] digits) {
        Arrays.sort(digits);    // сортируем массив
        String result = "";     // строка для конечного результата
        for(int i = digits.length - 1; i >= 0; i--) {       // в цикле проходим с последнего элемента массива до первого
            result += digits[i];    // в каждой итерации конкатенируем к конечному результату новую цифру
        }
        return result;
    }
}
