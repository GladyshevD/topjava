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
    <caption><h3>Meals</h3></caption>
    <style type="text/css">
        TABLE {
            border-top: 2px solid #000;
            border-bottom: 2px solid #000;
        }
        TD, TH {
            padding: 3px 30px 3px 3px;
        }
        TH {
            text-align: center;
            border-bottom: 1px solid #000;
        }
    </style>
    <thead>
    <tr>
        <th>ID</th>
        <th>Meal Date/Time</th>
        <th>Meal</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <c:forEach items="${mapTo}" var="map">
        <tr style="color: ${map.value.excess ? 'red' : 'green'}">
            <td>${map.key}</td>
            <td><fmt:parseDate value="${map.value.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                            type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></td>
            <td>${map.value.description}</td>
            <td>${map.value.calories}</td>
            <td></td>
            <td>
                <form action="meals" method="post">
                    <button type="submit" name="delete" value="${map.key}">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>