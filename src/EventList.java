

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EventList
 */
@WebServlet("/EventList")
public class EventList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter web = response.getWriter();
		response.setContentType("text/html");
		web.append("Served at: ").append(request.getContextPath());
		String title = "Calendar Manager";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	    web.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + 
	    		"<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
	    
	    // PRINT EVENT LIST
	    List<Event> list = UtilDB.listEvent();
	    web.println("<div style=\"border: 3px solid blue; margin: auto; width: 50%;\">");
	    for (int i = 0; i < list.size(); i++) {
	    	Event event = list.get(i);
	    	web.println("<div style=\"text-align: center; padding: 10px;\"> [" + event.getId() + "] " +
	    			event.getName() + " on " + event.getDate() + " from " +
	    			event.getStart() + "-" + event.getEnd() + " (" + event.getUser() +
	    			")");
	    	web.println("</div>");
	    }
	    web.println("</div>");
	    
	    // SPACING
	    web.println("<div><br/><div/>");
	    
	    // PRINT EVENT CREATION
	    web.println("<div style=\"border: 3px solid blue; margin: auto; width: 50%;\"><h1 style=\"margin: auto; text-align: center; padding: 10px;\">"
	    		+ "Create New Event</h1><form style=\"margin: auto; text-align: center; padding:10px\" action=\"EventList\" method=\"POST\">"
	    		+ "Event Name: <input style=\"padding: 2px;\" type\"text\" name=\"name\"/><br/>" 
	    		+ "Date: <input style=\"padding: 2px;\" type=\"text\" name=\"date\"/><br/>" 
	    		+ "Start Time: <input style=\"padding: 2px;\" type=\"text\" name=\"start\"/><br/>" 
	    		+ "End Time: <input style=\"padding: 2px;\" type=\"text\" name=\"end\"/><br/>" 
	    		+ "User: <input style=\"padding: 2px;\" type=\"text\" name=\"user\"/><br/>" 
	    		+ "<input style=\"padding: 10px;\"type=\"submit\" value=\"Create Event\"/>"
	    		+ "</form></div>");
	    
	    // SPACING
	    web.println("<div><br/><div/>");
	    
	    // PRINT EVENT DELETION
	    web.println("<div style=\"border: 3px solid blue; margin: auto; width: 50%;\"><h1 style=\"margin: auto; text-align: center; padding: 10px;\">"
	    		+ "Delete Event by Id</h1><form style=\"margin: auto; text-align: center; padding: 10px\" action'\"EventList\" method=\"POST\">"
	    		+ "Event Id: <input style=\"padding: 2px;\" type=\"text\" name=\"id\"/><br/>"
	    		+ "<input style=\"padding: 10px;\" type=\"submit\" value=\"Delete Event\"/>"
	    		+ "</form></div>");
	    
	    // END WEBSITE
	    web.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("user") != null) {
			UtilDB.createEvent(request.getParameter("user"), request.getParameter("start"), request.getParameter("end"), request.getParameter("date"), request.getParameter("name"));
		}
		else if (request.getParameter("id") != null) {
			UtilDB.deleteEvent(Integer.parseInt(request.getParameter("id")));
		}
		
		doGet(request, response);
	}


}
