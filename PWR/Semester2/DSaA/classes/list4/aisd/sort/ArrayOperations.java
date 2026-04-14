package aisd.sort;

public class ArrayOperations {
    public static void swap(int[] arr, int idx1, int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void reverseSubarray(int[] arr, int l, int r){
        for(int i = l; i < r/2 + 1; i++){
            swap(arr, i, r - i + l);
        }
    }
}
