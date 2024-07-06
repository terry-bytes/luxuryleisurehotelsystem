<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>All Rooms</title>
        <style>

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }


            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                background-color: #f2f2f2;
                padding: 20px;
                color: #333; 
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
                color: black; 
            }


            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
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


            form {
                max-width: 500px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
                margin-bottom: 20px;
            }

            form label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
            }

            form input[type="text"],
            form input[type="number"],
            form select {
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
            }

            form input[type="submit"] {
                background-color: #007bff; 
                color: #fff;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            form input[type="submit"]:hover {
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
        </style>
  
    </head>
    <body>


        <div class="main-content">
            <header>
                <div class="nav-links">
                    <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
                </div>
            </header>

            <h2>All Rooms</h2>

            <c:if test="${not empty successMessage}">
                <p class="success-message">${successMessage}</p>
            </c:if>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Room Type</th>
                        <th>Rate</th>
                        <th>Max Occupancy</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            <td>${room.id}</td>
                            <td>${room.roomType}</td>
                            <td>${room.rate}</td>
                            <td>${room.maxOccupancy}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h2>Add Room</h2>
            <form action="adminDashboard" method="post">
                <input type="hidden" name="action" value="addRoom"/>

                <label for="roomType">Room Type:</label>
                <input type="text" id="roomType" name="roomType" required/>

                <label for="rate">Rate:</label>
                <input type="number" id="rate" name="rate" required step="0.01"/>

                <label for="maxOccupancy">Max Occupancy:</label>
                <input type="number" id="maxOccupancy" name="maxOccupancy" required/>

                <input type="submit" value="Add Room"/>
            </form>

            <h2>Delete Room</h2>
            <form action="adminDashboard" method="post">
                <input type="hidden" name="action" value="deleteRoom"/>

                <label for="roomId">Select Room to Delete:</label>
                <select id="roomId" name="id" required>
                    <c:forEach var="room" items="${rooms}">
                        <option value="${room.id}">${room.roomType}</option>
                    </c:forEach>
                </select>

                <br><br>
                <input type="submit" value="Delete Room"/>
            </form>
            <h2>Update Room</h2>
            <c:if test="${not empty successMessage}">
                <script>
        alert("${successMessage}");
                </script>
            </c:if>

            <c:if test="${not empty errorMessage}">
                <script>
        alert("${errorMessage}");
                </script>
            </c:if>
            <form action="${pageContext.request.contextPath}/adminDashboard?action=updateRoom" method="post">
                <div class="form-group">
                    <label for="id">Room ID</label>
                    <input type="text" id="id" name="id" value="${param.id}" required>
                </div>
                <div class="form-group">
                    <label for="roomType">Room Type</label>
                    <input type="text" id="roomType" name="roomType" value="${param.roomType}" required>
                </div>
                <div class="form-group">
                    <label for="rate">Rate</label>
                    <input type="number" id="rate" name="rate" step="0.01" value="${param.rate}" required>
                </div>
                <div class="form-group">
                    <label for="maxOccupancy">Max Occupancy</label>
                    <input type="number" id="maxOccupancy" name="maxOccupancy" value="${param.maxOccupancy}" required>
                </div>
                <div class="form-actions">
                    <button type="submit">Update Room</button>
                </div>
            </form>
            <a href="adminDashboard.jsp" class="back-link">Back to Admin Dashboard</a>
        </div>

        <footer>
            <p>&copy; 2024 Luxury Leisure Hotel. All rights reserved.</p>
        </footer>
    </body>
</html>
