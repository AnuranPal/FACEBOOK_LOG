import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Customer extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String c_mail = request.getParameter("cemail");
		String c_name = request.getParameter("cname");
		String c_addr = request.getParameter("cadd");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/user?"
              + "user=root&password=system");
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into userinfo values('"+c_mail+"','"+c_name+"','"+c_addr+"')");
			if(i>0)
				{response.sendRedirect("start.html");} 
                               
			else
				out.print("Sorry for Error");
		}
		catch(Exception e)
		{
			out.print("Exception : "+e);
		}
	}
}