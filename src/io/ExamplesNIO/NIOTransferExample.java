package io.ExamplesNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NIOTransferExample {
    public static void main(String[] args) {
        transferFromExample();
    }

    public static void transferFromExample() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("dos.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile("dos2.txt", "rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
