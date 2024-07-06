package com.mycompany.luxuryleisurehotel.DAO;

import com.mycompany.luxuryleisurehotel.Models.User;
import javax.security.auth.login.LoginException;

public interface UserDao {

    boolean addUser(User user);

    User login(String email, String password) throws LoginException;

    User getUserById(int userId);

    User getUserByEmail(String email);  

    boolean updatePassword(String email, String newPassword);
}
