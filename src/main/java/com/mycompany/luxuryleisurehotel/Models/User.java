package com.mycompany.luxuryleisurehotel.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isAdmin;


}
