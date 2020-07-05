package lesson5.homework;

import java.util.concurrent.CountDownLatch;

public class MainClass {
    private static final int CARS_COUNT = 4;
    //Старт и финиш - счётчики, собирающие машины перед стартом и перед объявлением завершения гонки
    private static CountDownLatch start = new CountDownLatch(CARS_COUNT + 1);
    private static CountDownLatch finish = new CountDownLatch(CARS_COUNT);

    public static CountDownLatch getStart() { return start; }
    public static CountDownLatch getFinish() { return finish; }
    public static int getCarsCount() { return CARS_COUNT; }

    public static void main(String[] args) {
        TimeStamp.setStartTime(System.currentTimeMillis());
        TimeStamp.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) { new Thread(car).start(); }

        //По состоянию предстартового счётчика проверяем, все ли машины готовы стартовать. Если нет - ждём.
        while (start.getCount() > 1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TimeStamp.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        //После объявления старта снимаем последнюю защёлку, и машины начинают прохождение трассы.
        start.countDown();

        //Перед объявлением о завершении гонки ждём, пока все машины придут к финишу.
        try {
            finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TimeStamp.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
