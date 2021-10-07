package top.latke.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        socketChannel.write(ByteBuffer.wrap("cliet send".getBytes(StandardCharsets.UTF_8)));
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = socketChannel.read(allocate);
        System.out.println("server data:" + new String(allocate.array(),0,read,StandardCharsets.UTF_8));
        socketChannel.close();
    }
}
