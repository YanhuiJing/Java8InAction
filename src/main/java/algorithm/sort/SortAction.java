package algorithm.sort;

import java.util.Arrays;

/**
 * @author gavin
 * @createDate 2020/3/7
 */
public class SortAction {

    public static void main(String[] args) {

        int[] nums = {2,4,7,9,6,3,8};

//        bubbleSortAction(nums);

//        insertSortAction(nums);

//        quickSort(nums,0,6);
//
//        System.out.println(Arrays.toString(nums));
    }


    //O(n2)
    public static void bubbleSortAction(int[] nums){

        int size = nums.length;
        boolean flag = false;

        for(int i=0;i<size;++i){
            for(int j=0;j<size-1-i;++j){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag) {break;}
        }

        System.out.println(Arrays.toString(nums));
    }

    //O(n2)
    public static void insertSortAction(int[] nums){

        int size = nums.length;

        for(int i=0;i<size;i++){
            for(int j=i;j<size;j++){
                if(nums[j]<nums[i]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(nums));

    }

    //O(nlogn)
    public static void quickSort(int[] nums,int start,int end){

        if(start>=end) {return;}

        int mid = partition(nums, start, end);

        quickSort(nums,start,mid-1);
        quickSort(nums,mid+1,end);

    }

    private static int partition(int[] nums, int start, int end) {

        int data = nums[end];

        int i = start;

        for (int j = start;j<end;j++){
            //如果当前值小于目标值,偏移量加1,同时将当前值交换到偏移坐标上
            if(nums[j] < data){
                if(i != j){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                i++;
            }
        }

        int temVal = nums[i];
        nums[i] = data;
        nums[end] = temVal;

        return i;
    }



}
