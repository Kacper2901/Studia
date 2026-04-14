import static aisd.sort.ArrayOperations.*;

public class PancakeSorting {
    void pancakeSorting(int[] arr){
        int right = arr.length;
        if(arr.length <= 1) return;
        int maxIdx = 0;
        for(int j = 0; j < arr.length; j++) {
            for (int i = 1; i < right; i++) {
                if (arr[maxIdx] < arr[i]) maxIdx = i;
            }
        }

    }


    void main(){
        int[] arr = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};
        reverseSubarray(arr, 1, 4);
        printArray(arr);
    }
}
