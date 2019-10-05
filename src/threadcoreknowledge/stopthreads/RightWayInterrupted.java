package threadcoreknowledge.stopthreads;

/**
 * 注意Thread.interrupted()方法的目标对象是“当前线程”，而不管本方法来自于哪个对象
 * Created by 卜庆硕 on 2019/9/8.
 */
public class RightWayInterrupted {

    // 输出结果为true,false,false,true
    // 第一次输出为threadOne线程的中断状态，因为刚刚上一语句中断，所以为true
    // 第二次输出为threadOne.interrupted()，虽然是用对象threadOne引用了interrupted方法，但因为threadOne仍处于中断状态，所以当前线程应该是main方法的线程
    // 第三次输出Thread.interrupted()同第二次一样，当前线程都是main
    // 第四次输出，因为前面的方法并没有对threadOne的中断状态进行唤醒，所以同第一次输出一样
    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });

        //启动线程
        threadOne.start();
        //设置中断标志
        threadOne.interrupt();
        //获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        //获取中断标志并重置
        System.out.println("isInterrupted:" + threadOne.interrupted());
        //获取中断状态并重置
        System.out.println("isInterrupted:" + Thread.interrupted());
        //获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("Main thread is over");
    }
}
