package io.ExamplesNIO;

import java.nio.CharBuffer;

public class CharBufferExample {
    public static void main(String[] args) {
        CharBuffer buf = CharBuffer.allocate(1024);
        char[] c = "Java\nHello\nBuffer\n".toCharArray();
        buf.put(c);
        buf.flip();
        while (buf.hasRemaining()) {
            System.out.print(buf.get());
        }
        buf.clear();
    }
}
