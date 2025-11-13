import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getDouble("price"), rs.getString("image_url")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = new Gson().toJson(list);
        res.getWriter().print(json);
    }
}
