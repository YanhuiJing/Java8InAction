package ConcurrencyAction;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产消费者模式
 *
 * @author gavin
 * @createDate 2020/3/6
 */
public class ProducerConsumerModel {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        BlockingQueue<Model> blockingQueue = new LinkedBlockingQueue<>(10);

        Producer p1 = new Producer(blockingQueue);
        Producer p2 = new Producer(blockingQueue);
        Producer p3 = new Producer(blockingQueue);

        Consumer c1 = new Consumer(blockingQueue);
        Consumer c2 = new Consumer(blockingQueue);
        Consumer c3 = new Consumer(blockingQueue);

        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);


        Thread.sleep(5000);
        p1.stop();
        p2.stop();
        p3.stop();



    }
}

@Data
@AllArgsConstructor
class Model{

    private Integer id;
    private String name;

}

class Consumer implements Runnable{

    private BlockingQueue<Model> blockingQueue;
    private static Random random = new Random();

    public Consumer(BlockingQueue<Model> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {

                Model model = this.blockingQueue.take();
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+"成功消费"+model.toString()+"队列数量"+blockingQueue.size());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}


class Producer implements Runnable{

    //共享队列,用于存放生产出来的数据
    private BlockingQueue<Model> blockingQueue;
    //设置静态AtomicInteger,生成唯一的标识号
    private static AtomicInteger count = new AtomicInteger(0);
    //设置线程运行标识符,如果为false则结束生产数据
    private volatile Boolean flag = true;

    private static Random random = new Random();


    public Producer(BlockingQueue blockingQueue){

        this.blockingQueue = blockingQueue;

    }

    @SneakyThrows
    @Override
    public void run() {
        while(flag){
            int num = count.getAndIncrement();
            Model model = new Model(num,Integer.toString(num));

            try {

                Thread.sleep(random.nextInt(5000));
                if(blockingQueue.offer(model)){
                    System.out.println(Thread.currentThread().getName()+"成功生产"+model.toString());
                };


            } catch (InterruptedException e) {
                System.out.println(model.toString()+"生产不成功！");
                e.printStackTrace();
            }

        }

    }


    public void stop(){

        this.flag = false;
    }

}