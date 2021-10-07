package top.latke;

import java.nio.ByteBuffer;

public class GetBuffer {
    public static void main(String[] args) {
        //创建指定长度的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put("0123".getBytes());
        System.out.println("position:"+allocate.position());
        System.out.println("limit:"+allocate.limit());
        System.out.println("capacity:"+allocate.capacity());
        System.out.println("remaining:"+allocate.remaining());

//        byte b = allocate.get();
//        System.out.println("data:" + b);

        //切换读模式
        allocate.flip();
        for (int i = 0; i < allocate.limit(); i++) {
            System.out.println(allocate.get());
        }

        System.out.println(allocate.get(1));

        allocate.rewind();
        byte[] bytes = new byte[4];
        System.out.println(allocate.get(bytes));
        System.out.println(new String(bytes));


        byte[] array = allocate.array();
        System.out.println(new String(array));

        //切换写模式
        allocate.clear();
        allocate.put("abc".getBytes());

        byte[] array2 = allocate.array();
        System.out.println(new String(array2));
    }
}
