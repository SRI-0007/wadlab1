import java.io.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
                       throws IOException
    {

        String u = req.getParameter("username");
        String p = req.getParameter("password");

        PrintWriter out = res.getWriter();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
            DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/testdb1",
            "root",
            "1234");

            PreparedStatement ps =
            con.prepareStatement(
            "insert into users values(?,?)");

            ps.setString(1,u);
            ps.setString(2,p);

            ps.executeUpdate();

            out.println("Registration Successful");

            con.close();
        }

        catch(Exception e)
        {
            out.println(e);
        }
    }
}
