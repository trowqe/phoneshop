<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<p>
    Hello from product list!
</p>

<div class="input-group">
    <input type="text" class="form-control" id="searchInput"
           placeholder="Search for phone.."/>
</div>

<p>
    Found
    <c:out value="${phones.size()}"/> phones.
</p>
<table border="1px" id="phoneTable">
    <thead>
    <tr>
        <td>Image</td>
        <td>Brand
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </button>
        </td>
        <td>Model
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </button>
        </td>
        <td>Color</td>
        <td>Display size
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </button>
        </td>
        <td>Price
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-down"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-arrow-up"></span>
            </button>
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

</body>
</html>