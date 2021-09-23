package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * scoket主类
 */
public class Bootstrap {

    /**
     * 监听端口
     */
    private int port = 8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * scoket启动需要初始化展开的一些操作
     */
    public void start() throws IOException {
        //浏览器请求localhost:8080返回固定字符串
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("====================>>>>Scoket start on port: " + port);

//        while (true){
//            Socket socket = serverSocket.accept();
//            //请求接收
//            OutputStream outputStream = socket.getOutputStream();
//            String data = "hello word";
//            String responseText = HttpProtocoUtil.successHeader(data.getBytes().length) + data;
//            outputStream.write(responseText.getBytes());
//            socket.close();
//        }

        //封装Resuest和Response对象,返回静态资源文件.
        while (true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            //请求接收
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes);
            System.out.println("====================>>>> request body:" + new String(bytes));
            socket.close();
        }
    }



    /**
     * scoket启动入口
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }
}
