package algorithm.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author gavin
 * @createDate 2020/3/8
 * PriorityQueue(优先级队列java实现)
 */
public class SmallHeap {

    public static void main(String[] args) {

        int[] array = {1,3,5,2,4,6,8,9};

        sort(array);

        System.out.println(Arrays.toString(array));
    }

    private int[] datas;
    private int capacity;
    private int count;

    public SmallHeap(int capacity){

        this.datas = new int[capacity+1];
        this.capacity = capacity;
        this.count = 0;

    }

    public int getCount() {
        return count;
    }

    public int[] getDatas() {
        return datas;
    }

    public void insert(int data){

        if(count == capacity){return;}
        ++count;
        datas[count] = data;

        int index = count;

        while(index/2>0 && datas[index] <datas[index/2]){

            swap(datas,index,index/2);
            index = index/2;

        }

    }

    public void removeMin(){

        if(count == 0){return;}

        datas[1] = datas[count];
        datas[count] = 0;
        --count;

        heapify(datas,count,1);

    }

    public void top(int data){
        if(count<10){
            insert(data);
        }else if(count == 10 && data > datas[1]){
            datas[1] = data;
            heapify(datas,10,1);
        }
    }

    public static void sort(int [] array){

        if(array.length == 0){return;}

        buildHeap(array);

        int k = array.length-1;
        while(k>0){
            swap(array,0,k);
            heapify(array,--k,0);
        }

    }

    public static void buildHeap(int[] array){

        if(array.length == 0){return;}

        for (int index = (array.length/2);index>=0;index--){
            //从倒数父节点对整体数组依次进行堆化
            heapify(array,array.length-1,index);

        }

    }

    public static void heapify(int datas[], int num, int index){

        while(true){

            int minPos = index;
            if(index*2<=num && datas[index*2] <datas[minPos]){ minPos = index*2;}
            if(index*2+1<=num && datas[index*2+1] <datas[minPos]){ minPos = index*2+1;}

            if(minPos == index) {break;}

            swap(datas,minPos,index);
            index = minPos;

        }

    }

    public static void swap(int[] datas,int left,int right){

        int temp = datas[left];
        datas[left] = datas[right];
        datas[right] = temp;

    }


    @Override
    public String toString() {
        return "SmallHeap{" +
                "datas=" + Arrays.toString(datas) +
                ", capacity=" + capacity +
                ", count=" + count +
                '}';
    }
}
