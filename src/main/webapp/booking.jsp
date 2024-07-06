<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.luxuryleisurehotel.Models.Room" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking</title>
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
                background-color: black;
                margin: 0;
                background-image: url('images/Forbes Travel.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                height: 100vh;
                display: flex;
                flex-direction: column;
            }


            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                margin: auto;
                background-color: rgba(255, 255, 255, 0.7);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                width: 400px;
                max-width: 100%;
            }


            form {
                display: flex;
                flex-direction: column;
                width: 100%;
            }

            label {
                margin-bottom: 8px;
            }

            select, input[type="datetime-local"], input[type="submit"] {
                margin-bottom: 12px;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 5px;
                outline: none;
                transition: border-color 0.3s ease-in-out;
                width: 100%;
            }

            input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }


            .message, .error {
                margin-top: 10px;
                padding: 10px;
                border-radius: 5px;
            }

            .message {
                background-color: lightgreen;
            }

            .error {
                background-color: lightcoral;
            }


            .header {
                background-color: rgba(0, 123, 255, 0.7);
                color: #fff;
                padding: 10px;
                width: 100%;
                text-align: center;
                position: sticky;
                top: 0;
                z-index: 1000;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .header h2 {
                margin: 0;
            }

            .header .links {
                display: flex;
            }

            .header .links a {
                margin-right: 10px;
                color: #fff;
                text-decoration: none;
                font-weight: bold;
            }

            .header .links a:hover {
                text-decoration: underline;
            }


            .footer {
                text-align: center;
                margin-top: auto;
                padding: 10px 0;
                font-size: 14px;
                color: #555;
                background-color: rgba(0, 123, 255, 0.7);
                box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.2);
            }
        </style>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const startTimeInput = document.getElementById('startTime');
                const endTimeInput = document.getElementById('endTime');
                const form = document.querySelector('form[name="bookingForm"]');
                const today = new Date().toISOString().split('T')[0];

                startTimeInput.min = today;
                endTimeInput.min = today;

                startTimeInput.addEventListener('change', function () {
                    if (new Date(this.value) < new Date()) {
                        alert('Check-in date cannot be in the past.');
                        this.value = '';
                    }
                    endTimeInput.min = this.value;
                });

                endTimeInput.addEventListener('change', function () {
                    if (new Date(this.value) <= new Date(startTimeInput.value)) {
                        alert('Check-out date must be after the check-in date.');
                        this.value = '';
                    }
                });

                form.addEventListener('submit', function (event) {
                    if (!isValidInterval(startTimeInput.value, endTimeInput.value)) {
                        alert('The interval between check-in and check-out must be at least 60 minutes.');
                        event.preventDefault();
                    }

                    const selectedRate = parseFloat(document.getElementById('roomId').selectedOptions[0].dataset.rate);
                    const lowestRate = 140;

                    if (selectedRate < lowestRate) {
                        alert(`The selected room rate is less than the minimum allowable rate (${lowestRate}). Please select a different room or rate.`);
                        event.preventDefault();
                    }
                });
            });

            function isValidInterval(startTime, endTime) {
                const start = new Date(startTime);
                const end = new Date(endTime);
                const intervalInMinutes = (end - start) / (1000 * 60);

                return intervalInMinutes >= 60;
            }
        </script>
    </head>
    <body>
        <div class="header">
            <h2>Book Accommodation</h2>
            <div class="links">
                <a href="BookingServlet?submit=Get Available Rooms">View Available Rooms</a>
                <a href="BookingServlet?submit=Get My Bookings">View My Upcoming Bookings</a>
                <a href="BookingServlet?submit=Get Booking History">View Booking History</a>
                <a href="gallery.jsp">Room Gallery</a>
                <a href="index.html">Logout</a>
            </div>
        </div>

        <div class="container">
            <form name="bookingForm" action="BookingServlet" method="POST">
                <label for="roomId">Room Type:</label>
                <select name="roomId" id="roomId" required>
                    <c:forEach var="room" items="${availableRooms}">
                        <option value="${room.id}" data-rate="${room.rate}">
                            ${room.roomType} - ${room.rate}
                        </option>
                    </c:forEach>
                </select>

                <label for="startTime">Check-in Date:</label>
                <input type="datetime-local" id="startTime" name="startTime" required>

                <label for="endTime">Check-out Date:</label>
                <input type="datetime-local" id="endTime" name="endTime" required>

                <label for="diningPreference">Dining Preference:</label>
                <select name="diningPreference" id="diningPreference">
                    <option value="Vegetarian">Vegetarian</option>
                    <option value="Vegan">Vegan</option>
                    <option value="Gluten-free">Gluten-free</option>
                    <option value="Nut-free">Nut-free</option>
                    <option value="No Preference">No Preference</option>
                </select>

                <input type="submit" name="submit" value="Book Now">
            </form>

            <c:if test="${not empty message}">
                <div class="message">
                    ${message}
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
        </div>

        <div class="footer">
            &copy; 2024 Luxury Leisure Hotel. All rights reserved.
        </div>
    </body>
</html>
