<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Details</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Room Details</h2>

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
</body>
</html>
