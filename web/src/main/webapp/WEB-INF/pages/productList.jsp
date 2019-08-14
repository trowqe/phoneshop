<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>

</head>
<body>
<p>
    Hello from product list!
</p>

<form class="form-inline" action="${pageContext.request.contextPath}/productList" method="GET">
    <button type="submit" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-search"></span> Search
    </button>
    <input type="text" class="form-control" name="userSearch"
           placeholder="Search for phone.."/>
</form>

<p>
    Found
    <c:out value="${phones.size()}"/> phones.
</p>
<table border="1px" id="phoneTable" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <td>Image</td>
        <td>Brand
            <a href="${pageContext.request.contextPath}/productList?sort=BRAND&type=DESC">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=BRAND&type=ASC">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Model
            <a href="${pageContext.request.contextPath}/productList?sort=MODEL&type=DESC">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=MODEL&type=ASC">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Color</td>
        <td>Display size
            <a href="${pageContext.request.contextPath}/productList?sort=DISPLAY_SIZE&type=DESC">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=DISPLAY_SIZE&type=ASC">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </a>
        </td>
        <td>Price
            <a href="${pageContext.request.contextPath}/productList?sort=PRICE&type=DESC">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </a>
            <a href="${pageContext.request.contextPath}/productList?sort=PRICE&type=ASC">
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
            <td><input type="text" value="1"></td>
            <td><input type="button" value="Add to" onClick=""></td>
        </tr>
    </c:forEach>
</table>

<script>
    $(document).ready(function() {
        $('#phoneTable').DataTable( {
            "pagingType": "full_numbers",
            "bFilter": false,
            "ordering" : false
        } );
    } );
</script>

</body>
</html>