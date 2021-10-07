package top.latke;

import java.nio.ByteBuffer;

public class CreateBuffer {

    public static void main(String[] args) {
        //创建指定长度的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(allocate.get());
        }

        ByteBuffer wrap = ByteBuffer.wrap("latke".getBytes());
        for (int i = 0; i < 5; i++) {
            System.out.println(wrap.get());
        }
    }
}
