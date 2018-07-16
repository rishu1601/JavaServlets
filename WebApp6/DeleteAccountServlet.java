import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class DeleteAccountServlet extends GenericServlet
{
	Connection con;
	PreparedStatement ps;
	public void init() throws ServletException
	{
		try{
			ServletContext scon=getServletContext();
			String driver=scon.getInitParameter("driver");
			String url=scon.getInitParameter("url");	
			String user=scon.getInitParameter("user");
			String pass=scon.getInitParameter("pass");
			String sql=getInitParameter("sql");
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pass);
			ps=con.prepareStatement(sql);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new ServletException("Initialization failed, Unable to get DB connection");
		}
	}
	public void service (ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		try
		{
			String uname=req.getAttribute("uname");
			String pass1=req.getAttribute("pass");
			String pass2=req.getParameter("pass");
			if (pass2==null||pass2.equals("")||!pass2.equals(req.getParameter("repass"))||pass1.equals(pass)) 
			{
				out.println("<html><body bgcolor=cyan><center>");
				out.println("<br><i>Passwords did not match or Password can not be empty</i>");
				out.println("<br><pre><a href=Login.html>Home Page</a></pre>");
				out.println("</center></body></html>");
				return;
			}
			ps.setString(1,uname);
			ps.setString(2,pass1);
			int cn=ps.executeUpdate();
			if(cn==1||cn==Statement.SUCCESS_NO_INFO)
			{
				out.println("<html><body bgcolor=red>");
				out.println("<center><h1>Sagar Technology</h1></center>");
				out.println("<table border='1' width='100%' height='100%'>");
				out.println("<tr>");
				out.println("<td width='15%' valign='top' align='center'>");
				//out.println("<br/><a href='Register.html'>Register</a><br/>");
				out.println("</td>");
				out.println("<td valign='top' align='center'><br/>");
				out.println("<h3>The account with username : "+uname+" has been deleted</h3><br/>");
				out.println("<h2>You can go back to homepage</h2>");
				out.println("<br/><a href='Home.html'>Homepage</a><br/>");
				out.println("</td></tr></table>");
				out.println("</body></html>");
			}
			else
			{
				out.println("<html><body bgcolor=red><center>");
				out.println("Given details are incorrect<br/>");
				out.println("<br><i>Please try again later</i>");
				out.println("<br/><a href='Home.html'>homepage</a><br/>");
				out.println("</center></body></html>");	

			}
		}
		catch(SQLException e)
		{
			out.println("<html><body><center>");
			System.out.println(e);
			out.println("<h2>SQL Exception</h2>");
			out.println("<br><a href='Home.html'><pre>HomePage</a></pre><br>");
			out.println("</center></body></html>");
		}
		catch(Exception e)
		{
			out.println("<html><body><center>");
			System.out.println(e);
			out.println("<h2>Unable to the process the request try after some time</h2>");
			out.println("<br/><a href='Home.html'>homepage</a><br/>");
			out.println("</center></body></html>");
		}
	}
}