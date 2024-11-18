import java.sql.*;

public class OrderDAO {

    // Place a new order
    public boolean placeOrder(int userId, double totalAmount) {
        String query = "INSERT INTO Orders (user_id, total_amount, order_status, payment_status) VALUES (?, ?, 'Pending', 'Pending')";

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get orders by user ID
    public ResultSet getOrdersByUserId(int userId) {
        String query = "SELECT * FROM Orders WHERE user_id = ?";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update order status
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String query = "UPDATE Orders SET order_status = ? WHERE order_id = ?";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
