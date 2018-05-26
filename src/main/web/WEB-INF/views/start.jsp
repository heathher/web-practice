<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigationBar.jsp" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <title>Main page</title>
</head>
<body>
<h1 style="margin-left: 50px">Contract</h1>

<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Service</th>
        <th >Client</th>
        <th >Servant</th>
        <th >Date</th>
        <th colspan="4"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contracts}" var="elem">
        <c:url var="editUrl" value="/main/start/edit?id=${elem.id}" />
        <c:url var="deleteUrl" value="/main/start/delete?id=${elem.id}" />
        <tr>
            <td><c:out value="${elem.serviceByServiceId.serviceName}" /></td>
            <td><c:out value="${elem.customerByCustomerId.firstName} ${elem.customerByCustomerId.lastName}" /></td>
            <td><c:out value="${elem.employeeByEmployeeId.firstname} ${elem.employeeByEmployeeId.lastname}" /></td>
            <td><c:out value="${elem.orderDate}" /></td>

            <td style="background-color: indianred;width: 100px; padding:0px">
                <a style="color: white;text-decoration: none;display: block; padding: 9px" href="${editUrl}">
                    Edit
                </a>
            </td>
            <td style="background-color: indianred;width: 100px; padding:0px">
                <a style="color: white;text-decoration: none;display: block; padding: 9px" href="${deleteUrl}">
                    Delete
                </a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<c:url var="addUrl" value="/main/start/add" />
<nav style="background-color: indianred; width: 10%; margin-left: 50px; margin-top: 10px;">
    <li><a style="color: #ffffff; display: inline-block; line-height: 2em;padding: 0.5em 2em;
    text-decoration: blink;" href="${addUrl}">Add Contract</a></li>
</nav>

<c:if test="${empty contracts}">
    There are currently no contract in the list. <a href="${addUrl}">Add</a> the contract.
</c:if>

</body>
</html>