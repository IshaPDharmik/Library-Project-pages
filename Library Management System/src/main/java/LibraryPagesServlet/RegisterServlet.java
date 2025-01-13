package LibraryPagesServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regi")
public class RegisterServlet extends HttpServlet {
@Override	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String fname = request.getParameter("F-Name");
	String lname = request.getParameter("L-Name");
	String DateofBirth = request.getParameter("Dob");
	String phone = request.getParameter("Phone");
	String AlterNumber = request.getParameter("alter-phonenumber");
	String Gender = request.getParameter("gender");
	String email= request.getParameter("Email");
	String Password = request.getParameter("password");
	String Cpassword = request.getParameter("confirm-password");
	String Address = request.getParameter("address");
	String City= request.getParameter("city");
	String State = request.getParameter("state");

	
    System.out.println("Successfully...!");
    
        
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","prachi");
        PreparedStatement ps =c.prepareStatement(
     "insert into registerform(F-Name,L-Name,Dob,Phone,alter-phonenumber,gender,Email,password,confirm-password,address,city,state)");
      ps.setString(1,fname);
      ps.setString(2,lname );
      ps.setString(3,DateofBirth );
      ps.setString(4,phone);
      ps.setString(5,AlterNumber);
      ps.setString(6,Gender);
      ps.setString(7,email);
      ps.setString(8,Password);
      ps.setString(9,Cpassword);
      ps.setString(10,Address);
      ps.setString(11,City);
      ps.setString(12,State);
      c.close();
      PrintWriter out= response.getWriter();
      response.setContentType("text/html");
      out.print("<h1 style='color:salmon;'>inserted successfully</h1>");
      RequestDispatcher rd= request.getRequestDispatcher("/LoginPage.html");
      rd.include(request, response);
  
    } catch (Exception e) {
    
    }
}
}