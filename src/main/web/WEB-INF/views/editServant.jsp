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
    <title>Edit servants page</title>
</head>
<body>

<h1><b>Edit Servant</b></h1>

<c:url var="saveUrl" value="/main/servants/edit?id=${servantAttribute.employeeId}" />
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
            <td><form:label path="educationByEducationId.educationId">Servant's education id:</form:label></td>
            <td><form:input path="educationByEducationId.educationId"/></td>
        </tr>
        <tr>
            <td><form:label path="jobByJobId.jobId">Servant's position id:</form:label></td>
            <td><form:input path="jobByJobId.jobId"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>

<h1 style="margin-left: 50px">Job</h1>
<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Function</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${job}" var="elem2">
        <tr>
            <td><c:out value="${elem2.jobId}" /></td>
            <td><c:out value="${elem2.function}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h1 style="margin-left: 50px">Education</h1>
<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Grade</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${education}" var="elem2">
        <tr>
            <td><c:out value="${elem2.educationId}" /></td>
            <td><c:out value="${elem2.grade}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>