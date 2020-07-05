package lesson5;

public class Solution {
    public static void main(String[] args) {
        System.out.println("4/2=" + 4 / 2 == "4/2=2");
        String s = " ";//1 пробел
        String string = s + "12345" + s + s;
        String string2 = string.trim();
        string2.concat(s);
        System.out.println(string.length() + " " + string2.length());
    }
}





















