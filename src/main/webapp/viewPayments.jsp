<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mycompany.luxuryleisurehotel.Models.Payment" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Payments</title>
        <style>

            body {
                font-family: Arial, sans-serif;
                background-image: url('images/Luxury Resort Hotel & Spa.jpg');
                background-size: cover;
                background-position: center;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            .container {
                flex: 1;
                padding: 20px;
            }

            header {
                background-color: rgba(0, 0, 0, 0.7);
                color: white;
                padding: 10px;
                width: 100%;
                text-align: center;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-shrink: 0;
            }

            footer {
                background-color: #333;
                color: white;
                padding: 10px;
                text-align: center;
                margin-top: auto;
                box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.2);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: rgba(255, 255, 255, 0.8); 
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: rgba(242, 242, 242, 0.8); 
            }
            .error {
                background-color: #f8d7da;
                color: #721c24;
                padding: 10px;
                border: 1px solid #f5c6cb;
                margin-bottom: 10px;
            }
            .center {
                text-align: center;
            }

            form button {
                background-color: #dc3545;
                color: white;
                border: none;
                padding: 8px 12px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }
            form button:hover {
                background-color: #c82333;
            }
            .nav-links a {
                color: white; 
                text-decoration: none;
            }
            .nav-links a:hover {
                text-decoration: underline;
            }
            .nav-links a:hover {
                background-color: rgba(0, 0, 0, 0.5);
            }
        </style>
    </head>
    <body>
        <header>
            <div class="nav-links">
                <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
            </div>
        </header>

        <div class="container">
            <c:if test="${not empty errorMessage}">
                <div class="error">
                    ${errorMessage}
                </div>
            </c:if>

            <c:if test="${not empty payments}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Booking ID</th>
                            <th>Amount</th>
                            <th>Payment Time</th>
                            <th>Payment Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="payment" items="${payments}">
                            <tr>
                                <td>${payment.id}</td>
                                <td>${payment.bookingId}</td>
                                <td>${payment.amount}</td>
                                <td>${payment.paymentTime}</td>
                                <td>${payment.paymentStatus}</td>
                                <td>
                                    <form action="adminDashboard" method="POST">
                                        <input type="hidden" name="id" value="${payment.id}">
                                        <input type="hidden" name="action" value="deletePayment">
                                        <button type="submit">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty payments}">
                <p class="center">No payments found.</p>
            </c:if>


            <div class="nav-links">
                <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
            </div>

        </div>

        <footer>
            <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
        </footer>
    </body>
</html>
