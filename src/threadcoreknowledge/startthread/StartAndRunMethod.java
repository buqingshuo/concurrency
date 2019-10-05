package threadcoreknowledge.startthread;

/**
 * 对比start和run两种启动线程的方式
 * Created by 卜庆硕 on 2019/8/31.
 */
public class StartAndRunMethod {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();

    }
}
