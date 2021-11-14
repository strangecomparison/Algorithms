
// рекурсивная функция для поиска суммы членов массива

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        System.out.println(arraySum(arr));
    }

    public static int arraySum(int[] array) {
        if(array.length == 0)
            return 0;

        if(array.length == 1)
            return array[0];

        return array[0] + arraySum(Arrays.copyOfRange(array, 1, array.length));
    }

}


/*
определяем базовый случай:
если массив пустой, то результат равен 0
если в массиве 1 элемент, то результат равен этому элементу
на каждой итерации рекурсии берется первый элемент массива и
складывается с суммой остальных элементов

 */