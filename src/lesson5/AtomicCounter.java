package lesson5;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * i++; ++i ; --i;i--
 * integer = integer +1;
 * сравнение с обменом
 */
public class AtomicCounter {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static volatile Integer integer = 0;
    private static int simple_i = 0;
    private static int count = 0;

    public static void main(String[] args) {
        AtomicCounter atomicCounter = new AtomicCounter();
        final int THREADS_COUNT = 50; // кол-во потоков
        final int TICKS_COUNT = 50; // количество "тиков" в каждом потоке
        CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        for (int q = 0; q < THREADS_COUNT; q++) {
            new Thread(() -> {
                for (int i = 0; i < TICKS_COUNT; i++) {
                    try {
                        Thread.sleep(100); // усыпляем поток на 3 мс.
                        atomicInteger.getAndAdd(1); // увеличиваем значение AtomicInteger на 1
                        integer = integer + 1;
                        simple_i++;
                        atomicCounter.setInteger(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown(); // как только поток все посчитал - уменьшаем счетчик
            }).start();
        }

        try {
            cdl.await(); // ждем пока оба потока выполнят все действия
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Integer: " + integer); // не гарантированно получим THREADS_COUNT * TICKS_COUNT
        System.out.println("synchronized void: " + count); // гарантированно получим THREADS_COUNT * TICKS_COUNT
        System.out.println("atomicInteger: " + atomicInteger); // гарантированно получим THREADS_COUNT * TICKS_COUNT
        System.out.println("int simple_i: " + simple_i); // не гарантированно получим THREADS_COUNT * TICKS_COUNT
    }

    public synchronized void setInteger(Integer integer) {
        this.count = count + integer;
    }
}
