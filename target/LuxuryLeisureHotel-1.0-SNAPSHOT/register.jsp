<%@page import="com.mycompany.luxuryleisurehotel.Models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register - Luxury Leisure Hotel</title>
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
                align-items: flex-start;
                min-height: calc(100vh - 80px);
                padding-bottom: 60px;
            }


            .form-container {
                background-color: rgba(255, 255, 255, 0.7);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                max-width: 100%;
                width: 300px;
                background-blend-mode: overlay;
                color: #333;
            }


            .register-form label {
                display: block;
                margin-bottom: 5px;
            }

            .register-form input[type="text"],
            .register-form input[type="email"],
            .register-form input[type="password"],
            .register-form input[type="submit"] {
                width: calc(100% - 20px);
                margin-bottom: 12px;
                padding: 10px;
                font-size: 1rem;
                border: 1px solid #ccc;
                border-radius: 5px;
                transition: border-color 0.3s ease;
                background-color: rgba(255, 255, 255, 0.8);
            }

            .register-form input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }

            .register-form input[type="submit"]:hover {
                background-color: #0056b3;
            }


            .error-message {
                color: red;
                margin-top: 10px;
            }


            .login-link {
                margin-top: 10px;
                text-align: center;
            }

            .login-link a {
                color: #007bff;
                text-decoration: none;
            }

            .login-link a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <header>

           <h1><a href="index.html" style="color: white; text-decoration: none;">Welcome to Luxury Leisure Hotel</a></h1>
        </header>

        <div class="main-content">
            <div class="form-container">
                <h2>Register</h2>

                <c:if test="${not empty message}">
                    <p style="color: green;">${message}</p>
                </c:if>
                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>

                <form class="register-form" method="post" action="UserServlet">
                    <input type="hidden" name="submit" value="register">

                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required><br>

                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" name="surname" required><br>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required><br>

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required><br>

                    <input type="submit" value="Register">


                    <p>Already have an account? <a href="login.jsp">Login here</a></p>
                </form>
            </div>
        </div>

        <footer>

            <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
        </footer>

    </body>
</html>
