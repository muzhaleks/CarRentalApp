<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>orders_list</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>
<div>
<fmt:bundle basename="pagecontent" prefix="orders_page.">
    <table class="table">
        <caption><fmt:message key="headText"/></caption>
        <thead class="thead-light">
        <tr>
            <th scope="col"><fmt:message key="tableTextID"/></th>
            <th scope="col"><fmt:message key="tableUserLogin"/></th>
            <th scope="col"><fmt:message key="tableTextMark"/></th>
            <th scope="col"><fmt:message key="tableTextModel"/></th>
            <th scope="col"><fmt:message key="tableTextTime"/></th>
            <th scope="col"><fmt:message key="tableTotalPrice"/></th>
            <th scope="col"><fmt:message key="tablePaymentStatus"/></th>
            <th scope="col"><fmt:message key="tableConfirmByAdminStatus"/></th>
            <th scope="col"><fmt:message key="tableDateOfRegOrder"/></th>
            <th scope="col"><fmt:message key="tableOrderStatus"/></th>
            <th scope="col"><fmt:message key="tableNotes"/></th>
            <th scope="col"><fmt:message key="tableOrderInfo"/></th>
        </tr>
        </thead>


        <tbody>

        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.user.login}</td>
                <td>${order.car.mark.mark}</td>
                <td>${order.car.model.modelName}</td>
                <td>${order.rentHours}</td>
                <td>${order.totalPrice}</td>

                <c:if test="${order.paymentStatus.equals(true)}">
                    <td><p class="text-success"><fmt:message key="paymentStatusTrue"/></p></td>
                </c:if>

                <c:if test="${!order.paymentStatus.equals(true)}">
                    <td><p class="text-danger"><fmt:message key="paymentStatusFalse"/></p></td>
                </c:if>

                <c:if test="${order.confirmByAdminStatus.equals(true)}">
                    <td><p class="text-success"><fmt:message key="tableConfirmByAdminStatusTrue"/></p></td>
                </c:if>

                <c:if test="${!order.confirmByAdminStatus.equals(true)}">
                    <td><p class="text-danger"><fmt:message key="tableConfirmByAdminStatusFalse"/></p></td>
                </c:if>

                <td>${order.dateOfRegOrder}</td>

                <c:if test="${order.orderStatus.equals(true)}">
                    <td><p class="text-success"><fmt:message key="tableOrderStatusTrue"/></p></td>
                </c:if>

                <c:if test="${!order.orderStatus.equals(true)}">
                    <td><p class="text-danger"><fmt:message key="tableOrderStatusFalse"/></p></td>
                </c:if>

                <td>${order.notes}</td>
                <td>
                    <form action="controller" method="POST">
                        <input type="hidden" id="OrderUserID" name="orderID" value="${order.id}">
                        <button type="submit" name="command" value="ORDER_INFO"
                                class="btn btn-outline-info"><fmt:message key="buttonOrderInfo"/>
                        </button>
                    </form>
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

