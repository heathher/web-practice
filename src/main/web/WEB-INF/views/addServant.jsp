<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adding servants</title>
</head>
<body>

<h1>Create Servant</h1>

<c:url var="saveUrl" value="/main/servants/add" />
<form:form modelAttribute="servantAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="lastname">Servant's lastname:</form:label></td>
            <td><form:input path="lastname"/></td>
        </tr>
        <tr>
            <td><form:label path="firstname">Servant's name:</form:label></td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td><form:label path="address">Servant's address:</form:label></td>
            <td><form:input path="address"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">Servant's phone:</form:label></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="mail">Servant's e-mail:</form:label></td>
            <td><form:input path="mail"/></td>
        </tr>
        <tr>
            <td><form:label path="educationByEducationId.grade">Servant's education level:</form:label></td>
            <td><form:input path="educationByEducationId.grade"/></td>
        </tr>
        <tr>
            <td><form:label path="jobByJobId.function">Servant's position:</form:label></td>
            <td><form:input path="jobByJobId.function"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>