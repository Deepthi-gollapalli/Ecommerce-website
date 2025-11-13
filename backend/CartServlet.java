import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class CartServlet extends HttpServlet {
    private List<Product> cart = new ArrayList<>();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cart.add(new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getDouble("price"), rs.getString("image_url")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.getWriter().print("{"message":"added"}");
        } else if ("view".equals(action)) {
            String json = new Gson().toJson(cart);
            res.getWriter().print(json);
        }
    }
}
