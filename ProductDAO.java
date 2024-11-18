import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    // Method to insert a new product into the Products table
    public boolean insertProduct(String name, String description, double basePrice, int stockQuantity, String imageUrl) {
        String insertProductSQL = "INSERT INTO Products (name, description, base_price, stock_quantity, image_url) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); // Get database connection
             PreparedStatement ps = conn.prepareStatement(insertProductSQL)) {

            // Set parameters for the query
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, basePrice);
            ps.setInt(4, stockQuantity);
            ps.setString(5, imageUrl);

            // Execute the query
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }
}
