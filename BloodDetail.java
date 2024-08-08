package blood;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/record2")
public class BloodDetail extends HttpServlet{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		
		String url = "jdbc:mysql://localhost:3306/hospital";
		String user = "root";
		String password = "Gupta@1729";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement ps = con.prepareStatement("SELECT*FROM BLOOD_RECORD");
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				HttpSession session = req.getSession();
				session.setAttribute("session_hopname" , rs.getString("HOSPITAL_NAME"));
				session.setAttribute("session_number", rs.getString("PHONE_NUMBER"));
				session.setAttribute("session_adress", rs.getString("ADRESS"));
				session.setAttribute("session_ap", rs.getString("Apositive"));
				session.setAttribute("session_ap", rs.getString("ABpositive"));
				session.setAttribute("session_bp", rs.getString("Bpositive"));
				session.setAttribute("session_an", rs.getString("Anegative"));
				session.setAttribute("session_abn", rs.getString("ABnegative"));
				session.setAttribute("session_bn", rs.getString("Bnegative"));
				session.setAttribute("session_on", rs.getString("Onegative"));
				session.setAttribute("session_op", rs.getString("Opositive"));
				

				RequestDispatcher rd= req.getRequestDispatcher("/blooddetail.jsp");
				rd.include(req, resp);
			}
		
			
		}
		catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style= 'color:green; text-align:center;'> Exception occurd : "+e.getMessage()+" </h3>");
			RequestDispatcher rd= req.getRequestDispatcher("/blooddetail.jsp");
			rd.include(req, resp);
		}


	}
	
	
	
}
