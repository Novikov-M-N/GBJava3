package lesson5.homework;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    // Семафор следит за количеством машин, проходящих тоннель. Его ёмкость определяется как количество машин пополам.
    // Параметр справедливости ставим true - машины допускаются к препятствию в порядке прибития.
    private final Semaphore semaphore;

    public Tunnel() {
        this.length = 80;
        this.semaphore = new Semaphore(MainClass.getCarsCount() / 2, true);
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                TimeStamp.println(c.getName() + " готовится к этапу(ждет): " + description);
                // По прибытии к этапу машина отмечается в семафоре. Если места есть - проходит тоннель.
                semaphore.acquire();
                TimeStamp.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                TimeStamp.println(c.getName() + " закончил этап: " + description);
                // После прохождения тоннеля освобождаем семафор.
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
