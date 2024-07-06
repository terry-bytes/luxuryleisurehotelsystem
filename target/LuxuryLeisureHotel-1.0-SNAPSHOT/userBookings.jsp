<%@page import="java.util.List"%>
<%@page import="com.mycompany.luxuryleisurehotel.Models.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Bookings</title>
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

            .cancel-button {
                background-color: #f44336;
                color: white;
                border: none;
                padding: 5px 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                border-radius: 4px;
                cursor: pointer;
            }

            .cancel-button:hover {
                background-color: #d32f2f;
            }
        </style>
    </head>
    <body>
        <h2>User Bookings</h2>
        <a href="booking.jsp" class="back-link">Back to Book accommodation</a>
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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Booking> userBookings = (List<Booking>) request.getAttribute("userBookings");
                    if (userBookings != null && !userBookings.isEmpty()) {
                        for (Booking booking : userBookings) {
                %>
                <tr>
                    <td><%= booking.getId()%></td>
                    <td><%= booking.getUserId()%></td>
                    <td><%= booking.getRoomId()%></td>
                    <td><%= booking.getStartTime()%></td>
                    <td><%= booking.getEndTime()%></td>
                    <td><%= booking.getStatus()%></td>
                    <td><%= booking.getDiningPreference()%></td>
                    <td>
                        <form action="BookingServlet" method="post">
                            <input type="hidden" name="submit" value="Cancel Booking">
                            <input type="hidden" name="bookingId" value="<%= booking.getId()%>">
                            <button type="submit" class="cancel-button">Cancel</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="no-bookings">No bookings found.</td>
                </tr>
                <% }%>
            </tbody>


        </table>
    </body>
</html>
