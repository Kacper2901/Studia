import static aisd.sort.ArrayOperations.*;

public class BubbleSort {
    public void bubbleSort(int[] arr){
        printArray(arr);
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = i; j > 0; j--){
                if(arr[j] > arr[j - 1]) swap(arr, j-1, j);
            }
            printArray(arr);
        }
    }

    void main(){
        int[] arr = {76, 20, 5, 57, 12, 50, 20, 93, 44, 55, 62, 3};
        bubbleSort(arr);
    }
}
