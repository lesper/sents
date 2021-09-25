package server;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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
    public void start() throws Exception {
        loadServlet();
        int corePoolSize = 10;
        int maximumPoolSize = 50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handle = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handle);

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("====================>>>>Scoket start on port: " + port);

        //封装Resuest和Response对象,返回静态资源文件.
        while (true){
            Socket socket = serverSocket.accept();
            RequestProcessor requestProcessor = new RequestProcessor(socket,servletMap);
//            requestProcessor.start();
            threadPoolExecutor.execute(requestProcessor);
        }
    }

    private Map<String,HttpServlet> servletMap = new HashMap<String,HttpServlet>();

    /**
     * 加载解析web.xml,初始化servlet.
     */
    private void loadServlet() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("web.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element root = document.getRootElement();
            List<Element> servletNodes = root.selectNodes("//servlet");
            for (int i = 0; i < servletNodes.size(); i++) {
                Element element = servletNodes.get(i);
                Element servletNameElement = (Element) element.selectSingleNode("servlet-name");
                String servletName = servletNameElement.getStringValue();
                Element servletClassElement = (Element) element.selectSingleNode("servlet-class");
                String servletClass = servletClassElement.getStringValue();
                Element servletMapping = (Element) root.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
                servletMap.put(urlPattern,(HttpServlet) Class.forName(servletClass).newInstance());
            }
        }catch (Exception ex){

        }
    }


    /**
     * scoket启动入口
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }
}
