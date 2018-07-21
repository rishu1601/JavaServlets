import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class LoginCookie extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Cookie c[]=req.getCookies();
		String usr=null;
		String pas=null;
		String type="text";
		String name="user";
		String type2="password";
		String name2="pass";
		String type3="checkbox";
		String name3="rem";
		String value1="Remember";
		String type4="submit";
		String rem=null;
		String emp="";
		String name4="login";
		if(c!=null){
			for(int i=0;i<c.length;i++){
				String namer=c[i].getName();
				if(namer.equals("usr")){
					usr=c[i].getValue();
				}
				else if(namer.equals("psw")){
					pas=c[i].getValue();
				}
				else if(namer.equals("rem")){
					rem=c[i].getValue();
				}
			}
			
				out.println("<html><head><title>Login Page</title></head><body bgcolor=cyan><center><h1>Login Page</h1><h2>Enter your Credentials</h2><form action=./login>");
				out.println("<br>Enter Username : <input type="+type+" name="+name+" value= "+usr+">");
				out.println("<br>Enter Password : <input type="+type2+" name="+name2+" value= "+pas+">");
				out.println("<br><input type="+type3+" name="+name3+" value="+value1+">Remember details<br>");
				out.println("<input type=" + type4 + " name="+name4+">");
				out.println("</center></body></html>");
		}
		else
		{
			out.println("<html><head><title>Login Page</title></head><body bgcolor=cyan><center><h1>Login Page</h1><h2>Enter your Credentials</h2><form action=./login>");
			out.println("<br>Enter Username : <input type="+type+" name="+name+" value= "+emp+">");
			out.println("<br>Enter Password : <input type="+type2+" name="+name2+" value= "+emp+">");
			out.println("<br><input type="+type3+" name="+name3+" value="+value1+">Remember details<br>");
			out.println("<input type=" + type4 + " name="+name4+">");
			out.println("</center></body></html>");
		}
	}
}