package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 展示wait和notify的基本用法
 * 1、研究代码执行顺序
 * 2、证明wait释放锁
 * Created by 卜庆硕 on 2019/9/18.
 */
public class Wait {

    private static Object object = new Object();

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行。");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获得到了锁。");
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println(Thread.currentThread().getName() + "调用了notify()。");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }
}
