package ConcurrencyAction;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gavin
 * @createDate 2020/3/6
 */
public class ThreadPoolAction implements Runnable{

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {

        try {
            int temp = count.incrementAndGet();
            System.out.println(temp+"当前线程id和名称为:" + Thread.currentThread().getId() +", " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Runnable> queue =
                new ArrayBlockingQueue<Runnable>(10);
        ExecutorService executor  = new ThreadPoolExecutor(
                5, 		//core
                10, 	//max
                120L, 	//2fenzhong
                TimeUnit.SECONDS,
                queue);

        //创建线程池的最大数量为10,部分thread被重复使用,轮询执行线程
        for(int i = 0 ; i < 20; i++){
            executor.execute(new ThreadPoolAction());
        }
        Thread.sleep(1000);
        System.out.println("queue size:" + queue.size());		//10
        Thread.sleep(2000);

    }
}
