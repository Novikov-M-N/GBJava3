package lesson5.nexthw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;

public class NumberConsumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumberConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Run {
    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(BOUND);
        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumberProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }
        for (int i = 1; i < N_CONSUMERS; i++) {
            new Thread(new NumberConsumer(queue, poisonPill)).start();
        }

        new Thread(new NumberProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }
}
