package Lesson1Homework;

public class ElementsTransposition<T> {
    void transpos(T[] arr, int from, int to) {
        T buffer = arr[from];
        arr[from] = arr[to];
        arr[to] = buffer;
        for (T t:arr) {
        }
    }

    void printArray(T[] arr) {
        for (T t:arr) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        ElementsTransposition et = new ElementsTransposition();
        String[] stringArray = {"first", "second", "third", "fourth", "fifth"};
        Integer[] integerArray = {1, 2, 3, 4, 5};
        Float[] floatArray = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f};

        et.transpos(stringArray, 2, 4);
        et.printArray(stringArray);
        et.transpos(integerArray, 1, 3);
        et.printArray(integerArray);
        et.transpos(floatArray, 0, 3);
        et.printArray(floatArray);
    }
}
