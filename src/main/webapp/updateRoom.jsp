<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Room</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
        .form-actions {
            text-align: center;
        }
        button {
            padding: 10px 20px;
            background-color: #5cb85c;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 4px;
        }
        button:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Room</h2>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
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
    </div>
</body>
</html>
