package io.ExamplesNIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        String text = "HellosgsdfdfdsfdsfsdfdfdFDSFZSDF";
        try (FileOutputStream fos = new FileOutputStream("1.txt")) {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fin = new FileInputStream("1.txt")) {
            System.out.printf("File size: %d byte \n", fin.available());
            int j = -1;
            while ((j = fin.read()) != -1) {
                System.out.println((char) j);
            }
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes, 0, fin.available());
            System.out.println("file data:");
            for(int i = 0; i<bytes.length; i++){
                System.out.println((char) bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
