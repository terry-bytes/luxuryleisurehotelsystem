
package com.mycompany.luxuryleisurehotel.DAOIMPL;

import com.mycompany.luxuryleisurehotel.DAO.AdminDao;
import com.mycompany.luxuryleisurehotel.Models.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;


public class AdminDaoImpl implements AdminDao {

    private Connection con;

    public AdminDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Admin adminLogin(String email, String password) throws LoginException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxuryleisurehotel?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("SELECT * FROM admins WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setSurname(rs.getString("surname"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (admin == null) {
            throw new LoginException();
        }

        return admin;
    }
}



