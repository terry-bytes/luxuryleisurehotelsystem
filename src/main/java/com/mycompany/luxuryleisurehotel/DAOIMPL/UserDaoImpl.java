package com.mycompany.luxuryleisurehotel.DAOIMPL;

import com.mycompany.luxuryleisurehotel.DAO.UserDao;
import com.mycompany.luxuryleisurehotel.Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import org.mindrot.jbcrypt.BCrypt;

public class UserDaoImpl implements UserDao {

    private Connection con;

    public UserDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean addUser(User user) {
        int affectedRows = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("INSERT INTO users (name, surname, email, password, isAdmin) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, false);

            affectedRows = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affectedRows == 1;
    }

    @Override
    public User login(String email, String password) throws LoginException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoginException("Database error occurred");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (user == null) {
            throw new LoginException("Incorrect email or password");
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                }
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root")) {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setSurname(rs.getString("surname"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setAdmin(rs.getBoolean("isAdmin"));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        int affectedRows = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root")) {
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newPassword);
                ps.setString(2, email);
                affectedRows = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affectedRows == 1;
    }

}
