<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:page>
    <h3>Thank u for your order</h3>
    <br/>
    <h1>order:</h1>
    <br>
    <a href="${pageContext.request.contextPath}/productList/">back to product list</a>

    <div class="container">
        <h4>your order number: ${order.id}</h4>
        <c:choose>
            <c:when test="${not empty order}">
                <form:form modelAttribute="order">
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
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.brand"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.model"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.displaySizeInches"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].phone.price"
                                                    readonly="true"/></td>
                                    <td><form:input path="orderItems[${tagStatus.index}].quantity" readonly="true"/>

                                    </td>
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
                                <td><form:input path="firstName" readonly="true"/>
                            </tr>
                            <tr>
                                <td>Last Name</td>
                                <td><form:input path="lastName" readonly="true"/>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><form:input path="deliveryAddress" readonly="true"/>
                            </tr>

                            <tr>
                                <td>Phone</td>
                                <td><form:input path="contactPhoneNo" readonly="true"/>
                            </tr>

                            <tr>
                                <td>Additional Info</td>
                                <td><form:input path="additionalInfo" readonly="true"/></td>
                            </tr>


                        </table>

                    </div>
                </form:form>
                <br>

            </c:when>
            <c:otherwise>
                <div>
                    <h3>you have no order</h3>
                </div>
            </c:otherwise>
        </c:choose>
    </div>


</template:page>
