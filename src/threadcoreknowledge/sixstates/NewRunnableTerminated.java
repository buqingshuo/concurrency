package threadcoreknowledge.sixstates;

/**
 * 展示New、Runnable、Terminated三种线程状态。即使是正在运行，也是Runnable状态，而不是Running。
 * Created by 卜庆硕 on 2019/9/8.
 */
public class NewRunnableTerminated implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        // 线程刚刚创建，而没有被start时，处于NEW状态
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println("线程刚被创建时的状态：" + thread.getState());
        // 线程调用了start方法，会立刻进入RUNNABLE状态
        thread.start();
        System.out.println("线程调用了start方法后的状态：" + thread.getState());
        // 线程即使是正在运行，也是RUNNABLE状态，而不是RUNNING
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程正在运行时的状态：" + thread.getState());
        // 线程执行完毕后的状态时TERMINATED
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕后的状态：" + thread.getState());
    }
}
