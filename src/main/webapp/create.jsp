<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit meal</title>
</head>
<body>

<form method="POST" action='MealController' name="frmAddMeal">
    DateTime : <input
        type="text" name="ldt"
        value="<fmt:formatDate pattern="yyyy-dd-MM HH:mm" value="${meal.ldt}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Last Name : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />

    <input type="submit" value="Submit" />
</form>
</body>
</html>