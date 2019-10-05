package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复无尽等待的问题
 * Created by 卜庆硕 on 2019/9/8.
 */
public class WrongWayVolatileFiexed {

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFiexed wrongWayVolatileFiexed = new WrongWayVolatileFiexed();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = wrongWayVolatileFiexed.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        // 给1秒钟的时间让生产者线程把队列塞满
        Thread.sleep(1000);
        Consumer consumer = wrongWayVolatileFiexed.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(1000);
        }
        System.out.println("消费者不需要更多数据了");
        // 使用interrupt来中断线程
        producerThread.interrupt();
    }


    class Producer implements Runnable {

        private BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override

        public void run() {
            int num = 0;
            try {
                while (num < 10000 && !Thread.currentThread().isInterrupted()) {
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
}
