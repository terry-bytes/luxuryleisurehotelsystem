/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luxuryleisurehotel.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private String sender;
    private String receiver;
    private String message;
    private String password;
    private String subject;

    
    public Email(String receiver, String message, String subject) {
    this.receiver = receiver;
    this.message = message;
    this.subject = subject;
}
 public Email(String sender, String password) {
        this.sender = sender;
        this.password = password;
    }    


}
