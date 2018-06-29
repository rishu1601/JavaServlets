import javax.servlet.*;
import java.io.*;
public class ClientInfoServlet extends GenericServlet
{
  public void service(ServletRequest req,ServletResponse res)throws IOException,ServletException
  {
    String pro=req.getProtocol();
    String ip=req.getRemoteAddr();
    String hn=req.getRemoteHost();
    String sn=req.getServerName();
    int po=req.getServerPort();
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
    out.println("<html>");
    out.println("<head><title>Response Page</title></head>");
    out.println("<body bgcolor=black>");
    out.println("<center><h2><font color=white>");
    out.println("<br><br>The protocol used is : "+pro);
    out.println("<br><br>The ip address of the client is : "+ip);
    out.println("<br><br>The hostname is : "+hn);
    out.println("<br><br>The server name is : "+sn);
    out.println("<br><br>The port being used is : "+po);
    out.println("<br><a href=Client.html>Home Page</a>");
    out.println("</font></h2></center>");
    out.println("</body>\n</html>");
  }
}
