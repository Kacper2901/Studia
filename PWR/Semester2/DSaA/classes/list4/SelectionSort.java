import static aisd.sort.ArrayOperations.*;

public class SelectionSort {
    public void selectionSort(int[] arr){
        printArray(arr);
        for(int i = arr.length - 1; i > 0; i--){
            int currMinValIdx = i;
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] < arr[currMinValIdx]) currMinValIdx = j;
            }
            swap(arr,currMinValIdx,i);
            printArray(arr);
        }
    }

    void main(){
        int[] arr = {76, 71, 5, 57, 12, 50, 20, 3, 20, 55, 62, 53};
        selectionSort(arr);
    }
}
