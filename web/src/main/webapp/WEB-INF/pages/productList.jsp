<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
    <jsp:include page="header/header.jsp"/>
</head>
<body>
<form class="form-inline" action="${pageContext.request.contextPath}/productList" method="GET">
    <button type="submit" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-search"></span> Search
    </button>
    <input type="text" class="form-control" id="userSearch" name="userSearch"
           placeholder="Search for phone.."/>
</form>
<br>
<c:set var="one" value="${1}"/>
<c:set var="curPage" value="${bookPage.number + one}"/>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${bookPage.totalPages}" var="i">
            <c:choose>
                <c:when test="${bookPage.number eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}/productList/page/${i}">${i}</a></td>
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
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=BRAND&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=BRAND&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Model
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=MODEL&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=MODEL&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Display size
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=DISPLAY_SIZE&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=DISPLAY_SIZE&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Price
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=PRICE&type=DESC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList/page/${curPage}?sort=PRICE&type=ASC&userSearch=${param.userSearch}">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="phone" items="${bookPage.content}">
        <tr>
            <td>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>${phone.displaySizeInches}</td>
            <td><p id = ${phone.id}p>${phone.price}</p></td>
            <td>
                <form:form method="post" modelAttribute="cartItem" action="${pageContext.request.contextPath}/ajaxCart'" >
                    <form:input path="itemQuantity" type="text" id="${phone.id}q"/>
                    <form:errors path="itemQuantity"/>
                    <form:button id="${phone.id}" >Add</form:button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>


<script>
    $(document).ready(function () {

        $("button").click(function (e) {

            e.preventDefault();
            var phoneId =  parseInt(this.id);
            var quantity = parseInt(document.getElementById(this.id+'q').value);
            var price = parseFloat(document.getElementById(this.id+'p').textContent);
            var quantityBefore = parseInt(document.getElementById("totalQuantity").innerText);
            var totalSumBefore = parseFloat(document.getElementById("totalSum").innerText);

            var quantityAfter = parseInt(quantity + quantityBefore);
            var totalSumAfter = parseFloat(totalSumBefore + price * quantity);
            document.getElementById("totalQuantity").innerHTML = quantityAfter;
            document.getElementById("totalSum").innerHTML = totalSumAfter;

            var cartItem = {
                itemId: phoneId,
                itemQuantity: quantity
            };

            console.log(cartItem);

            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ajaxCart',
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(cartItem),
                success: function (data) {
                    console.log("post resp " + data);
                }, error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        });
    });


</script>


</body>
</html>