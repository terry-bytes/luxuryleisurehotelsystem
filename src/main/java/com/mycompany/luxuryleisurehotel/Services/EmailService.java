/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.Models.Email;

public interface EmailService {
    void sendMail(Email email);
     void sendPasswordResetMail(String email, String otp);
}
