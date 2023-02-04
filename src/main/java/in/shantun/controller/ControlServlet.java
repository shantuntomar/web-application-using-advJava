package in.shantun.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.shantun.dto.Student;
import in.shantun.factory.StudentServiceFactory;
import in.shantun.services.IStudentService;

@WebServlet("/controller/*")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		System.out.println(requestUri);
		
		IStudentService stdService = StudentServiceFactory.getStudentService();
		RequestDispatcher rd = null;
		
		if(requestUri.endsWith("layout")) {
			rd = request.getRequestDispatcher("../layout.html");
			rd.forward(request, response);
		}
		
		if(requestUri.endsWith("addform")) {
			
			//create an empty student object.
			Student student = new Student(); // []
			
			//fetching the data from the request.
			String sname = request.getParameter("sname"); // arun 
			String sage = request.getParameter("sage"); // 12 
			String saddr = request.getParameter("saddr");// gwl
			
			// save the feilds in the student object.
			student.setName(sname); // name -> arun
			student.setAddr(saddr); // addr -> gwl
			student.setAge(Integer.parseInt(sage)); // age -> 12
			
			//save the student.
			String status = stdService.save(student);
			System.out.println(status);
			
			//sending the success and failing message to the client.
			if(status.equalsIgnoreCase("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
			
		}
		
		//search for the student.
		if(requestUri.endsWith("searchform")) {
			String sid = request.getParameter("sid");
			Student student = stdService.findById(Integer.parseInt(sid));
			if(student != null) {
				//display the data of student.
				response.setContentType("text/html");

				PrintWriter out = response.getWriter();
				out.println("<html><head><title>STUDENT DATA</title></head>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br/><br/><br/>");
				out.println("<table align='center' border='1'>");
				out.println("<tr>");
				out.println("<th>SID</th>");
				out.println("<th>SNAME</th>");
				out.println("<th>SAGE</th>");
				out.println("<th>SADDRESS</th>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td>" + student.getId() + "</td>");
				out.println("<td>" + student.getName() + "</td>");
				out.println("<td>" + student.getAge() + "</td>");
				out.println("<td>" + student.getAddr() + "</td>");
				out.println("</tr>");

				out.println("</table>");
				out.println("</body>");
				out.println("</html>");

				out.close();
			}
			else {
				rd = request.getRequestDispatcher("../notfound.html");
				rd.forward(request, response);
			}
		}
		
		//delete the student.
		if(requestUri.endsWith("deleteform")) {
			String sid = request.getParameter("sid");
			String status = stdService.deleteById(Integer.parseInt(sid));
			
			if(status.equalsIgnoreCase("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else if(status.equalsIgnoreCase("fail")) {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
			else {
				rd = request.getRequestDispatcher("../notfound.html");
				rd.forward(request, response);
			}
		}
		
		//update the student data.
		if(requestUri.endsWith("editform")) {
			String sid = request.getParameter("sid");
			Student student = stdService.findById(Integer.parseInt(sid));
			if(student != null) {
				//edit form.
				response.setContentType("text/html");

				// display editpage using html
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>OUTPUT</title></head>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br/><br/><br/>");
				out.println("<form method='post' action='./update'>");
				out.println("<table align='center'>");
				out.println("<tr><th>ID</th><td>" + student.getId() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + student.getId() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getName()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getAge()
						+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + student.getAddr()
						+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
			else {
				rd = request.getRequestDispatcher("../notfound.html");
				rd.forward(request, response);
			}
		}
		
		//update logic.
		if(requestUri.endsWith("update")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname"); // arun 
			String sage = request.getParameter("sage"); // 12 
			String saddr = request.getParameter("saddr");// gwl
			
			Student student = new Student();
			
			student.setId(Integer.parseInt(sid));
			student.setName(sname);
			student.setAge(Integer.parseInt(sage));
			student.setAddr(saddr);
			
			String status = stdService.updateById(student);
			
			if(status.equalsIgnoreCase("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}	
		}	
	}

}
