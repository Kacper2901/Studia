
import static aisd.sort.ArrayOperations.swap;
import static aisd.sort.ArrayOperations.printArray;

public class InsertionSort{
    public void insertionSort(int[] arr){
        printArray(arr);
        for(int i = arr.length-2; i >= 0; i--){
            int key = arr[i];
            int j = i + 1;
            while (j < arr.length && key < arr[j]){
                swap(arr, j - 1, j);
                j++;
            }
            printArray(arr);
        }
    }

    void main(){
        int[] arr = {76, 71, 5, 57, 12, 50, 20, 3, 20, 55, 62, 53};
        insertionSort(arr);
    }
}
