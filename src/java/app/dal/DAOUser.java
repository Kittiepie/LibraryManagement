package app.dal;

import java.util.ArrayList;
import java.util.List;
import app.entity.User;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DAOUser extends DBContext {

    public boolean updatePasswordById(int id, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update [User] set [password] = ? where [id] = ?");
            stmt.setString(1, password);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    //==================================
    public void updatePassByUser(String user, String pass) {
        String sql = "UPDATE [User]\n"
                + "   SET password = ?\n"
                + " WHERE email = ?";
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, pass);
            pre.setString(2, user);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUser(User user) {
        String sql = "INSERT INTO [dbo].[User] ([email], [password], [role_id], [full_name], [gender_id], [mobile], [profile_picture], [is_active]) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getEmail());
            pre.setString(2, user.getPassword());
            pre.setInt(3, user.getRoleId());
            pre.setString(4, user.getFullName());
            pre.setInt(5, user.getGenderId());
            pre.setString(6, user.getMobile());
            pre.setString(7, "public/images/anonymous-user.webp");
            pre.setBoolean(8, true);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "SQL Error: " + ex.getMessage(), ex);
        }
    }

    public boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        String sql = "SELECT COUNT(*) FROM [dbo].[User] WHERE email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                isRegistered = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    public boolean isMobileRegistered(String mobile) {
        boolean isRegistered = false;
        String sql = "SELECT COUNT(*) FROM [dbo].[User] WHERE mobile = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, mobile);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                isRegistered = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    private Vector<User> getFull(String sql) {
        Vector<User> Out = new Vector<User>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Out.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return Out;
    }

    public Vector<User> getAll() {
        return getFull("select * from [User]");
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM [User] where id = '" + id + "';";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM [User] where Email = ?";
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            preStat.setString(1, email);
            ResultSet rs = preStat.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public byte[] getProfileImage(int id) {
        String sql = "SELECT * FROM ProfilePicture where UserId = '" + id + "';";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                return rs.getBytes(2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int insertProfileImage(int id, byte[] image) {
        String sql = "INSERT INTO ProfilePicture (UserId, Image)\n"
                + "     VALUES (?,?)";
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            preStat.setInt(1, id);
            preStat.setBytes(2, image);
            return preStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateProfileImage(int id, byte[] image) {
        if (getProfileImage(id) == null) {
            return insertProfileImage(id, image);
        } else {
            String sql = "UPDATE ProfilePicture\n"
                    + " SET Image = ?"
                    + " WHERE UserId = ?;";
            try {
                PreparedStatement preStat = connection.prepareStatement(sql);
                preStat.setInt(2, id);
                preStat.setBytes(1, image);
                return preStat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public int updateUserProfile(int id, String fullName, int genderId, String mobile) {
        String sql = "UPDATE [User]\n"
                + "   SET full_name = ?\n"
                + "      ,gender_id = ?\n"
                + "      ,mobile = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            preStat.setInt(4, id);
            preStat.setString(1, fullName);
            preStat.setInt(2, genderId);
            preStat.setString(3, mobile);
            return preStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // to be replaced with better ways immediately after showcase
    public String idToName(int id) {
        String sql = "SELECT full_name FROM [User] where id = '" + id + "';";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getRoleUser(String email, String pass) {
        String sql = "SELECT role_id FROM [User] "
                + "WHERE Email = ? AND password = ? ";
        int role = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getInt("roleId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }

    public List<User> getUsers(int offset, int noOfRecords) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM [User] Where role_id = 1 ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id"),
                            rs.getString("full_name"),
                            rs.getInt("gender_id"),
                            rs.getString("mobile"),
                            rs.getString("profile_picture"),
                            rs.getBoolean("is_active"),
                            rs.getInt("borrowcard_id")
                    );
                    userList.add(user);
                }
            }

            // Calculate total number of records
            String countSql = "SELECT COUNT(*) FROM [User]";
            try (PreparedStatement countPs = connection.prepareStatement(countSql); ResultSet countRs = countPs.executeQuery()) {
                if (countRs.next()) {
                    this.noOfRecords = countRs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<User> getStaffByRole(int roleId, int offset, int noOfRecords) {
        List<User> staffList = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE role_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, offset);
            ps.setInt(3, noOfRecords);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id"),
                            rs.getString("full_name"),
                            rs.getInt("gender_id"),
                            rs.getString("mobile"),
                            rs.getString("profile_picture"),
                            rs.getBoolean("is_active")
                    );
                    staffList.add(user);
                }
            }

            // Calculate total number of records for the given role
            String countSql = "SELECT COUNT(*) FROM [User] WHERE role_id = ?";
            try (PreparedStatement countPs = connection.prepareStatement(countSql)) {
                countPs.setInt(1, roleId);
                try (ResultSet countRs = countPs.executeQuery()) {
                    if (countRs.next()) {
                        this.noOfRecords = countRs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public int getNoOfRecordsByRole(int roleId) {
        return noOfRecords;
    }

    public boolean updateUserRole(int userId, int roleId) {
        String sql = "UPDATE [User] SET role_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateBorrowCardId(int userId, int borrowCardId) {
        String sql = "UPDATE [User] SET borrowcard_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCardId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
