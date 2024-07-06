<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: black;
            margin: 0;
            background-image: url('images/Forbes Travel.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center; 
        }

        h2 {
            text-align: center;
            color: #007bff;
            margin-bottom: 20px;
        }

        .checkout-details {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
        }

        .checkout-details p {
            margin-bottom: 10px;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="checkout-details">
    <h2>Checkout</h2>

    <p>Room ID: ${roomId}</p>
    <p>Booking Start Time: ${startTime}</p>
    <p>Booking End Time: ${endTime}</p>
    <p>Total Amount: ${totalAmount}</p>

    <form action="PaymentConfirmationServlet" method="post" onsubmit="return confirm('Are you sure you want to confirm payment?');">
        <input type="hidden" name="roomId" value="${roomId}">
        <input type="hidden" name="startTime" value="${startTime}">
        <input type="hidden" name="endTime" value="${endTime}">
        <input type="hidden" name="totalAmount" value="${totalAmount}">

        <input type="submit" value="Confirm Payment">
    </form>
</div>
</body>
</html>
