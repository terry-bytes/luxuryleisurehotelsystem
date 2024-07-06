/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.DAO.AdminDao;
import com.mycompany.luxuryleisurehotel.Models.Admin;
import javax.security.auth.login.LoginException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Train
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin adminLogin(String email, String password) throws LoginException {
        Admin admin = adminDao.adminLogin(email, password);
        String hash = admin.getPassword();

        if (!BCrypt.checkpw(password, hash)) {
            throw new LoginException("Password incorrect");
        }
        return admin;
    }
}



