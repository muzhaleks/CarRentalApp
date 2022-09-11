<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/myTags.tld" prefix="mt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>

    <meta charset="UTF-8">
    <title>index</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>

<div class="container-fluid">
    <fmt:bundle basename="pagecontent" prefix="index.">
    <h1><fmt:message key="name" /></h1>
    <div class="text-center">
        <img src="images/rentimage.jpg" class="rounded" width="800">
    </div>
    <p>
        <fmt:message key="text" />
    </p>
    </fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>