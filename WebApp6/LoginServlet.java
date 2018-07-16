import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class LoginServlet extends GenericServlet 
{
	Connection con;
	PreparedStatement ps,ps2;
	public void init() throws ServletException 
	{
		try 
		{
			ServletContext scon=getServletContext();
			//geting context init parameters from web.xml
			String driver=scon.getInitParameter("driver");
			String url=scon.getInitParameter("url");	
			String user=scon.getInitParameter("user");
			String pass=scon.getInitParameter("pass");
			String sql2="select * from userdetails where uname=? and pass=?";
			//geting init parameters from web.xml
			String sql=getInitParameter("sql");

			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pass);
			ps=con.prepareStatement(sql);
			ps2=con.prepareStatement(sql2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException("Initialization failed, Unable to get DB connection");
		}
	}

	public void service (ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try 
		{
			//geting request parameters from Login.html
			String uname=req.getParameter("uname");
			String pass= req.getParameter("pass");
			if (uname==null||uname.equals("")||pass==null||pass.equals("")) 
			{
				out.println("<html><body><center>");
				out.println("<br><i>User Name and Password can not be empty</i>");
				out.println("<br><pre><a href=Login.html>Login Page</a></pre>");
				out.println("</center></body></html>");
				return;
			}

			ps.setString(1,uname);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();

			if (rs.next())
			{
				ps2.setString(1,uname);
				ps2.setString(2,pass);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next())
				{
					String addr=rs2.getString(3);
					String phno=rs2.getString(4);
					String email=rs2.getString(5);
					req.setAttribute("uname",uname);
					req.setAttribute("pass",pass);
					RequestDispatcher rd=req.getRequestDispatcher("login");
					rd.forward(req,res);
					out.println("<html><body>");
					out.println("<center><h1>Sagar Technology</h1></center>");
					out.println("<table border='1' width='100%' height='100%'>");
					out.println("<tr>");
					out.println("<td width='15%' valign='top' align='center'>");
					out.println("<br><a href='Modify.html'><pre>Modify Data</a></pre><br>");
					out.println("<br><a href='Delete.html'><pre>Delete Account</pre></a><br>");
					out.println("<br><a href=Home.html><pre>Logout</pre></a><br>");
					out.println("</td>");
					out.println("<td valign='top' align='center'><br/>");
					out.println("<h3>Welcome, "+uname+"</h3><br/>");
					out.println("<h2>Enjoy browsing our Site</h2>");
					out.println("<br><h3>Here are your current details<h3>");
					out.println("<br><pre>Address  : "+addr+"</pre>");
					out.println("<br><pre>Phone No.: "+phno+"</pre>");
					out.println("<br><pre>Email   :  "+email+"</pre>");
					out.println("</td></tr></table>");
					out.println("</body></html>");
				}
			}
			else
			{
				out.println("<html><body bgcolor=RED><center>");
				out.println("<br>Given username and password are incorrect<br>");
				out.println("<br><a href='Home.html'><pre>HomePage</a></pre><br>");
				out.println("</center></body></html>");				
			}
		}
		catch(SQLException e)
		{
			out.println("<html><body><center>");
			out.println("<h2>SQL Exception</h2>");
			out.println("<br><a href='Home.html'><pre>HomePageme</a></pre><br>");
			out.println("</center></body></html>");
		}
		catch(Exception e)
		{
			out.println("<html><body><center>");
			out.println("<h2>Unable to the process the request try after some time</h2>");
			out.println("<br><a href='Home.html'><pre>HomePage</a></pre><br>");
			out.println("</center></body></html>");
		}
	}//service

	public void destroy () 
	{
		try 
		{
			con.close();
			ps.close();
		}
		catch(Exception e){}
	}//destroy

}//class
