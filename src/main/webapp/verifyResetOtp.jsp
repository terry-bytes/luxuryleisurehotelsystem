<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify OTP for Password Reset</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            display: flex;
            flex-direction: column;
            justify-content: center;
            min-height: 100vh;
            background-color: #fff;
            margin: 0;
            background-image: url('images/Hotel St John Island, Singapore by INSIDE MONACO.jpg'); 
            background-size: cover; 
            background-position: center; 
        }

        header {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        footer {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .main-content {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-grow: 1;
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.7);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1); 
            width: 400px;
            margin: 20px;
        }

        .reset-form input[type="text"],
        .reset-form input[type="password"],
        .reset-form button[type="submit"] {
            width: calc(100% - 20px);
            margin-bottom: 15px;
            padding: 12px;
            font-size: 1.2rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s ease;
            background-color: rgba(255, 255, 255, 0.8);
        }

        .reset-form button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .reset-form button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
    <script>
        function confirmPasswordReset(event) {
            var newPassword = document.getElementById('newPassword').value;
            var confirmation = confirm('Are you sure you want to reset your password to: ' + newPassword + '?');
            if (!confirmation) {
                event.preventDefault(); // Prevents form submission
            }
        }
    </script>
</head>
<body>

<header>
    <h1><a href="index.html" style="color: white; text-decoration: none;">Welcome to Luxury Leisure Hotel</a></h1>
</header>

<div class="main-content">
    <div class="form-container">
        <h1>Verify OTP for Password Reset</h1>
        <form class="reset-form" action="UserServlet" method="post" onsubmit="confirmPasswordReset(event)">
            <input type="hidden" name="submit" value="verifyResetOTP">
            <label for="otp">OTP:</label><br>
            <input type="text" id="otp" name="otp" required><br><br>
            <label for="newPassword">New Password:</label><br>
            <input type="password" id="newPassword" name="newPassword" required><br><br>
            <button type="submit">Reset Password</button>
        </form>
        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>
    </div>
</div>

<footer>
    <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
</footer>

</body>
</html>
