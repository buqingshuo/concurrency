package threadcoreknowledge.stopthreads;

/**
 * 最佳实践：catch了InterruptedException之后的优先选择：在方法签名中抛出异常
 * 这样做后，run()方法就会强制try/catch
 * Created by 卜庆硕 on 2019/9/1.
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("go");
            try {
                ThrowInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void ThrowInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
