<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<template:page>

    <spring:url value="/resources/js/deletePhoneByIdFromCart.js" var="deleteFromCart"/>
    <script type="text/javascript" src="${deleteFromCart}">
    </script>


    <a href="${pageContext.request.contextPath}/productList/">back to product list</a>

    <div class="container">
        <h4>Cart</h4>
        <c:choose>
            <c:when test="${not empty phones}">
                <form:form method="post" action="${pageContext.request.contextPath}/cartPage"
                           modelAttribute="cartItemForm">
                    <input type="hidden" name="_method" value="PUT">
                    <div id="tablePhonesCart">
                        <table id="tableProductsCart" border="1px" width="100%" cellspacing="0"
                               class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr id="headerTable">
                                <td>Brand</td>
                                <td>Model</td>
                                <td>Display size</td>
                                <td>Price</td>
                                <td>Quantity</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="map" value="${cartItemsForm.cartItems}"/>
                            <c:forEach var="phone" items="${phones}">
                                <tr id="${phone.id}tr">
                                    <c:set var="id" value="${phone.id}"/>
                                    <td>${phone.brand}</td>
                                    <td>${phone.model}</td>
                                    <td>${phone.displaySizeInches}</td>
                                    <td>${phone.price}</td>
                                    <td>
                                        <c:set var="cartItemQuantity"
                                               value="${map.get(id).itemQuantity}"/>
                                        <form:input path="cartItems[${id}].itemQuantity" id="${phone.id}q"
                                                    value="${cartItemQuantity}"/>

                                        <form:errors path="cartItems[${id}].itemQuantity" cssClass="error"/></td>

                                    <td>
                                        <button type="button" form="${pageContext.request.contextPath}/ajaxCart/" id="deleteButton" name="${id}"/>delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <input type="submit" value="Update" formmethod="post" style="width: 100px;">
                </form:form>
                <br>
                <form>
                    <a href="${pageContext.request.contextPath}/order">order</a>
                </form>
            </c:when>
            <c:otherwise>
                <div id="emptyCart">
                    <h3>Cart is empty</h3>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</template:page>