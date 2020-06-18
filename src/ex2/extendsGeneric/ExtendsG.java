package ex2.extendsGeneric;

import java.util.ArrayList;
import java.util.List;

public class ExtendsG {
    public static void main(String[] args) {
//        List<CharSequence> charSequences = new ArrayList<String>();

        List<Integer> list = new ArrayList<>();
        List<? extends Number> listObject = list;

        List<Number> listObject2 = new ArrayList<>(); //Integer, Double ...
        List<? super Integer> list2 = listObject2;
    }
}
