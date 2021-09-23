package server;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求对象(inputStream)
 */
public class Request {

    private String method;
    private String url;

    private InputStream inputStream;

    public Request() {
    }

    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        String body = new String(bytes);
        String firstLineBody = body.split("\\n")[0];
        String[] strings = firstLineBody.split(" ");
        this.method = strings[0];
        this.url = strings[1];
        System.out.println("====================>>>> request method:" + this.method);
        System.out.println("====================>>>> request method:" + this.url);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
