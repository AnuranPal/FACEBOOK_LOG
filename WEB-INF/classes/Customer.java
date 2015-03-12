import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Customer extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
	 String MYSQL_USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
      String MYSQL_PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
      String MYSQL_DATABASE_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
      String MYSQL_DATABASE_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	  String MYSQL_DATABASE_NAME = "webapp";
	  Connection con;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String c_mail = request.getParameter("cemail");
		String c_name = request.getParameter("cname");
		String c_addr = request.getParameter("cadd");
		out.println("<body background=Darkseagreen>");
                out.println("<h2>Email- "+c_mail);
                out.println("<br>Name- "+c_name); 
                out.println("<br>Address- "+c_addr);  
		        out.println("<h2>");
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Connection con=DriverManager.getConnection("jdbc:mysql://${OPENSHIFT_MYSQL_DB_HOST}:${OPENSHIFT_MYSQL_DB_PORT}/webapp?"
             // + "user=adminswkCyuv&password=ukxIhPGR4Ugg");
			 String url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + MYSQL_DATABASE_NAME;
             con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into userinfo values('"+c_mail+"','"+c_name+"','"+c_addr+"')");
			if(i>0)
				{   out.println("<br><h2><center>THANK YOU "+c_name+" FOR LOGIN</center></h2>");
					out.println("<br/><a href=\"start.html\"><h1><center>Click here to read the Tutorial.</center></h1></a></body>");} 
                               
			else
				out.print("Sorry for Error");
		}
		catch(Exception e)
		{
			out.print("Exception : "+e);
		}
	}
}