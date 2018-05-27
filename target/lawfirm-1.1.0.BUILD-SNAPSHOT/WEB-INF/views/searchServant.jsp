<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigationBar.jsp" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <title>Search servants</title>
</head>
<body>

<h1>Searching servants</h1>
<h2>Write searching options (leave blank, what's not needed)</h2>
<h2>Write date in yyyy-mm-dd format</h2>

<c:url var="saveUrl" value="/main/servants/search" />
<form:form modelAttribute="pairAttribute" method="POST" action="${saveUrl}">
    <table>
        <table>
            <tr>
                <td><form:label path="serviceByServiceId.serviceId">Service id:</form:label></td>
                <td><form:input path="serviceByServiceId.serviceId"/></td>
            </tr>
            <tr>
                <td><form:label path="customerByCustomerId.customerId">Client's id:</form:label></td>
                <td><form:input path="customerByCustomerId.customerId"/></td>
            </tr>
            <tr>
                <td><form:label path="employeeByEmployeeId.employeeId">Servant's id:</form:label></td>
                <td><form:input path="employeeByEmployeeId.employeeId"/></td>
            </tr>
            <tr>
                <td><form:label path="orderDate">End date:</form:label></td>
                <td><form:input path="orderDate"/></td>
            </tr>
        </table>
    </table>
    <input type="submit" value="Search" />
</form:form>

<h1 style="margin-left: 50px">Client</h1>

<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Name</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clientsList2}" var="elem">
        <tr>
            <td><c:out value="${elem.customerId}" /></td>
            <td><c:out value="${elem.lastName} ${elem.firstName}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h1 style="margin-left: 50px">Servant</h1>
<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Name</th>
        <th colspan="6"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${servantsList2}" var="elem1">
        <tr>
            <td><c:out value="${elem1.employeeId}" /></td>
            <td><c:out value="${elem1.lastname} ${elem1.firstname}" /></td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<h1 style="margin-left: 50px">Service</h1>
<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Function</th>
        <th colspan="6"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${servicesList2}" var="elem2">
        <tr>
            <td><c:out value="${elem2.serviceId}" /></td>
            <td><c:out value="${elem2.serviceName}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>