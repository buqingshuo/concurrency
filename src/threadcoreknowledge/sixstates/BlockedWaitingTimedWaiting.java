package threadcoreknowledge.sixstates;

/**
 * 展示Blocked、Waiting、TimedWaiting三种线程状态
 * Created by 卜庆硕 on 2019/9/8.
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        // 让主线程先sleep一会，确保子线程可以执行到sleep方法
        Thread.sleep(100);
        // 打印出TIMED_WAITING，因为正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        // 打印出BLOCKED，因为thread2想要拿到syn()的锁却拿不到
        System.out.println(thread2.getState());
        //确保可以执行到wait方法
        Thread.sleep(1300);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
