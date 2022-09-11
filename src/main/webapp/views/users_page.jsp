<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>users_page</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>

<div>
    <fmt:bundle basename="pagecontent" prefix="users_page.">
        <table class="table">
            <caption><fmt:message key="headText"/></caption>
            <thead class="thead-light">
            <tr>
                <th scope="col"><fmt:message key="tableUserID"/></th>
                <th scope="col"><fmt:message key="tableUserLogin"/></th>
                <th scope="col"><fmt:message key="tableUserRole"/></th>
                <th scope="col"><fmt:message key="tableUserEmail"/></th>
                <th scope="col"><fmt:message key="tableUserStatus"/></th>
                <th scope="col"><fmt:message key="tableUserOrders"/></th>
                <th scope="col"><fmt:message key="tableUserBlock"/></th>
                <th scope="col"><fmt:message key="tableUserInfo"/></th>
                <th scope="col"><fmt:message key="tableUserDelete"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.login}</td>
                    <td>${user.role.role}</td>
                    <td>${user.email}</td>
                    <c:if test="${user.activeStatus.equals(true)}">
                        <td>
                            <fmt:message key="tableTextActive"/>
                        </td>
                    </c:if>

                    <c:if test="${!user.activeStatus.equals(true)}">
                        <td>
                            <fmt:message key="tableTextBlock"/>
                        </td>
                    </c:if>

                    <td>
                        <form action="controller" method="POST">
                            <input type="hidden" id="OrderUserID" name="userID" value="${user.id}">
                            <button type="submit" name="command" value="ORDERS_BY_USER_ID"
                                    class="btn btn-outline-info"><fmt:message key="ButtonUserOrders"/>
                            </button>
                        </form>
                    </td>

                    <c:if test="${user.activeStatus.equals(true)}">
                        <td>
                            <form action="controller" method="POST">
                                <input type="hidden" id="BlockUserID" name="userID" value="${user.id}">
                                <button type="submit" name="command" value="BLOCK_USER_BY_ADMIN"
                                        class="btn btn-outline-danger">
                                    <fmt:message key="ButtonUserBlock"/>
                                </button>
                            </form>
                        </td>
                    </c:if>

                    <c:if test="${!user.activeStatus.equals(true)}">
                        <td>
                            <form action="controller" method="POST">
                                <input type="hidden" id="UnblockUserID" name="userID" value="${user.id}">
                                <button type="submit" name="command" value="UNBLOCK_USER"
                                        class="btn btn-outline-success">
                                    <fmt:message key="ButtonUserUnblock"/>
                                </button>
                            </form>
                        </td>
                    </c:if>

                    <td>
                        <form action="controller" method="POST">
                            <input type="hidden" id="InfoUserID" name="userID" value="${user.id}">
                            <button type="submit" name="command" value="USER_INFO_PAGE_BY_ADMIN"
                                    class="btn btn-outline-info"><fmt:message key="ButtonUserInfo"/>
                            </button>
                        </form>

                    <td>
                        <form action="controller" method="POST">
                            <input type="hidden" id="DeleteUserID" name="userID" value="${user.id}">
                            <button type="submit" name="command" value="DELETE_USER_FROM_DB"
                                    class="btn btn-outline-danger">
                                <fmt:message key="ButtonUserDelete"/>
                            </button>
                        </form>
                    </td>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>