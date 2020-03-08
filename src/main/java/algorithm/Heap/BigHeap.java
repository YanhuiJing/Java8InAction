package algorithm.Heap;

import java.util.Arrays;

/**
 * 大顶堆
 *
 * @author gavin
 * @createDate 2020/3/8
 * https://github.com/kkzfl22/datastruct/tree/master/src/main/java/com/liujun/datastruct/base/datastruct/heap/solution
 */
public class BigHeap {

    public static void main(String[] args) {

        int[] datas = {1,3,5,7,6,9};

        sort(datas);

        System.out.println(Arrays.toString(datas));

    }

    private int[] datas;
    private int capacity;
    private int count;

    public BigHeap(int capacity){

        this.datas = new int[capacity+1];
        this.capacity = capacity;
        this.count = 0;

    }

    public void inset(int data){

        if(count>capacity){return;}

        ++count;
        datas[count] = data;

        int index = count;
        while(index/2>0 && datas[index] > datas[index/2]){
            swap(datas,index,index/2);
            index = index / 2;
        }

    }


    public void removeMax(){

        if(count == 0) {return;}

        datas[1] = datas[count];
        --count;

        heapify(datas,count,1);
    }

    //通过堆对指定元素进行排序
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 1、建堆
        buildHeap(arr);

        // 2、排序
        int k = arr.length - 1;
        while (k > 0) {
            // 将堆顶元素（最大）与最后一个元素交换位置
            swap(arr, 0, k);
            // 将剩下元素重新堆化，堆顶元素变成最大元素
            heapify(arr, --k, 0);
        }

    }


    //对指定数组进行堆化操作,从length/2父节点开始对数据依次进行堆化操作
    //堆化完成,原数组就是堆化结果
    private static void buildHeap(int[] arr) {
        // (arr.length - 1) / 2 为最后一个叶子节点的父节点
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    public static void heapify(int[] datas,int num,int index){

        while(true){

            int maxPos = index;

            if(index*2<=num && datas[index*2]>datas[maxPos]){maxPos = index*2;}
            if(index*2+1<=num && datas[index*2+1]>datas[maxPos]){maxPos = index*2+1;}

            if(maxPos == index){break;}

            swap(datas,maxPos,index);

            index = maxPos;
        }

    }

    public static void swap(int[] datas,int left,int right){

        int temp = datas[left];
        datas[left] = datas[right];
        datas[right] = temp;

    }


}
