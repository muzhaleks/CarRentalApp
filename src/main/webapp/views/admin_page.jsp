<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>admin_page</title>
    <%@include file="include/header.jsp" %>
</head>
<body>

<%@include file="include/nav_bar.jsp" %>

<div>
<fmt:bundle basename="pagecontent" prefix="admin_page.">
    <h2>
        <fmt:message key="hello" /> ${sessionScope.activeUser.login}
    </h2>
</fmt:bundle>
</div>
<div class="container">
<fmt:bundle basename="pagecontent" prefix="admin_page.">
    <ul class="list-unstyled">
        <li><p class="text-danger"><fmt:message key="uncheckOrders" /> <strong>${countOrder}</strong></p></li>
    </ul>

    <ul class="list-unstyled">
    <fmt:bundle basename="pagecontent" prefix="admin_page.">
            <td>
                <form action="controller" method="POST">
                    <button type="submit" name="command" value="ORDER_BY_CONFIRM_STATUS"
                            class="btn btn-outline-danger">
                        <fmt:message key="buttonGetAllOrder"/>
                    </button>
                </form>
            </td>
    </fmt:bundle>
    </ul>


</fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>
