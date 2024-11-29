import java.sql.*;

public class UserDAO {

    // Add a new user
    public boolean addUser(String name, String email, String passwordHash, String phoneNumber, String address) {
        String query = "INSERT INTO Users (name, email, password_hash, phone_number, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, passwordHash);
            ps.setString(4, phoneNumber);
            ps.setString(5, address);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
  // Get user by ID
    public User getUserById(int userId) {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        User user = null;

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Update user's address
    public boolean updateUserAddress(int userId, String newAddress) {
        String query = "UPDATE Users SET address = ? WHERE user_id = ?";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, newAddress);
            ps.setInt(2, userId);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
