import javax.servlet.*;
import java.io.*;
public class MyServletClass implements Servlet{
  private ServletConfig config;
  public void init(ServletConfig sc){
    config=sc;
    System.out.println("init() called");
  }
  public void service(ServletRequest req,ServletResponse res)throws IOException{
    System.out.println("Service() is called");
    PrintWriter out=res.getWriter();
    res.setContentType("text/html");
    out.println("<html");
    out.println("<body bgcolor=yellow>");
    out.println("<h1>My Servlet App welcomes you</h1>");
    out.println("</body>");
    out.println("</html");
  }
  public void destroy(){
    System.out.println("Destroyed");
  }
  public String getServletInfo(){
    return "MyServletClass";
  }
  public ServletConfig getServletConfig(){
    return config;
  }
}
