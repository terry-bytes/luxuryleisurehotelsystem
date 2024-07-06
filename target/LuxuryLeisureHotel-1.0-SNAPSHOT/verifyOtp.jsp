<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verify Your Email</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image:  url('images/Hotel St John Island, Singapore by INSIDE MONACO.jpg'); /* Replace 'background.jpg' with your image file path */
            background-size: cover;
            background-position: center;
            color: #333;
            text-align: center;
            padding: 50px;
            margin: 0;
        }
        h2 {
            color: #fff;
            background-color: #333;
            padding: 10px;
            border-radius: 5px;
        }
        form {
            margin-top: 20px;
        }
        input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .error {
            color: #f00;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>Email Verification</h2>
    <form method="post" action="UserServlet">
        <input type="hidden" name="submit" value="verifyOTP">
        <input type="text" name="otp" placeholder="Enter OTP" required>
        <input type="submit" value="Verify">
    </form>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>
</body>
</html>
