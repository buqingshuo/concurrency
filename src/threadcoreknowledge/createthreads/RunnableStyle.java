package threadcoreknowledge.createthreads;

/**
 * 用Runnable方式创建线程
 * Created by 卜庆硕 on 2019/8/24.
 */
public class RunnableStyle implements Runnable {


    @Override
    public void run() {
        System.out.println("用Runnable方式实现线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}
