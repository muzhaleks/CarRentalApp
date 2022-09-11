<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit password </title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>
<div style="width: 400px" class="container mx-auto">
<fmt:bundle basename="pagecontent" prefix="change_password_page.">
    <div class="container text-center my-5">

        <form class="form-signin" action="controller" method="POST">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="headText"/></h1>

            <label for="oldPass"><fmt:message key="enterOldPass"/></label>
            <input type="password" id="oldPass" class="form-control mt-1" placeholder="Старый пароль" name="oldPassword">

            <label for="newPass"><fmt:message key="enterNewPass"/></label>
            <input type="password" id="newPass" class="form-control mt-1" placeholder="Новый пароль" name="newPassword">

            <label for="rPassword"><fmt:message key="repeatPass"/></label>
            <input type="password" id="rPassword" class="form-control mt-1" placeholder="Повторите пароль" name="rPassword">

            <button class="btn btn-lg btn-primary btn-block my-3" type="submit" name="command" value="USER_CHANGE_PASSWORD"><fmt:message key="tableButtonChangePass"/></button>
        </form>
    </div>
</fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>
