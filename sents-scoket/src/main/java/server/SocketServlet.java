package server;

import java.io.IOException;

public class SocketServlet extends HttpServlet{
    @Override
    public void doGet(Request request, Response response){
        String context = "<h1>SocketServlet-get</h1>";
        String result = (HttpProtocoUtil.successHeader(context.getBytes().length) + context);

        try{
            response.outPut(result);
        }catch (Exception ex){

        }
    }

    @Override
    public void doPost(Request request, Response response) {
        String context = "<h1>SocketServlet-post</h1>";
        String result = (HttpProtocoUtil.successHeader(context.getBytes().length) + context);

        try{
            response.outPut(result);
        }catch (Exception ex){

        }
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destory() throws Exception {

    }
}
