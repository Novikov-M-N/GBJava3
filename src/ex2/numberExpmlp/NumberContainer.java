package ex2.numberExpmlp;

public class NumberContainer<T extends Number> {
    private T number;

    public NumberContainer(T number) {
        this.number = number;
    }

    public void print(){
        System.out.println(number);
    }

    public static void main(String[] args) {
        NumberContainer numberContainer = new NumberContainer(2L);
        NumberContainer numberContainer2 = new NumberContainer(1);
//        NumberContainer numberContainer3 = new NumberContainer("f");
    }
}
