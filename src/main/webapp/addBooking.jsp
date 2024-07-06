<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Booking</title>
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

        form {
            margin-top: 20px;
            max-width: 500px;
            margin-left: auto;
            margin-right: auto;
        }

        form label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        form input[type="text"], form input[type="datetime-local"], form input[type="number"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        form button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        form button:hover {
            background-color: #0056b3;
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
        h2 {
        color: black;
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
    <h2>Add Booking</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <% if (request.getAttribute("successMessage") != null) { %>
        <div style="color: green;"><%= request.getAttribute("successMessage") %></div>
    <% } %>


    <form action="adminDashboard" method="post">
        <input type="hidden" name="action" value="addBooking">
        
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br><br>
        
        <label for="roomId">Room ID:</label>
        <input type="text" id="roomId" name="roomId" required><br><br>
        
        <label for="startTime">Start Time:</label>
        <input type="datetime-local" id="startTime" name="startTime" required><br><br>
        
        <label for="endTime">End Time:</label>
        <input type="datetime-local" id="endTime" name="endTime" required><br><br>
        
        <label for="status">Status:</label>
        <input type="text" id="status" name="status" required><br><br>
        
        <label for="diningPreference">Dining Preference:</label>
        <input type="text" id="diningPreference" name="diningPreference"><br><br>
        
        <label for="totalAmount">Total Amount:</label>
        <input type="text" id="totalAmount" name="totalAmount" required><br><br>
        
        <button type="submit">Add Booking</button>
    </form>

    <br>
     <a href="adminDashboard?action=viewBookings" class="back-link">Back to Bookings</a>
</div>

<footer>
    <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
</footer>

</body>
</html>
