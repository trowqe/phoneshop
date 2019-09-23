<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>


</head>
<body>

<div>
    <a href="${pageContext.request.contextPath}/cartPage/">
        <label>items: </label>
        <p id="totalQuantity">${cartView.totalItems}</p>
        <label>sum: </label>
        <p id="totalSum">${cartView.totalSum}<p>
    </a>

    <a href="${pageContext.request.contextPath}/admin/orders/">admin</a>
</div>


<jsp:doBody/>
</body>
</html>