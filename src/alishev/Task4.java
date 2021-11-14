package alishev;

// алгоритм двоичного поиска
public class Task4 {
    public static void main(String[] args) {
        int[] arr = {-7, -5, -3, -1, 1, 2, 4, 5, 8, 9, 11, 85, 90, 200};
        int low = 0;
        int high = arr.length - 1;
        int key = -3;

        System.out.println(recursiveBinarySearch(arr, low, high, key));

    }

    // указатель low соответствует началу массива и в дальнейшем подмассива
    // указатель high соответствует концу массива и в дальнейшем подмассива
    // указатель middle соответствует середине массива и в дальнейшем подмассива

    public static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length -1;

        // пока конец массива не будет совпадать с его началом
        // (это будет соответствовать - успешному или нет - окончанию двоичного поиска)
        while(low <= high) {
            int middle = low + (high - low) / 2;

            if(key < a[middle]) {           // если число меньше среднего
                high = middle - 1;          // отсекаем правую половину
            } else if(key > a[middle]) {    // если число больше среднего
                low = middle + 1;           // отсекаем левую половину
            } else {
                return middle;
            }
        }
        return -1;
    }

    // рекурсивная функция будет вызываться до тех пор, пока искомое число не станет middle (либо его не будет)
    public static int recursiveBinarySearch(int[] arr, int low, int high, int key) {
        int middle = low + (high - low) / 2;

            if (key < arr[middle]) {
                high = middle - 1;
                return recursiveBinarySearch(arr, low, high, key);
            } else if (key > arr[middle]) {
                low = middle + 1;
                return recursiveBinarySearch(arr, low, high, key);
            } else {
                if(arr[middle] == key)
                    return middle;
            }

        return -1;
    }

    /*
    каждый вызов - отдельный подмассив

    передаем позиции low и high
    {
    вычситываем middle
    если число меньше middle, высчитываем новые low и high, вызываем recursiveBinarySearch
    если число больше middle, высчитываем новые low и high, вызываем recursiveBinarySearch
    если число равно middle, возвращаем индекс

    вернуть recursiveBinarySearch для подмассива
    }
    если числа нет, вернуть -1
     */
}
