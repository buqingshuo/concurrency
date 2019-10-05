package threadcoreknowledge.startthread;

/**
 * 演示不能两次调用start方法，否则会报错
 * Created by 卜庆硕 on 2019/8/31.
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
