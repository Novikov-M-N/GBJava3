package lesson5.nexthw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class NumberProducer implements Runnable {
    private BlockingQueue<Integer> nIntegers;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public NumberProducer(BlockingQueue<Integer> nIntegers, int poisonPill, int poisonPillPerProducer) {
        this.nIntegers = nIntegers;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            generateNumber();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumber() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            nIntegers.put(ThreadLocalRandom.current().nextInt(100));
        }
        for (int j = 0; j < poisonPillPerProducer; j++) {
            nIntegers.put(poisonPill);
        }
    }
}
