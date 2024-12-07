<%--
  Created by IntelliJ IDEA.
  User: Judit
  Date: 2024. 11. 15.
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Hallgatók</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        table th, td
        {
            border: 1px solid black;
        }

        tr:nth-child(odd){
            background-color: beige;
        }
    </style>
</head>
<body>

<c:if test="${!empty message}">
    <h2>${message}</h2>
</c:if>

<c:if test="${empty hallgatok}">
Nincs hallgató, vegyél fel újat!
</c:if>
<c:if test="${!empty hallgatok}">
    <h1>Hallgatóink:</h1>
    <br>
    <table>
        <tr>
            <th>Név</th>
            <th>Neptun kód</th>
            <th></th>
        </tr>
        <c:forEach items="${hallgatok}" var="hallgato">
            <tr onclick="window.location ='${pageContext.servletContext.contextPath}/hallgato/${hallgato.neptunKod}';">
                <td>${hallgato.teljesNev}</td>
                <td>${hallgato.neptunKod}</td>
                <td><i class="fa fa-car"></i> </td>
            </tr>
        </c:forEach>


    </table>
</c:if>
<br>
<form action="${pageContext.request.contextPath}/ujhallgato" method="get">
<input type="submit" value="Új hallgatód hozzáadása" />
</form>
</body>
</html>
