package top.latke;

import java.nio.ByteBuffer;

public class PutBuffer {

    public static void main(String[] args) {
        //创建指定长度的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());
        System.out.println(allocate.remaining());

//        //修改当前索引所在位置
//        allocate.position(1);
//        //修改最多能操作到哪个索引位置
//        allocate.limit(9);
//
//        System.out.println(allocate.position());
//        System.out.println(allocate.limit());
//        System.out.println(allocate.capacity());
//        System.out.println(allocate.remaining());

        allocate.put((byte)97);

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());
        System.out.println(allocate.remaining());


        allocate.put("abc".getBytes());

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());
        System.out.println(allocate.remaining());

    }
}
