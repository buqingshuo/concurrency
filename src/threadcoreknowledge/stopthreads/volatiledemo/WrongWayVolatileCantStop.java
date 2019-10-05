package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示volatile的局限：part2 陷入阻塞时，volatile是无法停止线程的，此例中，生产者的生产速度很快，消费者消费速度很慢，所以阻塞队列塞满后，生产者会阻塞，等待消费者进一步消费
 * Created by 卜庆硕 on 2019/9/8.
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        // 给1秒钟的时间让生产者线程把队列塞满
        Thread.sleep(1000);
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(1000);
        }
        System.out.println("消费者不需要更多数据了");
        //一旦消费者不需要更多数据了，我们应该让生产者也停止下来
        // 但实际情况是，由于消费者不再消费了，而队列又满了，生产者阻塞在storage.put(num);中，无法进行canceled的判断
        producer.canceled = true;
        System.out.println(producer.canceled);
    }

}

class Producer implements Runnable {

    public volatile boolean canceled = false;
    private BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override

    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者停止运行");
        }
    }
}

class Consumer {

    public BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }

}
