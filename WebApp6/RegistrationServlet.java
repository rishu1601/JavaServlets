import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class RegistrationServlet extends GenericServlet 
{
	Connection con;
	PreparedStatement ps;
	public void init() throws ServletException 
	{
		try 
		{
			//getting context-param
			ServletContext scon=getServletContext();
			String driver=scon.getInitParameter("driver");
			String url=scon.getInitParameter("url");
			String user=scon.getInitParameter("user");
			String pass=scon.getInitParameter("pass");

			//getting init-param
			String sql=getInitParameter("sql");
			
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pass);
			ps=con.prepareStatement(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException("Initialization failed, Unable to get DB connection");
		}
	}//init

	public void service (ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try 
		{
			//getting request parameters
			String uname=req.getParameter("uname");
			String pass= req.getParameter("pass");
			String repass= req.getParameter("repass");

			if (uname==null||uname.equals("")||pass==null||pass.equals("")||!pass.equals(repass)) 
			{
				out.println("<html><body><center>");
				out.println("<br><i>Given details are not valid to register</i><br>");
				out.println("<br><a href=Home.html>HomePage</a>");
				out.println("</center></body></html>");
				return; 
			}

			String add=req.getParameter("add");
			String phno=req.getParameter("phno");
			String email=req.getParameter("email");
			
			ps.setString(1,uname);
			ps.setString(2,pass);
			ps.setString(3,add);
			ps.setString(4,phno);
			ps.setString(5,email);
			int count=ps.executeUpdate();
			if (count==1||count==Statement.SUCCESS_NO_INFO)
				{
				out.println("<html><body>");
				out.println("<center><h1>Sagar Technology</h1></center>");
				out.println("<table border='1' width='100%' height='100%'>");
				out.println("<tr>");
				out.println("<td width='15%' valign='top' align='center'>");
				out.println("</td>");
				out.println("<td valign='top' align='center'><br/>");
				out.println("<h3>Welcome, "+uname+"</h3><br/>");
				out.println("<h2>To get full access login again</h2>");
				out.println("<br><a href=Home.html>HomePage</a>");
				out.println("</td></tr></table>");
				out.println("</body></html>");
			}
			else
			{
				out.println("<html><body><center>");
				out.println("Given details are incorrect<br/>");
				out.println("<br><i>Please try again later</i>");
				out.println("<br><a href=Home.html>HomePage</a>");
				out.println("</center></body></html>");		
			}
		}
		catch(Exception e)
		{
			out.println("<html><body><center>");
			out.println("<h2>Unable to the process the request try after some time</h2>");
			out.println("<br><a href=Home.html>HomePage</a>");
			out.println("</center></body></html>");
		}
	}
	public void destroy() 
	{
		try 
		{
			ps.close();
			con.close();
		}
		catch(Exception e){}
	}

}
