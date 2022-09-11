<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration page</title>
    <%@include file="include/header.jsp" %>
    <script src="js/registration_page.js" type="text/javascript"></script>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>


<form class="needs-validation" action="controller" novalidate method="POST">
<fmt:bundle basename="pagecontent" prefix="registration_page.">
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="login"><fmt:message key="tableUserLogin" /></label>
            <input type="text" class="form-control" name="login" id="login" placeholder=<fmt:message key="tableFieldTextLogin" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="password"><fmt:message key="tableUserPass" /></label>
            <input type="password" class="form-control" name="password" id="password" placeholder=<fmt:message key="tableFieldTextPass" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="confirm_password"><fmt:message key="tableUserRPass" /></label>
            <input type="password" class="form-control" name="confirm_password" id="confirm_password"
                   placeholder=<fmt:message key="tableFieldTextPass" /> required>
             <div class="invalid-feedback">
                 <fmt:message key="tableErrorFieldMessage" />
             </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="firstName"><fmt:message key="tableUserFirstName" /></label>
            <input type="text" class="form-control" name="firstName" id="firstName" placeholder=<fmt:message key="tableFieldTextFirstName" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="lastName"><fmt:message key="tableUserLastName" /></label>
            <input type="text" class="form-control" name="lastName" id="lastName" placeholder=<fmt:message key="tableFieldTextLastName" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="email"><fmt:message key="tableUserEmail" /></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                </div>
                <input type="text" class="form-control" name="email" id="email" placeholder=<fmt:message key="tableFieldTextEmail" />
                       aria-describedby="inputGroupPrepend" required>
                <div class="invalid-feedback">
                    <fmt:message key="tableErrorFieldMessage" />
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="passportSerialNumber"><fmt:message key="tableUserPassport" /></label>
            <input type="text" class="form-control" name="passportSerialNumber" id="passportSerialNumber"
                   placeholder=<fmt:message key="tableFieldTextPassportSerialNumber" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="phoneNumber"><fmt:message key="tableUserPhoneNumber" /></label>
            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder=<fmt:message key="tableFieldTextPhoneNumber" />
                   required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <label for="driverLicenceNumber"><fmt:message key="tableUserDriverLicenceNumber" /></label>
            <input type="text" class="form-control" name="driverLicenceNumber" id="driverLicenceNumber"
                   placeholder=<fmt:message key="tableFieldDriverLicenceNumber" /> required>
            <div class="invalid-feedback">
                <fmt:message key="tableErrorFieldMessage" />
            </div>
        </div>

        <button class="btn btn-primary" type="submit" name="command" value="USER_REGISTRATION"><fmt:message key="tableButtonRegistration" /></button>
    </div>
</fmt:bundle>
</form>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>
