<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit or add meal</title>
</head>
<body>
<h3>Edit or add meal</h3>
<form action="meals" method="post">
    Enter/edit meal date and time: <input type="datetime-local" name="datetime" value="${mealForEdit.dateTime}"><br/><br/>
    Enter/edit meal description:
    <input type="radio" name="description" value="Завтрак" <c:if test="${mealForEdit.description == 'Завтрак'}">
           checked</c:if>>Завтрак
    <input type="radio" name="description" value="Обед" <c:if test="${mealForEdit.description == 'Обед'}">
           checked</c:if>>Обед
    <input type="radio" name="description" value="Ужин" <c:if test="${mealForEdit.description == 'Ужин'}">
           checked</c:if>>Ужин
    <br/><br/>
    Enter/edit meal calories number: <input type="number" name="calories" value="${mealForEdit.calories}"><br/><br/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
