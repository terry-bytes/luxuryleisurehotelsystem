/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.Admin;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Train
 */
public interface AdminService {
    Admin adminLogin(String email, String password) throws LoginException;
}
