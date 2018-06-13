package myapp.servlets;

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
        servlets.put("/notes.add", new NotesAdditionServlet());
        servlets.put("/notes.list", new NotesListingServlet());
        servlets.put("/notes.delete", new NotesDeleteServlet());
        servlets.put("/notes.edit", new NotesEditServlet());

    }
}
