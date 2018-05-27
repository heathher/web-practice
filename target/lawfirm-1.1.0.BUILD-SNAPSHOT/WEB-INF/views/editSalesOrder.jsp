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
    <title>Edit contracts page</title>
</head>
<body>


<h1><b>Edit Contract</b></h1>
<h2>Write date in yyyy-mm-dd format</h2>
<c:url var="saveUrl" value="/main/start/edit?id=${salesOrderAttribute.id}" />
<form:form modelAttribute="salesOrderAttribute" method="POST" action="${saveUrl}">
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

    <input type="submit" value="Save" />
</form:form>
<h1 style="margin-left: 50px">Client</h1>

<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Id</th>
        <th >Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clientsList3}" var="elem">
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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${servantsList3}" var="elem1">
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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${servicesList3}" var="elem2">
        <tr>
            <td><c:out value="${elem2.serviceId}" /></td>
            <td><c:out value="${elem2.serviceName}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>