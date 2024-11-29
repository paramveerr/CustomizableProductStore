import java.sql.*;

public class OrderItemDAO {
   // Add an item to an order
    public boolean addOrderItem(int orderId, int productId, int customizationId, int quantity, double price) {
        String query = "INSERT INTO OrderItems (order_id, product_id, customization_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, customizationId);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get order items by order ID
    public ResultSet getOrderItemsByOrderId(int orderId) {
        String query = "SELECT * FROM OrderItems WHERE order_id = ?";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, orderId);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
