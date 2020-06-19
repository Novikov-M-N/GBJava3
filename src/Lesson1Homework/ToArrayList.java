package Lesson1Homework;

import java.util.ArrayList;
import java.util.List;

public class ToArrayList<T> {
    public List<T> transform(T[] inputArray) {
        List<T> list = new ArrayList();
        for (T t:inputArray) {
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) {
        String[] stringArray = {"first", "second", "third", "fourth", "fifth"};
        Integer[] integerArray = {1, 2, 3, 4, 5};
        Float[] floatArray = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f};

        ToArrayList list = new ToArrayList();

        List<String> stringList = list.transform(stringArray);
        List<Integer> integerList = list.transform(integerArray);
        List<Float> floatList = list.transform(floatArray);

        System.out.println(stringList);
        System.out.println(integerList);
        System.out.println(floatList);
    }
}
