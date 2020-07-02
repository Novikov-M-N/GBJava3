package ru.geekbrains.java3.lesson4.mt.homework;

public class ABC {
    private final Object lock = new Object();
    private final int LETTER_COUNT;
    private volatile char currentLetter;

    public ABC(char currentLetter, int LETTER_COUNT) {
        this.currentLetter = currentLetter;
        this.LETTER_COUNT = LETTER_COUNT;
    }

    public void print(char letter, char nextLetter) {
        synchronized (lock) {
            try {
                for (int i = 0; i < LETTER_COUNT; i++) {
                    while (currentLetter != letter) { lock.wait(); }
                    System.out.print(letter);
                    currentLetter = nextLetter;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ABC abc = new ABC('A', 5);
        Thread t1 = new Thread(()->{abc.print('A','B');});
        Thread t2 = new Thread(()->{abc.print('B','C');});
        Thread t3 = new Thread(()->{abc.print('C','A');});
        t1.start();
        t2.start();
        t3.start();

    }
}
