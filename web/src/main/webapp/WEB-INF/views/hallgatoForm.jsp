<%--
  Created by IntelliJ IDEA.
  User: Judit
  Date: 2024. 11. 15.
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Hallgató felvitele</title>
</head>
<body>
<h2>Hallgató adatai:</h2>

<form:form method="post" action="ujhallgato" modelAttribute="hallgato">
    <fieldset>
        <legend>Azonosítók</legend>
        <form:label path="neptunKod">Neptun Kód</form:label>
        <form:input path="neptunKod" type="text"/>
        <form:errors path="neptunKod"></form:errors>
    </fieldset>
    <br>
    <fieldset>
        <legend>Alap adatok</legend>

        <form:label path="teljesNev">Teljes név:</form:label>
        <form:input path="teljesNev" type="text"/>
        <form:errors path="teljesNev"></form:errors>

        <br>
        <form:label path="email">Email:</form:label>
        <form:input path="email" type="text"/>
        <form:errors path="email"></form:errors>
        <br>

        <form:label path="szuletesiDatum">Születési dátum:</form:label>
        <form:input path="szuletesiDatum" type="date"/>
        <form:errors path="szuletesiDatum"></form:errors>

        <br>
        <form:label path="Nem" for="nem">Nem:</form:label>
        <form:select path="nem">
            <form:options/>
        </form:select>
    </fieldset>
    <br>

<c:if test="${method ne 'View'}">
    <input type="submit" value="Hozzáadás">
</c:if>
</form:form>
</body>
</html>
