package top.latke.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1.打开一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.绑定对应端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //3.通道默认是阻塞的,设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        while (true){
            //4.检查客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel == null){
                System.out.println("===========无客户端连接==========");
                Thread.sleep(1000);
                continue;
            }
            //5.获取客户端传递的数据
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            //正数表示本次读到的有效字节数
            //0 本次未读到数据
            //-1 读到末尾
            int read = socketChannel.read(allocate);
            System.out.println("client data:" + new String(allocate.array(),0,read, StandardCharsets.UTF_8));
            //6.给客户端写数据
            socketChannel.write(ByteBuffer.wrap("server return".getBytes(StandardCharsets.UTF_8)));
            //7.释放资源
            serverSocketChannel.close();
        }
    }
}
