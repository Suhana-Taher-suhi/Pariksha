
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection con = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String userName = request.getParameter("user");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Error: unable to load driver class!");

		}
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination", "root", "svcdhoney1");

			Statement statement = con.createStatement();

			String sql = "SELECT username,password FROM Login";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				// Retrieve by column name
				String username1 = rs.getString("username");

				String password1 = rs.getString("password");
				;

				if (userName.equalsIgnoreCase(username1) && password.equals(password1)) {

					RequestDispatcher rd = request.getRequestDispatcher("WelcomeServlet");
					rd.forward(request, response);

				}
			}
			if (!rs.next() ) {
				//RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				//rd.forward(request, response);
				 
			   
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
