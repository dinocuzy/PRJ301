/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userDao;

import dao.DBConnection;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private static final String LOGIN = "SELECT id FROM dbo.Users WHERE username = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO dbo.Users (username, email, country, role, status, password, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM dbo.Users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM dbo.Users";
    private static final String DELETE_SQL = "DELETE FROM dbo.Users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE dbo.Users SET username = ?, email = ?, country = ?, role = ?, status = ?, password = ?, dob = ? WHERE id = ?";
    private static final String SEARCH_USERS = "SELECT * FROM dbo.Users WHERE username LIKE ? OR email LIKE ? OR country LIKE ? ";

    @Override
    public void insertUser(User user) throws SQLException {
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.setDate(7, (user.getDob() != null) ? Date.valueOf(user.getDob()) : null);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
                System.out.println("A new user was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(int id) {
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.setDate(7, (user.getDob() != null) ? Date.valueOf(user.getDob()) : null);
            ps.setInt(8, user.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<User> searchUsers(String keyword) {
        List<User> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(SEARCH_USERS)) {

            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("country"),
                rs.getString("role"),
                rs.getBoolean("status"),
                rs.getString("password"),
                (rs.getDate("dob") != null) ? rs.getDate("dob").toLocalDate() : null
        );
    }

    @Override
    public User login(String username, String password) {
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(LOGIN)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return new User(id, username, null, null, null, true, password, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
    