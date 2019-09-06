<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<template:page>
    <a href="${pageContext.request.contextPath}/productList/page/1">back to product list</a>


    <div class="container">
        <div class="row">
            <div class="col-sm-10">
                <h1>${phone.model} (ID#${phone.id})</h1>
            </div>
        </div>


        <div>
            <div>
                <div>
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"/>
                </div>
                <div>
                        ${phone.description}
                </div>

            </div>

            <div>
                <form:form method="post" modelAttribute="cartItem"
                           action="${pageContext.request.contextPath}/ajaxCart">
                    <form:input path="itemQuantity" type="text" id="${phone.id}q"/>
                    <form:errors path="itemQuantity"/>
                    <form:button id="${phone.id}">Add</form:button>
                </form:form>
            </div>
            <div>

                <div>
                    <div>
                        <table border="1px">
                            <tr>
                                <th>announced</th>
                                <td>${phone.announced}</td>
                            </tr>
                            <tr>
                                <th>brand</th>
                                <td>${phone.brand}</td>
                            </tr>
                            <tr>
                                <th>model</th>
                                <td>${phone.model}</td>
                            </tr>
                            <tr>
                                <th>type</th>
                                <td>${phone.deviceType}</td>
                            </tr>
                            <tr>
                                <th>os</th>
                                <td>${phone.os}</td>
                            </tr>
                            <tr>
                                <th>camera back</th>
                                <td>${phone.backCameraMegapixels}</td>
                            </tr>
                            <tr>
                                <th>camera front</th>
                                <td>${phone.frontCameraMegapixels}</td>
                            </tr>
                            <tr>
                                <th>ram</th>
                                <td>${phone.ramGb}</td>
                            </tr>
                            <tr>
                                <th>storage</th>
                                <td>${phone.internalStorageGb}</td>
                            </tr>
                            <tr>
                                <th>price</th>
                                <td><p id=${phone.id}p>${phone.price}</p></td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <tabl>
                            <c:choose>
                                <c:when test="${empty phone.colors}">
                                    <tr>
                                        <th>colors not available</th>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th>available colors:</th>
                                    </tr>
                                    <c:forEach var="color" items="${phone.colors}">
                                        <tr>
                                            <td>${color.code}</td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tabl>
                    </div>
                    <div>
                        <table border="1px">
                            <tr>
                                <th>display size</th>
                                <td>${phone.displaySizeInches}</td>
                            </tr>
                            <tr>
                                <th>display resolution</th>
                                <td>${phone.displayResolution}</td>
                            </tr>
                            <tr>
                                <th>density</th>
                                <td>${phone.pixelDensity}</td>
                            </tr>
                            <tr>
                                <th>display tech</th>
                                <td>${phone.displayTechnology}</td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <table border="1px">
                            <tr>
                                <th>weight</th>
                                <td>${phone.weightGr}</td>
                            </tr>
                            <tr>
                                <th>length</th>
                                <td>${phone.lengthMm}</td>
                            </tr>
                            <tr>
                                <th>width</th>
                                <td>${phone.widthMm}</td>
                            </tr>
                            <tr>
                                <th>height</th>
                                <td>${phone.heightMm}</td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <table border="1px">
                            <tr>
                                <th>battery capacity</th>
                                <td>${phone.batteryCapacityMah}</td>
                            </tr>
                            <tr>
                                <th>talk hours</th>
                                <td>${phone.talkTimeHours}</td>
                            </tr>
                            <tr>
                                <th>stand by time hours</th>
                                <td>${phone.standByTimeHours}</td>
                            </tr>
                            <tr>
                                <th>bluetooth</th>
                                <th>${phone.bluetooth}</th>
                            </tr>
                            <tr>
                                <th>positioning</th>
                                <td>${phone.positioning}</td>
                            </tr>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>


</template:page>
