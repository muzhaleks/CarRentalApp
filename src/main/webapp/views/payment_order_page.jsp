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


<div class="container">
    <fmt:bundle basename="pagecontent" prefix="payment_order_page.">
        <form class="form-horizontal" role="form" method="POST" action="controller">
            <div>
                <input type="hidden" id="paymentOrderID" name="paymentOrderID" value="${paymentOrderID}">
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2><fmt:message key="headText"/> ${totalPrice} </h2>
                    <hr>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="totalCost"><fmt:message key="enterTotalCost"/></label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                            <input type="text" name="totalCost" class="form-control" id="totalCost" placeholder=1..."
                                   required="" autofocus="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <button type="submit" name="command" value="PAYMENT_ORDER" class="btn btn-success"><i
                            class="fa fa-user-plus"></i> <fmt:message key="buttonToPay"/>
                    </button>
                </div>
            </div>
        </form>
    </fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>
