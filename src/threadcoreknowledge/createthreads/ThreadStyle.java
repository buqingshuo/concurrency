package threadcoreknowledge.createthreads;

/**
 * 用Thread方式创建线程
 * Created by 卜庆硕 on 2019/8/24.
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread方式创建线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

}
