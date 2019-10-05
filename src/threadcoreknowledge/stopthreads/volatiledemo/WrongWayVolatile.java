package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * 演示volatile局限性：part1 看似可行
 * Created by 卜庆硕 on 2019/9/8.
 */
public class WrongWayVolatile implements Runnable {

    //volatile关键字，可理解为，可以让变量具有可见性，多个线程间都可以看到其真实的值
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }
}
