package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 响应对象(outputStream)
 */
public class Response {

    private OutputStream outputStream;

    public Response() {
    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void outPut(String content) throws IOException {
        outputStream.write(content.getBytes());
    }

    public void outPutHtml(String path) throws IOException {
        //获取静态资源绝对路径
        String absolutePath = StaticResourceUtil.getAbsolutePath(path);
        //输入静态资源文件
        File file = new File(absolutePath);
        if(file.exists() || file.isFile()){
            StaticResourceUtil.outPutStaticResource(new FileInputStream(file),outputStream);
        }else{
            outPut(HttpProtocoUtil.notFoundHeader());
        }
    }
}
