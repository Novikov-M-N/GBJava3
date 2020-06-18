package ex2.error2;

import java.util.ArrayList;
import java.util.List;

public class Error2 {
    public static void print(List<? super String> list){
        list.add("bla bla");
        System.out.println(list.get(0));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Error2.print(list);
    }
}
