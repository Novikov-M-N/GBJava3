package ex2.average;

public class Average2<T extends Number> {
    private T[] array;
    private T ob;

    public Average2(T[] array) {
        this.array = array.clone();
    }

    public Average2(T o, T[] array) {
        ob = o;
        this.array = array.clone();
    }

    public double average() {
        double sum = 0.0;

        for (T value : array) {
            sum += value.doubleValue();
        }

        return sum / array.length;
    }

    boolean sameAvg(Average2<?> ob)
    {
        return average() == ob.average();
    }
}

class BoundsDemo2 {
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Average2<Integer> iob = new Average2<>(intArray);
        double v = iob.average();
        System.out.println("Integer average is " + v);

        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
        Average2<Double> dob = new Average2<>(doubleArray);
        double w = dob.average();
        System.out.println("Double average is " + w);

        Float[] floatArray = {1.0F, 2.0F, 3.0F, 4.0F, 5.0F};
        Average2<Float> fob = new Average2<>(floatArray);
        double x = fob.average();
        System.out.println("Float average is " + x);

        System.out.print("Averages of iob and dob ");
        if (iob.sameAvg(dob)) {
            System.out.println("are the same.");
        } else {
            System.out.println("differ.");
        }

        System.out.print("Averages of iob and fob ");
        if (iob.sameAvg(fob)) {
            System.out.println("are the same.");
        } else {
            System.out.println("differ.");
        }
    }
}
