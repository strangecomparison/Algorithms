
// быстрая сортировка без учета повторяющихся чисел

import java.util.Arrays;

public class QuickSort {


    public static void main(String[] args) {
        int[] massiv = {7, 6, 5, 4, 3, 2, 1};

        System.out.println(Arrays.toString(quicksort(massiv)));
    }


    public static int[] quicksort(int[] arr) {

        if(arr.length == 1) {       // если массив длиной 1, то возвращаем его
            return arr;
        } else if(arr.length == 0) {       // если массив длиной 0, то возвращаем null
            return null;
        } else if(arr.length == 2) {    // если массив длиной 2, то

            if(arr[0] > arr[1]) {   // если предыдущий элемент больше первого
                int temp = arr[0];      // первый меньше второго, и их надо поменять местами
                arr[0] = arr[1];
                arr[1] = temp;
            }

            return arr;             // возвращаем отсортированный массив

        } else {                     // если длина массива больше двух
            int pivot = arr[0];     // выбираем произвольный опорный элемент

            int[] less = Arrays.stream(arr)                 // создаем массив элементов, которые меньше опорного
                    .filter(e -> e < pivot)
                    .toArray();

            int[] greater = Arrays.stream(arr)              // создаем массив элементов, которые больше опорного
                    .filter(e -> e > pivot)
                    .toArray();


            return gather(quicksort(less), pivot, quicksort(greater));     // итог сортировки - объединяем части массива и
                                                                        // опорный элемент
        }
    }

    // метод для объединения 2 массивов и еще 1 числа
    public static int[] gather(int[] less, int pivot, int[] greater) {

        int[] result;

        if(less == null) {                          // если левая часть пустая, складываем опорную точку и правую часть
            result = new int[1 + greater.length];
            result[0] = pivot;
            System.arraycopy(greater, 0, result, 1, greater.length);
        } else if(greater == null) {                // если правая часть пустая, складываем опорную точку и левую часть
            result = new int[less.length + 1];
            System.arraycopy(less, 0, result, 0, less.length);
            result[result.length - 1] = pivot;
        } else {                                        // если обе части присутствуют, то складываем все в один массив
            result = new int[less.length + 1 + greater.length];
            System.arraycopy(less, 0, result, 0, less.length);
            result[less.length] = pivot;
            System.arraycopy(greater, 0, result, less.length + 1, greater.length);
        }
        return result;
    }


}
