<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<p id="id"></p>

<p>
    Found
    <c:out value="${phones.size()}"/> phones.
</p>
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
        <td>Color</td>
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
    <c:forEach var="phone" items="${phones}">
        <tr>
            <td>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>
                <c:forEach var="color" items="${phone.colors}">
                    ${color.code},
                </c:forEach>
            </td>
            <td>${phone.displaySizeInches}</td>
            <td>${phone.price}</td>
            <td><input type="text" name="quantity" id="${phone.id}" value="1"></td>
            <td><input type="button" value="Add to" onClick="addToCart('${phone.id}', '${phone.price}')"></td>
        </tr>
    </c:forEach>
</table>

<script>
    $(document).ready(function () {
        $('#phoneTable').DataTable({
            "pagingType": "full_numbers",
            "bFilter": false,
            "ordering": false
        });
    });
</script>
<script>
    function addToCart(phoneId, phonePrice) {

        var quantityBefore = parseInt(document.getElementById("totalQuantity").innerText);
        var totalSumBefore = parseFloat(document.getElementById("totalSum").innerText);
        var quantity =  parseInt(document.getElementById(phoneId).value)
        var quantityAfter = quantity + quantityBefore;
        var totalSumAfter = totalSumBefore + (parseFloat(phonePrice) * quantity) ;

        document.getElementById("totalQuantity").innerHTML = quantityAfter;
        document.getElementById("totalSum").innerHTML = totalSumAfter;

        $.getJSON(
            "ajaxCart",
            {phoneId: phoneId, quantity: quantity},
            function (data) {
                alert('ok' + data);
            }
        )


    }

</script>

</body>
</html>