<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.luxuryleisurehotel.Models.Booking" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Bookings</title>
    <style>
     
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

     
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-image: url('images/Luxury Resort Hotel & Spa.jpg');
            background-size: cover; 
            background-position: center; 
            overflow-x: hidden;
            display: flex;
            min-height: 100vh;
            flex-direction: column;
        }

      
        header {
            background-color:rgba(0, 0, 0, 0.7);
            color: #fff;
            padding: 10px; 
            width: 100%;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-shrink: 0;
        }

        header h1 {
            font-size: 1.8rem;
            margin: 0;
        }

     
        .nav-links a {
            color: #fff;
            text-decoration: none;
            margin-left: 10px; 
            font-weight: bold;
            background-color: rgba(0, 0, 0, 0.3);
            padding: 5px 8px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .nav-links a:hover {
            background-color: rgba(0, 0, 0, 0.5);
        }

       
        .main-content {
            flex: 1;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9); 
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 80%; 
            margin: auto;
            z-index: 1; 
        }

        .main-content h2 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: #007bff; 
            text-align: center;
        }

       
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

      
        form {
            display: inline-block;
        }

        .delete-button {
            background-color: #dc3545; 
            color: #fff;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .delete-button:hover {
            background-color: #c82333; 
        }

    
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }

       
        footer {
            background-color: rgba(0, 0, 0, 0.7);
            color: #fff;
            padding: 10px 0;
            text-align: center;
            width: 100%;
            flex-shrink: 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>

<header>
    <div class="nav-links">
        <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
        
    </div>
</header>

<div class="main-content">
    <h2>All Bookings</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <% if (request.getAttribute("successMessage") != null) { %>
        <div style="color: green;"><%= request.getAttribute("successMessage") %></div>
    <% } %>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Room ID</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Status</th>
            <th>Dining Preference</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% 
            List<Booking> allBookings = (List<Booking>) request.getAttribute("allBookings");
            if (allBookings != null && !allBookings.isEmpty()) {
                for (Booking booking : allBookings) {
        %>
        <tr>
            <td><%= booking.getId() %></td>
            <td><%= booking.getUserId() %></td>
            <td><%= booking.getRoomId() %></td>
            <td><%= booking.getStartTime() %></td>
            <td><%= booking.getEndTime() %></td>
            <td><%= booking.getStatus() %></td>
            <td><%= booking.getDiningPreference() %></td>
            <td>
                <form action="adminDashboard" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="deleteBooking">
                    <input type="hidden" name="id" value="<%= booking.getId() %>">
                    <button type="submit" class="delete-button" onclick="return confirm('Are you sure you want to delete this booking?');">Delete</button>
                </form>
            </td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="8">No bookings found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <br/>
    <a href="adminDashboard.jsp" class="back-link">Back to Admin Dashboard</a>
</div>

<footer>
    <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
</footer>

</body>
</html>
