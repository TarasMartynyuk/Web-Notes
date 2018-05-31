package myapp;

import http.server.servlet.AbstractServletsMap;
import myapp.web.HelloServlet;
import myapp.web.PrimitiveServlet;

/**
 *
 * @author andrii
 */
public class ServletsMap extends AbstractServletsMap {


    public ServletsMap() {
        servlets.put("/hello", new HelloServlet());
        servlets.put("/test", new PrimitiveServlet());

    }

}
