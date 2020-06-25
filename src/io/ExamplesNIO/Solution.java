package io.ExamplesNIO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Solution implements Serializable {

    public static void main(String[] args) {
        List list = new ArrayList(3);//1
        list.add(100);
        list.add(new Integer(200));
        list.add(new String(String.valueOf(300)));
        list.add(null);
        list.add(new Integer(400));//2

        System.out.println(list.size());//3

        int[] ints = new int[10];
        System.out.println(ints[5]);
    }

}
