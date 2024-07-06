/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.User;
import java.sql.Timestamp;
import javax.security.auth.login.LoginException;

public interface UserService {

    String register(User user);

    User login(String email, String password) throws LoginException;

    void sendRegistrationEmail(User user);

    void notifyAdminOnRegistration(User user);

    String initiatePasswordReset(String email);

    void resetPassword(String email, String newPassword);

}
