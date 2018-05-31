package myapp.web;


import http.server.servlet.AbstractServlet;
import http.server.Request;
import http.server.Response;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author andrii
 */
public class HelloServlet extends AbstractServlet  { 

    @Override
    public void service(Request req, Response res) throws IOException {
        PrintWriter out = res.getWriter();
        out.println("Hello from Servlet");
        out.close();
    }

   

}
