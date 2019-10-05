package threadcoreknowledge.stopthreads;

/**
 * run无法抛出checked Exception，只能用try/catch
 * Created by 卜庆硕 on 2019/9/1.
 */
public class RunThrowException {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
