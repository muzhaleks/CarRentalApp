<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/myTags.tld" prefix="mt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>

    <meta charset="UTF-8">
    <title>car info</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/nav_bar.jsp" %>

<div class="container-fluid">
    <fmt:bundle basename="pagecontent" prefix="cars_info_page.">
        <c:if test="${infoCarModel.equals('polo')}">
            <div>
                <img src="images/vw_polo_sedan.jpg" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/Volkswagen_Polo_Sedan_(2010)"><fmt:message
                        key="link_info_by_car_on_wiki"/></a>
            </div>

        </c:if>


        <c:if test="${infoCarModel.equals('c4')}">
            <div>
                <img src="images/C4_Sedan.png" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/Citro%C3%ABn_C4"><fmt:message
                        key="link_info_by_car_on_wiki"/></a>
            </div>

        </c:if>
        <c:if test="${infoCarModel.equals('c5')}">
            <div>
                <img src="images/citroen_c5.jpeg" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/Citro%C3%ABn_C5"><fmt:message
                        key="link_info_by_car_on_wiki"/></a>
            </div>
        </c:if>

        <c:if test="${infoCarModel.equals('vesta')}">
            <div>
                <img src="images/lada_vestaswcross_1.jpg" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/LADA_Vesta"><fmt:message key="link_info_by_car_on_wiki"/></a>
            </div>
        </c:if>
        <c:if test="${infoCarModel.equals('granta')}">
            <div>
                <img src="images/lada_granta.png" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/LADA_Granta)"><fmt:message key="link_info_by_car_on_wiki"/></a>
            </div>
        </c:if>

        <c:if test="${infoCarModel.equals('logan')}">
            <div>
                <img src="images/renault_logan.jpg" class="rounded" width="400">
                <a href="https://ru.wikipedia.org/wiki/Logan"><fmt:message key="link_info_by_car_on_wiki"/></a>
            </div>

        </c:if>


    </fmt:bundle>
</div>
<footer>
    <div id="footer">
        <%@include file="include/footer.jsp" %>
    </div>
</footer>
</body>
</html>