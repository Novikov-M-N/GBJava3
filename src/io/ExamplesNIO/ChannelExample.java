package io.ExamplesNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelExample {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(
                "C:\\1.txt",
                "r")
        ) {
            FileChannel in = raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            int bytesRead = in.read(bb);
            while(bytesRead != -1) {
                bb.flip();
                while(bb.hasRemaining()){
                    System.out.print((char)bb.get());
                }
                bb.clear();
                bytesRead = in.read(bb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile("2.txt", "rw");
        randomAccessFile.writeBytes("NEW STRING");
        randomAccessFile.seek(8);
        randomAccessFile.writeBytes("NEW STRING 2");
        randomAccessFile.close();

    }
}
