<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit or add meal</title>
</head>
<body>
<h4><a href="index.html">Home</a></h4><br/>
<h3>Edit or add meal</h3>
<form action="meals" method="post">
    Enter/edit meal date and time: <input type="datetime-local" name="datetime" value="${mealForEdit.dateTime}"><br/><br/>
    Enter/edit meal description:
    <input type="text" name="description" value="${mealForEdit.description}">
    <br/><br/>
    Enter/edit meal calories number: <input type="number" name="calories" value="${mealForEdit.calories}"><br/><br/>
    <input type="hidden" name="id" value="${mealForEdit.id}">
    <input type="submit" value="Submit">
</form>
</body>
</html>
