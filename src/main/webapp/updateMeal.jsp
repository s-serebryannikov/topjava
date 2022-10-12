<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit meal</title>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <hr>
    <h2> ${action=="create" ? 'Create Meal': 'Edit Meal'}<h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        DateTime:
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}"  required>
        Description:
        <input type="text" name="description" value="${meal.description}" required>
        Calories:
        <input type="number" name="calories" value="${meal.calories}" required>

        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>