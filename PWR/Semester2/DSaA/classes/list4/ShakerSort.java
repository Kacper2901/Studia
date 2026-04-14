import static aisd.sort.ArrayOperations.*;

public class ShakerSort {
    public void shakerSort(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            for(int i = left; i < right; i++){
                if(arr[i] > arr[i+1]){
                    swap(arr, i, i+1);
                }
            }
            right--;
            for (int i = right; i > left; i--){
                if(arr[i] < arr[i-1]) {
                    swap(arr, i, i-1);
                }
            }
            left++;
            printArray(arr);
        }
        printArray(arr);
    }

    public void shakerSortOptimized(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        boolean wasSwap = false;
        printArray(arr);
        while(left < right){
            for(int i = left; i < right; i++){
                int tempVal = arr[i];
                while(i < right && tempVal > arr[i+1]){
                    arr[i] = arr[i+1];
                    if(!wasSwap) wasSwap = true;
                    i++;
                }
                arr[i] = tempVal;
            }
            if(!wasSwap) return;
            wasSwap = false;
            right--;
            for (int i = right; i > left; i--){
                int tempVal = arr[i];
                while(i > left && tempVal < arr[i-1]) {
                    arr[i] = arr[i-1];
                    if(!wasSwap) wasSwap = true;
                    i--;
                }
                arr[i] = tempVal;
            }
            left++;
            printArray(arr);
            if(!wasSwap) return;
            wasSwap = false;
        }
        printArray(arr);
        }


    void main(){
        int[] arr = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};
        shakerSortOptimized(arr);
        System.out.println();
        System.out.println();
    }
}
