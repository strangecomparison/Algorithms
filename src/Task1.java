public class Task1 {

    // рекурсивное нахождение суммы элементов массива
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(recursiveSum(arr, arr.length));
    }

    public static int recursiveSum(int[] arr, int arrLength) {
        if(arrLength <= 0) {
            return 0;
        } else if(arr.length == 0) {
            return -1;
        }

        return arr[arrLength - 1] + recursiveSum(arr, arrLength - 1);
    }
}
