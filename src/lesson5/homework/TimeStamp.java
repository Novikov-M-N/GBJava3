package lesson5.homework;

/**
 * Класс переопределяет функцию вывода на печать с отметкой времени (замена System.out.println())
 */
public class TimeStamp {
    // Нулевой момент отсчёта времени в миллисекундах
    private static long startTime;

    /**
     * Устанавливает нулевой момент отсчёта времени в миллисекундах
     * @param startTime Нулевой момент отсчёта времени в миллисекундах
     */
    public static void setStartTime(long startTime) { TimeStamp.startTime = startTime; }

    /**
     * Выводит на печать в консоль сообщение с отметкой текущего времени в секундах и миллисекундах
     * Формат итоговой строки: "сс:ммм | исходное сообщение"
     * @param message Исходное сообщение
     */
    public static void println(String message) {
        int time = (int)(System.currentTimeMillis() - startTime);
        int seconds = time/1000;
        int millis = time - seconds*1000;
        System.out.printf("%02d:%03d | %s\n", seconds, millis, message);
    }

}
