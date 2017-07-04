package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.LoginDao;

/**
 * Servlet implementation class MultipleFruitsServlet
 */
@WebServlet("/MultipleFruitsServlet")
public class MultipleFruitsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultipleFruitsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexamination","root","suchi");
			Statement statement = con.createStatement();
					
			String sql1 = "insert into questioninfo(qid, sid, tid, question, option1, option2, option3, option4, solution) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			String []checkedValues = request.getParameterValues("cname");
			String testName=request.getParameter("testName");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print("<html><body>");
			out.print("<h1>Selected Questions for the test</h1>");
			out.print("<h1>" + testName+ "</h1>");
			out.print("<ul>");
			for (String s : checkedValues) {
				out.print("<li>" + s + "</li>");
			}
			for(String a: checkedValues) {
				int qid = Integer.parseInt(a);
				String sql2 = "SELECT * FROM questioninfo where (qid = "+ qid +") && (tid = 1) && (sid = 1)";
				Statement stmnt = con.createStatement();
				ResultSet rrs = stmnt.executeQuery(sql2);
				out.print(a);
				int sid = 0;
				int tid = 1;
				while(rrs.next()) {
					int qid1 = rrs.getInt("qid");
					String Question1 = rrs.getString("question");
					String op1 = rrs.getString("option1");
					String op2 = rrs.getString("option2");
					String op3 = rrs.getString("option3");
					String op4 = rrs.getString("option4");
					String soln = rrs.getString("solution");
					out.print(Question1 + "<br>");
					out.print(op1 + "<br>");
					out.print(op2 + "<br>");
					out.print(op3 + "<br>");
					out.print(op4 + "<br>");
					out.print(soln + "<br>");
					ps1.setInt(1, qid1);
					ps1.setInt(2,  sid);
					ps1.setInt(3, tid);
					ps1.setString(4, Question1);
					ps1.setString(5, op1);
					ps1.setString(6, op2);
					ps1.setString(7, op3);
					ps1.setString(8, op4);
					ps1.setString(9, soln);
					ps1.executeUpdate();
				}
			}
			
			out.print("</ul>");
			out.print("</body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}