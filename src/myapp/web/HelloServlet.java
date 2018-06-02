package myapp.web;


import myapp.ResponseBuilder;
import http.server.servlet.AbstractServlet;
import http.server.request.Request;
import http.server.response.Response;
import java.io.IOException;

/**
 *
 * @author andrii
 */
public class HelloServlet extends AbstractServlet  { 

    @Override
    public void service(Request req, Response res) throws IOException {
        var responce = "Hello from Servlet";

        var builder = new ResponseBuilder(res);
        builder.WriteOkResponce(responce, "text/html");

        res.getWriter().close();

    }



   

}
