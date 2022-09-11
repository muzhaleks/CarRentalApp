<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>car_list</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <fmt:bundle basename="pagecontent" prefix="cars_list_page.">
        <h1 class="display-4"><fmt:message key="priceText"/></h1>
        <p class="lead"><fmt:message key="headText"/></p>
    </fmt:bundle>
</div>


<div class="container text-center my-5 px-5">
    <fmt:bundle basename="pagecontent" prefix="cars_list_page.">
        <div class="row">
            <c:forEach items="${cars}" var="car">
                <div class="col-mb-3">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">${car.mark.mark}</h4>
                        </div>
                        <div class="card-body">

                            <ul class="list-unstyled mt-3 mb-4">
                                <li><fmt:message key="tableTextMark"/> - ${car.mark.mark}</li>
                                <li><fmt:message key="tableTextModel"/> - ${car.model.modelName}</li>
                                <li><fmt:message key="tableTextMillage"/> - ${car.millage}км</li>
                                <li><fmt:message key="tableTextPrice"/> - ${car.price}</li>
                                <li><fmt:message key="tableTextStatus"/> - ${car.carStatus.carStatus}</li>
                            </ul>
                            <c:if test="${car.carStatus.carStatus.equals('free')}">
                                <form action="controller" method="POST">
                                    <input type="hidden" id="OrderCarID" name="orderCarID" value="${car.id}">
                                    <input type="hidden" id="OrderCarMark" name="orderCarMark" value="${car.mark.mark}">
                                    <input type="hidden" id="OrderCarModel" name="orderCarModel" value="${car.model.modelName}">
                                    <button type="submit" name="command" value="CREATE_ORDER_PAGE"
                                            class="btn btn-lg btn-block btn-outline-primary"><fmt:message
                                            key="tableButtonToOrder"/></button>
                                </form>
                            </c:if>
                            <c:if test="${!car.carStatus.carStatus.equals('free')}">
                                <form action="controller" method="GET">
                                    <button type="submit" class="btn btn-lg btn-block btn-outline-secondary" disabled>
                                        <fmt:message key="tableButtonToOrder"/></button>
                                </form>
                            </c:if>
                            <form action="controller" method="POST">
                                <input type="hidden" id="InfoCarMark" name="infoCarMark" value="${car.mark.mark}">
                                <input type="hidden" id="InfoCarModel" name="infoCarModel" value="${car.model.modelName}">

                                <button type="submit" name="command" value="CAR_INFO_PAGE"
                                        class="btn btn-lg btn-block btn-outline-primary"><fmt:message
                                        key="tableButtonViewInfo"/></button>
                            </form>
                        </div>
                    </div>

                </div>
            </c:forEach>
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
