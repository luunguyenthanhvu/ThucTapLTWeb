<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 08/07/2024
  Time: 6:07 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa số lượng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/style.css">

</head>
<body>
<div class="container">
    <h2 class="mt-5">Chỉnh sửa số lượng sản phẩm</h2>
    <form action="${pageContext.request.contextPath}/page/bill/detail" method="post" class="mt-3">
        <input type="hidden" name="id" value="${bill.id}">
        <input type="hidden" name="productId" value="${product.id}">
        <div class="form-group">
            <label for="quantity">Số lượng:</label>
            <input type="number" id="quantity" name="quantity" value="${bill.quantity}" class="form-control" min="1" required>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>
