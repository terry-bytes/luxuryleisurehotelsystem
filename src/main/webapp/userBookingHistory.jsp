<%@page import="java.util.List"%>
<%@page import="com.mycompany.luxuryleisurehotel.Models.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Booking History</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                background-image: url('images/f15697736.jpg');
                background-size: cover; 
                background-position: center; 
                background-repeat: no-repeat;
                margin: 0;
                padding: 0;
                height: 100vh;
            }

            h2 {
                text-align: center;
                margin-top: 20px;
                color: white; 
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: rgba(255, 255, 255, 0.9);
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
                margin: auto;
                margin-top: 20px; 
            }

            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #f0f0f0; 
                color: #333;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2; 
            }

            .back-link {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: white; 
                text-decoration: none;
                font-weight: bold;
            }

            .back-link:hover {
                text-decoration: underline;
            }

            .no-bookings {
                text-align: center;
                padding: 20px;
                font-style: italic;
                color: #555; 
            }
        </style>
    </head>
    <body>
        <h2>My Booking History</h2>
        <a href="booking.jsp" class="back-link">Back to Dashboard</a>
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
                </tr>
            </thead>
            <tbody>
                <%
                   List<Booking> userBookingHistory = (List<Booking>) request.getAttribute("userBookingHistory");

                 
                    if (userBookingHistory != null && !userBookingHistory.isEmpty()) {
                        for (Booking booking : userBookingHistory) {
                %>
                <tr>
                    <td><%= booking.getId()%></td>
                    <td><%= booking.getUserId()%></td>
                    <td><%= booking.getRoomId()%></td>
                    <td><%= booking.getStartTime()%></td>
                    <td><%= booking.getEndTime()%></td>
                    <td><%= booking.getStatus()%></td>
                    <td><%= booking.getDiningPreference()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="7" class="no-bookings">No booking history found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
