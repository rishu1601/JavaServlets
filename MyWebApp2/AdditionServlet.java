import javax.servlet.*;
import java.io.*;
public class AdditionServlet extends GenericServlet
{
  public void service(ServletRequest req,ServletResponse res)throws IOException,ServletException
  {
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
    out.println("<html>");
    out.println("<head><title>Response Page</title></head>");
    out.println("<body bgcolor=blue>");
    try{
      double a=Double.parseDouble(req.getParameter("t1"));
      double b=Double.parseDouble(req.getParameter("t2"));
      double c=a+b;
      out.println("<center>");
      out.println("The addition of "+a+" and "+b+" is :"+c);
      out.println("<br><br><a href=Math.html>Enter Again</a>");
      out.println("</center>");
    }
    catch(NumberFormatException e){
          out.println("<h2>Enter numbers only</h2>");
          out.println("<br><br><a href=Math.html>Enter Again</a>");
      }
      out.println("</body>\n</html>");
  }
}
