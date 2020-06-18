package ex2.error1;

import java.util.ArrayList;
import java.util.List;
//raw types

public class ErrorOne {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("Hello");
//        list.add(123);
        for(Object string : list){
            System.out.println("-" + (String) string);
        }
    }
}
