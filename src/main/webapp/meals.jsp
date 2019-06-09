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
    <caption>Meals</caption>
    <style type="text/css">
        TABLE {
            width: 300px;
            border-top: 2px solid #000;
            border-bottom: 2px solid #000;
        }

        TD, TH {
            padding: 3px;
        }

        TH {
            text-align: left;
            border-bottom: 1px solid #000;
        }

        .red {
            color: red;
        }

        .green {
            color: green;
        }
    </style>
    <tr>
        <th>Meal Date/Time</th>
        <th>Meal</th>
        <th>Calories</th>
    </tr>
    <jsp:useBean id="listTo" scope="request" type="java.util.List"/>
    <c:forEach items="${listTo}" var="list">
        <c:set var="exc" value="class = ${list.excess ? 'red' : 'green'}"/>
        <tr>
            <td><span ${exc}><fmt:parseDate value="${list.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                            type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></span></td>
            <td><span ${exc}>${list.description}</span></td>
            <td><span ${exc}>${list.calories}</span></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>