<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:page>
    <h1>order:</h1>
    <br>
    <a href="${pageContext.request.contextPath}/cartPage">back to cart</a>

    <div class="container">
        <h4>Order</h4>
        <c:choose>
            <c:when test="${not empty order.totalPrice}">
                <form:form method="post" action="${pageContext.request.contextPath}/order"
                           modelAttribute="order">
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
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="orderItem" items="${order.orderItems}" varStatus="tagStatus">
                                <tr>
                                    <td><form:hidden path="orderItems[${tagStatus.index}].phone.id"
                                                     readonly="true"/>
                                    <form:input path="orderItems[${tagStatus.index}].phone.brand"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.model"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.displaySizeInches"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.price"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].quantity" readonly="true"/>

                                        <c:set var="quantity" value="${orderItem.quantity}"/>
                                        <c:if test="${quantity < 1}">
                                            <p>this phone is out of stock now, please, delete it from cart>
                                            </p></c:if>

                                        <form:errors path="orderItems[${tagStatus.index}].quantity"/></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <table border="1px" width="40%" cellspacing="0"
                               class="table table-striped table-bordered table-hover">
                            <tr>
                                <td>Subtotal</td>
                                <td><form:input path="subtotal" readonly="true"></form:input></td>
                            </tr>
                            <tr>
                                <td>Delivery</td>
                                <td><form:input path="deliveryPrice" readonly="true"></form:input></td>
                            </tr>
                            <tr>
                                <td>TOTAL</td>
                                <td><form:input path="totalPrice" readonly="true"></form:input></td>
                            </tr>
                        </table>

                        <table border="1px" width="60%" cellspacing="0"
                               class="table table-striped table-bordered table-hover">
                            <tr>
                                <td> First Name</td>
                                <td><form:input path="firstName"/>
                                    <form:errors path="firstName"/></td>
                            </tr>
                            <tr>
                                <td>Last Name</td>
                                <td><form:input path="lastName"/>
                                    <form:errors path="lastName"/></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><form:input path="deliveryAddress"/>
                                    <form:errors path="deliveryAddress"/>
                                    <form:hidden path="status"/></td>
                            </tr>

                            <tr>
                                <td>Phone</td>
                                <td><form:input path="contactPhoneNo"/>
                                    <form:errors path="contactPhoneNo"/></td>
                            </tr>

                            <tr>
                                <td>Additional Info</td>
                                <td><form:input path="additionalInfo"/></td>
                            </tr>


                        </table>

                    </div>
                    <input type="submit" value="Make order" style="width: 100px;">
                </form:form>
                <br>

            </c:when>
            <c:otherwise>
                <div id="emptyCart">
                    <h3>you have no order</h3>
                </div>
            </c:otherwise>
        </c:choose>
    </div>


</template:page>
