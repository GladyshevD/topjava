<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h4><a href="index.html">Home</a></h4>
<table>
    <style>
        table {
            border: 4px double black; /* Рамка вокруг таблицы */
            border-collapse: collapse; /* Отображать только одинарные линии */
        }
        th {
            text-align: center; /* Выравнивание по левому краю */
            background: #ccc; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black; /* Граница вокруг ячеек */
        }
        td {
            text-align: center;
            border: 1px solid black; /* Граница вокруг ячеек */
        }
    </style>
    <caption><h3>Meals</h3></caption>
    <thead>
    <tr>
        <th>Meal Date/Time</th>
        <th>Meal</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach items="${list}" var="list">
        <tr style="color: ${list.excess ? 'red' : 'green'}">
            <td><fmt:parseDate value="${list.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></td>
            <td>${list.description}</td>
            <td>${list.calories}</td>
            <td>
                <form action="meals" method="get">
                    <button type="submit" name="edit" value="${list.id}">Update</button>
                </form>
            </td>
            <td>
                <form action="meals" method="get">
                    <button type="submit" name="delete" value="${list.id}">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="meal.jsp" method="get">
    <button type="submit">Add a new meal</button>
</form>
</body>
</html>