<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>


</head>

<body>

GJHJHGJHG

<c:if test="${!empty errors}">
    <p>
    <div class="col-xs-10 col-xs-offset-1">
        <c:forEach items="${errors}" var="error">
            <div class="alert alert-danger fade in">
                <button class="close" aria-hidden="true" data-dismiss="alert" type="button">
                    ×
                </button>
                <strong><spring:message code="error.00" /></strong>
                <spring:message code="error.${error}" />
            </div>
        </c:forEach>
    </div>
    </p>
</c:if>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

</body>
</html>