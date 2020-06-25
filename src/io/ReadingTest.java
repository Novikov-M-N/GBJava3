package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadingTest {
    public static void main(String[] args) {
        readingStringVsStringBuilder();
    }

    public static  void readingStringVsStringBuilder() {
        String str = "";
        long time0 = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream("123.txt")) {
            int x;
            do {
                x = in.read();
                str += (char)x;
           } while (x != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        time0 = System.currentTimeMillis();
        System.out.println("String reading: " + (System.currentTimeMillis() - time0));

        StringBuilder sb = new StringBuilder("");
        try (FileInputStream in = new FileInputStream("123.txt")) {
            int x;
            do {
                x = in.read();
                sb.append((char)x);
            } while (x != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("StringBuilder reading: " + (System.currentTimeMillis() - time0));
    }
}
