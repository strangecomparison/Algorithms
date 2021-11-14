package alishev;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        int fibN = 100;
        long[] mem = new long[fibN + 1];        // массив для мемоизации параметра, с которым вызывается функция
        Arrays.fill(mem, -1);               // заполняем массив значениями -1
        System.out.println(fibonacciRecursive(fibN, mem));

        System.out.println(fibonacciArray(fibN));
    }

    //fibN = число фибоначчи; количество итераций, необходимых для его вычисления

    private static long fibonacciRecursive(int fibN, long[] mem) {
        if(mem[fibN] != -1)             // если элемент массива с индексом fibN не раве -1 значит, мы уже вызывали метод с таким
            return mem[fibN];           // аргументом, и мы выходим из этого рекурсивного метода, иначе продолжаем

        if(fibN <= 1)
            return fibN;                // есоли аргумент 1, то возвращаем 1, если 0, то - ноль

        long result = fibonacciRecursive(fibN - 1, mem) + fibonacciRecursive(fibN - 2, mem);   // вычисляем результат рекурсивного вызова
        mem[fibN] = result;     // помещаем в массив результат функции с аргументом fibN

        return result;
    }

    private static long fibonacciArray(int fibN) {
         long[] array = new long[fibN + 1];

         array[0] = 0;  // 2 первых элемента фибоначчи всегда равны 0 и 1
         array[1] = 1;

         // далее мы заполняем массив элементами по принципу "каждый следующий равен сумме 2 предыдущих"

         for(int i = 2; i <= fibN; i++) {
             array[i] = array[i-1] + array[i-2];
         }

         return array[fibN];
    }

}
