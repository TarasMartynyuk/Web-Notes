package myapp.web;


import http.server.servlet.AbstractServlet;
import http.server.request.Request;
import http.server.response.Response;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet extends AbstractServlet {

    int count;

    @Override
    public void init() {
        System.out.println("---from Servlet init---");
    }

    @Override
    public void service(Request request, Response response) throws IOException {
        System.out.println("---from Servlet service---");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head/><body>");
            out.println("<b>Hello. Roses are red.</b> <br>");
            out.println("Count: " + Counter.getCount());
            out.println("<br>");
            out.print("<i>Violets are blue.</i>");
            String[] list = {"A", "V", "C"};
            for (String s : list) {
                out.println(s);
                out.println("<input type=\"submit\" value=\"Show\"/>");
                out.println("<br>");
            }
            
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
        System.out.println("---from Servlet destroy---");
    }

}
