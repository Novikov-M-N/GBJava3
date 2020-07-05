package lesson5.homework;

public class Car implements Runnable{
    private static int CARS_COUNT = 0;
    private final Race race;
    private final int speed;
    private final String name;

    public String getName() { return name; }
    public int getSpeed() { return speed; }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            TimeStamp.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            TimeStamp.println(this.name + " готов");
            //По готовности каждого участника снимаем одну предстартовую защёлку.
            MainClass.getStart().countDown();
            //Если не все машины прошли подготовку - ждём.
            MainClass.getStart().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        //Если машина на финиш пришла первой, объявляем о её победе. Иначе просто о факте финиша.
        if (MainClass.getFinish().getCount() == CARS_COUNT) { TimeStamp.println(this.name + " выиграл гонку"); }
        else { TimeStamp.println(this.name + " финишировал"); }
        //Снимаем одну финишную защёлку.
        MainClass.getFinish().countDown();
    }
}
