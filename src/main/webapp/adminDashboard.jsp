<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Add Room</title>
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
            background-repeat: no-repeat; 
            padding: 20px;
            color: #333; 
        }

       
        header {
            background-color: rgba(0, 0, 0, 0.8);
            color: #fff; 
            padding: 10px;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 1; 
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            margin: 0;
            font-size: 1.8rem;
        }

        .header-links {
            margin-right: 20px;
        }

        .header-links a {
            color: #fff;
            text-decoration: none;
            margin-left: 10px;
            font-weight: bold;
            background-color: rgba(255, 255, 255, 0.3);
            padding: 5px 10px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .header-links a:hover {
            background-color: rgba(255, 255, 255, 0.5);
        }

      
        .main-content {
            margin-top: 80px;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            max-width: 800px;
            width: 100%;
        }

     
        footer {
            background-color: rgba(0, 0, 0, 0.8);
            color: #fff;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            z-index: 1;
        }
    </style>
</head>
<body>

<header>
    <h1>Welcome to Admin Dashboard</h1>
    <div class="header-links">
        <a href="RoomServlet">Manage Rooms</a>
        <a href="adminDashboard?action=viewBookings">Manage Bookings</a>
        <a href="adminDashboard?action=viewPayments">Manage Payments</a>
        <a href="index.html">Logout</a>
    </div>
</header>

<div class="main-content">
    <h1>keep track of everything </h1>
</div>

<footer>
    <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
</footer>

</body>
</html>
