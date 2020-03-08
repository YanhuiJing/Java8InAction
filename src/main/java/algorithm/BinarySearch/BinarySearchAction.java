package algorithm.BinarySearch;

/**
 * @author gavin
 * @createDate 2020/3/7
 * 二分查找时间复杂度O(logn)
 */
public class BinarySearchAction {

    public static void main(String[] args) {

        int[] nums = {1,3,3,3,5,7,9};
//        System.out.println(binarySearch(nums,9));
//        System.out.println(binarySearchFistEqual(nums,3));
//        System.out.println(binarySearchLastEqual(nums,3));
//        System.out.println(binarySearchFirstGreater(nums,6));
        System.out.println(binarySearchLastLess(nums,10));


    }

    //二分常规查找等值
    public static int binarySearch(int[] array,int value){
        int start = 0;
        int end = array.length-1;

        while(start <= end){
            // 使用位移计算中间值
            int mid = start +((end -start) >> 1);

            if(array[mid] == value){
                return mid;
            }else if(array[mid] > value){
                end = mid-1;
            }else {
                start = mid+1;
            }
        }

        return -1;
    }

    //二分查找第一个等值
    public static int binarySearchFistEqual(int[] array,int value){
        int start = 0;
        int end = array.length-1;

        while(start <= end){

            int mid = start + ((end - start) >>1);

            if(array[mid] == value){
                if(mid ==0 || array[mid-1] < value){
                    return mid;
                }else {
                    end = mid - 1;
                }
            }else if(array[mid] > value){
                end = mid -1;
            }else {
                start = mid+1;
            }
        }

        return -1;
    }

    //二分查找最后一个等值
    public static int binarySearchLastEqual(int[] array,int value){
        int start = 0;
        int end = array.length-1;

        while(start <= end){

            int mid = start + ((end - start) >>1);

            if(array[mid] == value){
                if(mid ==array.length-1 || array[mid+1] > value){
                    return mid;
                }else {
                    start = mid + 1;
                }
            }else if(array[mid] > value){
                end = mid -1;
            }else {
                start = mid+1;
            }
        }

        return -1;
    }

    //二分查找第一个大于的值
    public static int binarySearchFirstGreater(int[] array,int value){
        int start = 0;
        int end = array.length-1;

        while(start <= end){

            int mid = start + ((end - start) >>1);

            if(array[mid] > value){
                if(mid == 0 || array[mid-1]<value){
                    return mid;
                }
                end = mid -1;
            }else {
                start = mid+1;
            }
        }

        return -1;
    }

    //二分查找最后一个小于的值
    public static int binarySearchLastLess(int[] array,int value){
        int start = 0;
        int end = array.length-1;

        while(start <= end){

            int mid = start + ((end - start) >>1);

            if(array[mid] > value){
                end = mid -1;
            }else {
                if(mid == array.length-1 || array[mid+1]>value){
                    return mid;
                }
                start = mid+1;
            }
        }

        return -1;
    }



}
