<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.luxuryleisurehotel.Models.Room" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Rooms</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .back-link {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
            border-radius: 5px;
        }
        .no-rooms {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Available Rooms</h2>

    <table>
        <tr>
            <th>Room ID</th>
            <th>Room Type</th>
            <th>Rate</th>
            <th>Max Occupancy</th>
        </tr>
        <% 
            List<Room> availableRooms = (List<Room>) request.getAttribute("availableRooms");
            if (availableRooms != null && !availableRooms.isEmpty()) {
                for (Room room : availableRooms) {
        %>
        <tr>
            <td><%= room.getId() %></td>
            <td><%= room.getRoomType() %></td>
            <td><%= room.getRate() %></td>
            <td><%= room.getMaxOccupancy() %></td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="4" class="no-rooms">No rooms available at the moment.</td>
        </tr>
        <% } %>
    </table>

    <a href="booking.jsp" class="back-link">Back to Bookings</a>
</body>
</html>
