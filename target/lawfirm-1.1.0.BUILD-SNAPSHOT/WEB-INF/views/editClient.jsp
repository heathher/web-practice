<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigationBar.jsp" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <title>Edit clients page</title>
</head>
<body>

<h1><b>Edit Client</b></h1>

<c:url var="saveUrl" value="/main/clients/edit?id=${clientAttribute.customerId}" />
<form:form modelAttribute="clientAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="lastName">Client's lastname:</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">Client's firstname:</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">Phone:</form:label></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="mail">Mail:</form:label></td>
            <td><form:input path="mail"/></td>
        </tr>
        <tr>
            <td><form:label path="address">Address:</form:label></td>
            <td><form:input path="address"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>