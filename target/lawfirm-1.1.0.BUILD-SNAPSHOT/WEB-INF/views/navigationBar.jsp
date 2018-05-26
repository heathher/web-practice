<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        <%@include file="style.css" %>
    </style>
</head>
<body>

<c:url var="newsUrl" value="/main/start" />
<c:url var="clientUrl" value="/main/clients" />
<c:url var="servantUrl" value="/main/servants" />
<c:url var="searchClientUrl" value="/main/clients/search" />
<c:url var="searchServantUrl" value="/main/servants/search" />
<nav>
    <ul>
        <li><a href="${newsUrl}">Main page</a></li>
        <li><a href="${clientUrl}">Clients</a></li>
        <li><a href="${servantUrl}">Servants</a></li>
        <li><a href="${searchClientUrl}">Search clients</a></li>
        <li><a href="${searchServantUrl}">Search servants</a></li>
    </ul>
</nav>
</body>
</html>