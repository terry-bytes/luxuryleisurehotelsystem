
package com.mycompany.luxuryleisurehotel.Models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Room {
    private int id;
    private String roomType;
    private double rate;
    private int maxOccupancy;

}
