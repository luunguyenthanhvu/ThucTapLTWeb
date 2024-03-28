<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 26/03/2024
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <%@ page isELIgnored="false" %>
    <title>Thông tin người dùng</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/animate.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/owl.carousel.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/owl.theme.default.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/magnific-popup.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/aos.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/ionicons.min.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/bootstrap-datepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/jquery.timepicker.css">


    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/web-css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/icomoon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/fix.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/user-css/user-profile.css">

</head>
<body class="goto-here">
<nav class="navbar-container navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container navbar-container">
        <div class="navbar-brand">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Cửa Hàng Trái Cây</a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="navbar-account">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Thông tin người dùng</a>
                <div class="dropdown-menu account-menu" aria-labelledby="dropdown04">
                    <a class="account dropdown-item" href="${pageContext.request.contextPath}/page/user/user-profile">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/></svg>
                        Người Dùng
                    </a>
                    <a class="account dropdown-item" href="${pageContext.request.contextPath}/page/logout">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/></svg>
                        Đăng Xuất
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>

<style>
  .navbar-brand {
    margin-right: 250px;
  }

  .nav-item dropdown {
    margin-left: 250px;
  }
</style>

<!-- END nav -->

<div class="main-user-content" style="background-color: #e7e6e6; width: 100%">
    <div class="container">
        <div class="container-child-left">
            <div class="quan-ly-user">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M448 160H320V128H448v32zM48 64C21.5 64 0 85.5 0 112v64c0 26.5 21.5 48 48 48H464c26.5 0 48-21.5 48-48V112c0-26.5-21.5-48-48-48H48zM448 352v32H192V352H448zM48 288c-26.5 0-48 21.5-48 48v64c0 26.5 21.5 48 48 48H464c26.5 0 48-21.5 48-48V336c0-26.5-21.5-48-48-48H48z"/></svg>
                <span>Quản lý người dùng</span>
            </div>
            <ul>
                <li class="active">
                    <a href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M256 288A144 144 0 1 0 256 0a144 144 0 1 0 0 288zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z"/></svg>
                        Thông tin người dùng
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/page/user/update-info?id=${user.getId()}">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M402.6 83.2l90.2 90.2c3.8 3.8 3.8 10 0 13.8L274.4 405.6l-92.8 10.3c-12.4 1.4-22.9-9.1-21.5-21.5l10.3-92.8L388.8 83.2c3.8-3.8 10-3.8 13.8 0zm162-22.9l-48.8-48.8c-15.2-15.2-39.9-15.2-55.2 0l-35.4 35.4c-3.8 3.8-3.8 10 0 13.8l90.2 90.2c3.8 3.8 10 3.8 13.8 0l35.4-35.4c15.2-15.3 15.2-40 0-55.2zM384 346.2V448H64V128h229.8c3.2 0 6.2-1.3 8.5-3.5l40-40c7.6-7.6 2.2-20.5-8.5-20.5H48C21.5 64 0 85.5 0 112v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V306.2c0-10.7-12.9-16-20.5-8.5l-40 40c-2.2 2.3-3.5 5.3-3.5 8.5z"/></svg>
                        Chỉnh sửa thông tin
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/page/user/update-pass?id=${user.getId()}">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M336 352c97.2 0 176-78.8 176-176S433.2 0 336 0S160 78.8 160 176c0 18.7 2.9 36.8 8.3 53.7L7 391c-4.5 4.5-7 10.6-7 17v80c0 13.3 10.7 24 24 24h80c13.3 0 24-10.7 24-24V448h40c13.3 0 24-10.7 24-24V384h40c6.4 0 12.5-2.5 17-7l33.3-33.3c16.9 5.4 35 8.3 53.7 8.3zM376 96a40 40 0 1 1 0 80 40 40 0 1 1 0-80z"/></svg>
                        Đổi mật khẩu
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/page/bill/list-bill">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z"/></svg>
                        Danh sách hóa đơn
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/page/cart">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                            <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
                        Giỏ Hàng của bạn
                    </a>
                </li>
            </ul>
        </div>



        <div class="container-child-right">
            <h4>Thông Tin Tài Khoản</h4>
            <div class="line_of_account"></div>
            <span style="font-size: 20px ">Thông Tin Cơ Bản</span>
            <br>
            <br>

            <div class="user-profile" style="margin-right: 50px">
                <div class="user-basic-info">
                    <table style="border-collapse:collapse;
                    border: none; ">
                        <tr>
                            <td><label for="ten_nd">Tên người dùng <span style="color: red">*</span></label></td>
                            <td><span style="position: relative; top: -5px; margin-left: 80px"  id="ten_nd">${user.getUsername()}</span></td>
                        </tr>
                        <td><br></td>

                        <tr>
                            <td><label for="email_nd">Email <span style="color: red">*</span></label></td>
                            <td><span style="position: relative; top: -5px; margin-left: 80px" id="email_nd" type="text">${user.getEmail()}
                            </span></td>
                        </tr>
                        <td><br></td>


                        <td><br></td>

                        <tr>
                            <td><label for="gender">Giới tính <span style="color: red">*</span></label></td>
                            <td><span style="position: relative; top: -5px; margin-left: 80px"  id="gender">${user.getSexual()}</span></td>
                        </tr>
                        <td><br></td>
                        <tr>
                            <td><label for="dc_nd">Địa chỉ <span style="color: red">*</span></label></td>
                            <td><span style="position: relative; top: -5px;margin-left: 80px" id="dc_nd" type="text">${user.getAddress()}</span></td>
                        </tr>
                        <td><br></td>


                        <tr>
                            <td><label for="sdt_nd">Số điện thoại <span style="color: red">*</span></label></td>

                            <td><span style="position: relative; top: -5px;margin-left: 80px" id="sdt_nd">${user.getPhoneNumber()}</span></td>
                        </tr>
                        <td><br></td>
                        <tr>
                            <td><label for="sn_nd">Sinh nhật <span
                                    style="color: red">*</span></label></td>

                            <td><span style="position: relative; top: -5px;margin-left: 80px" id="sn_nd"> ${user.getDateOfBirth()}</span></td>

                        </tr>
                        <td><br></td>
                    </table>
                </div>
                <div class="anhNguoiDung">
                    <c:choose>
                        <c:when test="${not empty user.getImg()}">
                            <!-- Ảnh đã chỉnh sửa -->
                            <img  style="width: 170px; height:170px; object-fit:cover" src="${user.getImg()}" alt="">
                        </c:when>
                        <c:otherwise>
                            <!-- Ảnh mặc định khi mới đăng ký -->
                            <img style="width: 170px; height:170px; object-fit:cover" src="${pageContext.request.contextPath}/static/images/accountPicture.png" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <p style="color: red;padding: 30px"> ${result}</p>
        </div>

    </div>
</div>


<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>


<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.easing.1.3.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.stellar.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aos.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.animateNumber.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/scrollax.min.js"></script>
<script src="${pageContext.request.contextPath}/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="${pageContext.request.contextPath}/static/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
