<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Room</title>
</head>
<body>
    <h2>Delete Room</h2>
    
  
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    
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
    
    <br>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
