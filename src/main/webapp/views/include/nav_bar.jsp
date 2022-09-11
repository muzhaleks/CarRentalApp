<%@ page import="com.github.muzhaleks.command.receiver.language.Language" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${empty sessionScope.language}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${not empty sessionScope.language}">
    <c:if test="${ sessionScope.language.equals('en')}">
        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${ sessionScope.language.equals('ru')}">
        <fmt:setLocale value="ru"/>
    </c:if>
</c:if>
<c:if test="${empty sessionScope.role}">
    <c:set var="role" scope="session" value="guest" />
</c:if>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="bd-example">

            <fmt:bundle basename="pagecontent" prefix="nav_bar.">
                <ul class="nav nav-pills">

                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=INDEX_PAGE">
                            <fmt:message key="index"/> </a>
                    </li>

                    <c:if test="${empty sessionScope.role}">

                        <li class="nav-item active">
                            <a class="nav-link" href="controller?command=SIGN_IN_PAGE">
                                <fmt:message key="signIn"/> </a>
                        </li>

                        <li class="nav-item active">
                            <a class="nav-link" href="controller?command=USER_REGISTRATION_PAGE"
                               methods="POST"><fmt:message key="registration"/> </a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('guest')}">

                        <li class="nav-item active">
                            <a class="nav-link" href="controller?command=SIGN_IN_PAGE">
                                <fmt:message key="signIn"/> </a>
                        </li>

                        <li class="nav-item active">
                            <a class="nav-link" href="controller?command=USER_REGISTRATION_PAGE"
                               methods="POST"><fmt:message key="registration"/> </a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.role.equals('user')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=CARS_LIST">
                                <fmt:message key="carList"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('admin')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=CARS_LIST">
                                <fmt:message key="carList"/></a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.role.equals('user')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=USER_INFO_PAGE"><fmt:message
                                    key="profile"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('admin')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=USER_INFO_PAGE"><fmt:message
                                    key="profile"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('admin')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=ALL_USER_LIST"><fmt:message
                                    key="userList"/></a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.role.equals('user')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=ORDERS_BY_USER_ID&userID=${user.id}">
                                <fmt:message key="myOrders"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('admin')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=ORDERS_BY_USER_ID&userID=${ActiveUserId}">
                                <fmt:message key="myOrders"/></a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.role.equals('admin')}">
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=ADMIN_PAGE"><fmt:message key="adminPage"/></a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.role.equals('user')}">
                        <form class="form-inline" action="controller" method="POST">
                            <button class="btn btn-outline-danger " name="command" value="LOGOUT" type="submit">
                                <fmt:message key="signOut"/>
                            </button>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.role.equals('admin')}">
                        <form class="form-inline" action="controller" method="POST">
                            <button class="btn btn-outline-danger " name="command" value="LOGOUT" type="submit">
                                <fmt:message key="signOut"/>
                            </button>
                        </form>
                    </c:if>


                    <li class="nav-item dropdown ">
                        <div class="nav-item dropdown">
                            <button type="button"
                                    class="nav-link dropdown-toggle btn btn-link"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="language"/>
                            </button>
                            <div class="dropdown-menu">
                                <form method="post" action="controller" name="command">
                                    <input type="hidden"
                                           name="command"
                                           value="CHANGE_LANGUAGE">
                                    <button type="submit" class="btn btn-link"
                                            name="language"
                                            value="<%=Language.RU%>">
                                        <fmt:message key="language.ru"/>
                                    </button>
                                    <button type="submit" class="btn btn-link"
                                            name="language"
                                            value="<%=Language.EN%>">
                                        <fmt:message key="language.en"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </li>
                </ul>
            </fmt:bundle>
        </div>
    </div>
</nav>