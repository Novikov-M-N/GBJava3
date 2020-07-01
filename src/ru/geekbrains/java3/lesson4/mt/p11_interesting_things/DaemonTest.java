package ru.geekbrains.java3.lesson4.mt.p11_interesting_things;

public class DaemonTest {

    public static void main(String[] args) {
        new WorkerThread().start();

        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        System.out.println("Main Thread ending") ;
    }

}

class WorkerThread extends Thread {

    public WorkerThread() {
        // When false, (i.e. when it a user thread),
        // the Worker thread continues to run.
        // When true, (i.e. when it a daemon thread),
        // the Worker thread terminates when the main
        // thread terminates.
        setDaemon(true);
    }

    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Hello from Daemon "+count++);

            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}
