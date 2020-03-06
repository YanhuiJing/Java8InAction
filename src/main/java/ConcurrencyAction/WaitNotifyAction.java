package ConcurrencyAction;

/**
 * @author gavin
 * @createDate 2020/3/6
 */
public class WaitNotifyAction {

    private static Object object = new Object();

    public static class WaitThread extends Thread{

        @Override
        public void run() {
            synchronized (object){

                System.out.println(System.currentTimeMillis()+"t1 start");

                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis()+"t1 stop");

            }
        }
    }

    public static class NotifyThread extends Thread{

        @Override
        public void run() {

            synchronized (object){

                System.out.println(System.currentTimeMillis()+"t2 start");


                object.notify();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis()+"t2 stop");

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        WaitThread waitThread = new WaitThread();

        NotifyThread notifyThread = new NotifyThread();

        waitThread.start();
        notifyThread.start();

    }
}
