
package com.mycompany.luxuryleisurehotel.Models;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Train
 */
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Booking {
    private int id;
    private int userId;
    private int roomId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private String diningPreference;
     private Room room;
    
   double totalAmount;

}
