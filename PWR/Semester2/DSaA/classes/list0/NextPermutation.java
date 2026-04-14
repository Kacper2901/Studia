import java.util.*;

public class NextPermutation {

    public static void swap(int[] arr, int aIdx, int bIdx){
        int temp = arr[aIdx];
        arr[aIdx] = arr[bIdx];
        arr[bIdx] = temp;
    }

    public static int getIndexBreakingIncrease(int[] arr){
        int arrLength = arr.length;
        int currIdx = arrLength - 1;
        while(arr[currIdx] < arr[currIdx - 1]){
            currIdx--;
            if(currIdx - 1 < 0) return -1;
        }
        return currIdx;
    }

    public static int getFirstGreaterValueIdx(int[] arr, int val){
        int arrLength = arr.length;
        int currIdx = arrLength - 1;

        int swapIdx = currIdx - 1;
        int swapVal = arr[swapIdx];
        int i = swapIdx + 1;
        int minGreaterValIdx = i;
        int minGreaterVal = arr[i];

        while (i + 1 < arrLength){
            if(arr[i + 1] < minGreaterVal && arr[i + 1] > swapVal){
                minGreaterVal = arr[i + 1];
                minGreaterValIdx = i + 1;
            }
            i++;
        }
        return minGreaterValIdx;
    }

    public static void reverseSubArray(int[] arr, int idx){
        int r = arr.length - 1;
        int l = idx;

        while (l < r){
            swap(arr, l, r);
            l++;
            r--;
        }
    }

    public static boolean nextPermutation(int[] arr){
        int arrLength = arr.length;
        int currIdx = arrLength - 1;
        if(arrLength < 2) return false;

        int idxBreakingIncreasment = getIndexBreakingIncrease(arr);
        if(idxBreakingIncreasment == -1) return false;

        int firstGreaterNumIdx = getFirstGreaterValueIdx(arr);




        swap(arr, idxBreakingIncreasment, firstGreaterNumIdx);

        reverseSubArray(arr, firstGreaterNumIdx + 1);


        return true;
    }
}
