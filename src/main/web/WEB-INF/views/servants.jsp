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
<h1 style="margin-left: 50px">Servant</h1>

<table style="margin-left: 50px; width: 700px; text-align:center; border-collapse: collapse; ">
    <thead style="background:#fcf">
    <tr >
        <th >Name</th>
        <th >Address</th>
        <th >Phone</th>
        <th >E-mail</th>
        <th >Education level</th>
        <th >Position</th>
        <th colspan="6"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${servants}" var="elem">
        <c:url var="editUrl" value="/main/servants/edit?id=${elem.employeeId}" />
        <c:url var="deleteUrl" value="/main/servants/delete?id=${elem.employeeId}" />
        <tr>
            <td><c:out value="${elem.lastname}${elem.firstname}" /></td>
            <td><c:out value="${elem.address}" /></td>
            <td><c:out value="${elem.phone}" /></td>
            <td><c:out value="${elem.mail}" /></td>
            <td><c:out value="${elem.educationByEducationId.grade}" /></td>
            <td><c:out value="${elem.jobByJobId.function}" /></td>
            <td style="background-color: indianred;width: 100px; padding:0px">
                <a style="color: white;text-decoration: none;display: block; padding: 9px" href="${editUrl}">
                    Edit
                </a>
            </td>
            <td style="background-color: indianred;width: 100px; padding:0px">
                <a style="color: white;text-decoration: none;display: block; padding: 9px" href="${deleteUrl}" onclick="return confirm('Are you sure you want to delete ' +
                 'servant?\nYou will delete servant from all contracts')">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<c:url var="addUrl" value="/main/servants/add" />
<nav style="background-color: indianred; width: 10%; margin-left: 50px; margin-top: 10px;">
    <li><a style="color: #ffffff; display: inline-block; line-height: 2em;padding: 0.5em 2em;
    text-decoration: blink;" href="${addUrl}">Add Servant</a></li>
</nav>

<c:if test="${empty servants}">
    <c:if test="${!searched}">
        There are currently no servants in the list. <a href="${addUrl}">Add</a> the servant.
    </c:if>
    <c:if test="${searched}">
        No servant found using this parameters.
    </c:if>
</c:if>

</body>
</html>