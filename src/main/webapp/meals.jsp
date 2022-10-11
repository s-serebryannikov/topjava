<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h2><a href="">Home</a></h2>
<p></p>
<h1>Meals</h1>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${mealsList}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color:${meal.excess ? 'red' : 'green'}">
                <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm"))}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="MealController?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="MealController?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>