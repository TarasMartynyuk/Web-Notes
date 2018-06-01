package http.server;

import http.server.processors.Processor;
import http.server.processors.StaticResourceProcessor;
import http.server.processors.ServletProcessor;
import http.server.servlet.AbstractServletsMap;

import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;

public class HttpServer {

    /**
     * WEB_ROOT is the directory where our HTML and other files reside. For this
     * package, WEB_ROOT is the "webroot" directory under the working directory.
     * The working directory is the location in the file system from where the
     * java command was invoked.
     */
    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private static final boolean SHUTDOWN = true;

    private final AbstractServletsMap servletsMap;
    
    public HttpServer(AbstractServletsMap servletsMap) {
        this.servletsMap = servletsMap;
    }
    
    public void await() throws IOException {
        ServerSocket serverSocket = null;
        int port = 8888;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }        
        
        System.out.println("Server is waiting for request at port: " + port);
        servletsMap.callInit();
        boolean isShutDown = !SHUTDOWN;
        // Loop waiting for a request
        while (!isShutDown) {
            try {
                Socket socket = serverSocket.accept();
                isShutDown = processRequest(socket);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        servletsMap.callDestroy();
        serverSocket.close();
    }


//    void writeAll(InputStream in) throws IOException {
//        var twoBytes = new byte[2];
//        int inLine = 0;
//        while (true) {
//
//            if(in.read(twoBytes, 0, twoBytes.length) == -1) {
//                System.out.println("EOF!");
//                break;
//            }
//
//            System.out.print(new String(twoBytes, "UTF-8"));
//            inLine += 2;
//            if(inLine >= 80) {
//                System.out.print("\n");
//            }
//        }
//    }

    private boolean processRequest(Socket socket) throws IOException {
        var input = socket.getInputStream();
        var output = socket.getOutputStream();

//        writeAll(input);

        Request request = new HttpRequest(input);
//        System.out.println(request.getRequestAsText());

        Response response = new HttpResponse(output);

        String uri = request.getURI();
        if (isNull(uri)) {
            return !SHUTDOWN;
        }

        if (isShutDown(uri)) {
            return SHUTDOWN;
        }

        var processor = selectProcessor(uri);
        processor.process(request, response);

        // Close the socket
        socket.close();

        return !SHUTDOWN;
    }

    private boolean isNull(String uri) {
        return uri == null;
    }

    private boolean isShutDown(String uri) {
        return uri.equals(SHUTDOWN_COMMAND);
    }

    private Processor selectProcessor(String uri) {
        Processor processor;
        // check if this is a request for a servlet or a static resource
        // a request for a servlet begins with "/servlet/"
        if (uri.startsWith("/servlet/")) {
            processor = new ServletProcessor(servletsMap);
        } else {
            processor = new StaticResourceProcessor();
        }
        return processor;
    }
}
