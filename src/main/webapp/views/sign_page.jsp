<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Sign page</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>
<div style="width: 400px" class="container mx-auto">
<fmt:bundle basename="pagecontent" prefix="sign_in.">
    <div class="container text-center my-5">
        <img src="images/sign.png" width="100">
    </div>

    <div class="container text-center my-5">

        <form class="form-signin" action="controller" method="POST">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label" /></h1>
            <label for="login"><fmt:message key="loginInput" /></label>
            <input type="text" id="login" class="form-control" placeholder="Login" name="login">

            <label for="pass"><fmt:message key="passwordInput" /></label>
            <input type="password" id="pass" class="form-control mt-1" placeholder="Password" name="password">

            <button class="btn btn-lg btn-primary btn-block my-3" type="submit"
                    name="command" value="SIGN_IN"><fmt:message key="signIn" /></button>
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
