<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Bookings</title>
</head>
<body>
<h2>Bookings</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Room ID</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Status</th>
        <th>Dining Preference</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.id}</td>
            <td>${booking.userId}</td>
            <td>${booking.roomId}</td>
            <td>${booking.startTime}</td>
            <td>${booking.endTime}</td>
            <td>${booking.status}</td>
            <td>${booking.diningPreference}</td>
            <td>
                <form action="adminDashboard" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="deleteBooking"/>
                    <input type="hidden" name="id" value="${booking.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
