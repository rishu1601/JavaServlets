import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class LoginAccess extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Cookie usC=null;
		Cookie psC=null;
		Cookie cb=null;
		
		try
		{
			String remem=req.getParameter("rem");
			String usr=req.getParameter("user");
			String psw=req.getParameter("pass");
			if(usr==null||usr.equals("")||psw==null||psw.equals(""))
			{
				out.println("<html><body bgcolor=cyan><center><h1>Don't Enter empty Strings</h1></center></body></html>");
				return;
			}
			System.out.println(remem);
			if(remem.equals("Remember"))
			{
				usC=new Cookie("usr",usr);
				psC=new Cookie("psw",psw);
				cb=new Cookie("rem",remem);
				cb.setMaxAge(86400);
				usC.setMaxAge(86400);
				psC.setMaxAge(86400);
				res.addCookie(usC);
				res.addCookie(psC);
				res.addCookie(cb);
				out.println("<html><body bgcolor=cyan><center>");
				out.println("<h2>Welcome "+usr+"</h2>");
				out.println("<br><a href=./set>Go back</a>");
				out.println("<h3>Cookies Set</h3>");
				out.println("</center></body></html>");
			}
		}
		catch(Exception e)
		{
			String remem=req.getParameter("rem");
			String usr=req.getParameter("user");
			String psw=req.getParameter("pass");
			System.out.println(remem);
			if(remem==null){
				usC=new Cookie("usr","");
				psC=new Cookie("psw","");
				cb=new Cookie("rem","");
				cb.setMaxAge(0);
				usC.setMaxAge(0);
				psC.setMaxAge(0);
				res.addCookie(usC);
				res.addCookie(psC);
				res.addCookie(cb);
				out.println("<html><body bgcolor=cyan><center>");
				out.println("<h2>Welcome "+usr+"</h2>");
				out.println("<h3>Cookies Not Set</h3>");
				out.println("<br><a href=./set>Go back</a>");
				out.println("</center></body></html>");	
			}
			else
			{
				out.println("<html><body bgcolor=cyan><center>");
				//out.println("<h2>Welcome "+usr+"</h2>");
				out.println("<h3>Login Failed</h3>");
				out.println("<br><a href=home.html>Go back</a>");
				out.println("</center></body></html>");
			}
		}
	}
}