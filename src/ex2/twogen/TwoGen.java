package ex2.twogen;

public class TwoGen<T, V> {
    private T ob1;
    private V ob2;

    TwoGen(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    void showTypes() {
        System.out.println("Type of T is "
                + ob1.getClass().getName());

        System.out.println("Type of V is "
                + ob2.getClass().getName());
    }

    T getOb1() {
        return ob1;
    }

    V getOb2() {
        return ob2;
    }
}

class SimpleGen {
    public static void main(String[] args) {
        TwoGen<Integer, String> tgObj = new TwoGen<>(887687687, "Generics");

        tgObj.showTypes();

        int v = tgObj.getOb1();
        System.out.println("value: " + v);

        String str = tgObj.getOb2();
        System.out.println("value: " + str);
    }
}
