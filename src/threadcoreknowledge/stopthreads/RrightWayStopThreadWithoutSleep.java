package threadcoreknowledge.stopthreads;

/**
 * run方法内没有sleep和wait方法时，停止线程
 * Created by 卜庆硕 on 2019/8/31.
 */
public class RrightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RrightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
