<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<template:page>
<form class="form-inline" action="${pageContext.request.contextPath}/productList" method="GET">
    <button type="submit" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-search"></span> Search
    </button>
    <input type="text" class="form-control" id="userSearch" name="userSearch"
           placeholder="Search for phone.."/>
</form>
<br>

<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <c:forEach begin="0" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}/productList?page=${i}&sort=${param.sort}&type=${param.type}&userSearch=${param.userSearch}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<br>
<table border="1px" id="phoneTable" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <td>Image</td>
        <td>Brand
            <a href="${pageContext.request.contextPath}/productList?sort=BRAND&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=BRAND&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Model
            <a href="${pageContext.request.contextPath}/productList?sort=MODEL&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=MODEL&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Display size
            <a href="${pageContext.request.contextPath}/productList?sort=DISPLAY_SIZE&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=DISPLAY_SIZE&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Price
            <a href="${pageContext.request.contextPath}/productList?sort=PRICE&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=PRICE&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="phone" items="${bookPage}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/productDetails/${phone.id}">
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                </a>
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>${phone.displaySizeInches}</td>
            <td><p id=${phone.id}p>${phone.price}</p></td>
            <td>
                <form:form method="post" modelAttribute="cartItem"
                           action="${pageContext.request.contextPath}/ajaxCart">
                <form:input path="itemQuantity" id="${phone.id}q"/>
                <form:errors path="itemQuantity" cssClass="error"/></td>
            <td><form:button id="${phone.id}">Add</form:button>
                </form:form></td>
            </td>
        </tr>
    </c:forEach>
</table>

</template:page>