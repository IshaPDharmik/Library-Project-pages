package LibraryPagesServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("Email Id");
		String password = request.getParameter("Password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "prachi");
			PreparedStatement ps = c.prepareStatement("Select * from registerform where email=? and password=?");
			ps.setNString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				request.setAttribute("F-Name", rs.getString(1));
				request.setAttribute("L-Name", rs.getString(2));
				request.setAttribute("Dob", rs.getString(3));
				request.setAttribute("Phone", rs.getString(4));
				request.setAttribute("alter-phonenumber", rs.getString(5));
				request.setAttribute("gender", rs.getString(6));
				request.setAttribute("Email", rs.getString(7));
				request.setAttribute("password", rs.getString(8));
				request.setAttribute("confirm-password", rs.getString(9));
				request.setAttribute("address", rs.getString(10));
				request.setAttribute("city", rs.getString(11));
				request.setAttribute("state", rs.getString(12));

				RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
				rd.include(request, response);

			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.print("<h1 style='color=red;'>emailId and password doesn't match</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("LoginPage.html");
				rd.forward(request, response);

			}

			c.close();
		} catch (Exception e) {

		}
	}
}
