package server;

import java.nio.charset.StandardCharsets;

/**
 * 协议工具,提供响应头信息.
 */
public class HttpProtocoUtil {

    public static String successHeader(long contentLength){
        return "HTTP/1.1 200 OK \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + contentLength + "\n" +
                "\r\n";
    }

    public static String notFoundHeader(){
        String body = "<h1>404 not found</h1>";
        return "HTTP/1.1 404 NOT FOUND \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + body.getBytes().length + "\n" +
                "\r\n" + body;
    }

}
