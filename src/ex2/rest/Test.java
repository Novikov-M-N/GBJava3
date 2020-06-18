package ex2.rest;

public class Test {
    public static void main(String[] args) {

        System.out.println("" + new Test().summ(3, 3));
    }

    private int summ(int i, int j) {
        try {
//           return i + j;
            throw new Exception("!");
        } catch (Exception e) {
        } finally {
            if (i == j) {
                return 0;
            }
        }
        return 5;
    }
}
