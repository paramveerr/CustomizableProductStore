import java.sql.*;

public class AdminDAO {
  // Add a new admin
    public boolean addAdmin(String name, String email, String passwordHash) {
        String query = "INSERT INTO Admins (name, email, password_hash) VALUES (?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, passwordHash);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get admin by ID
    public Admin getAdminById(int adminId) {
        String query = "SELECT * FROM Admins WHERE admin_id = ?";
        Admin admin = null;

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                admin = new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
