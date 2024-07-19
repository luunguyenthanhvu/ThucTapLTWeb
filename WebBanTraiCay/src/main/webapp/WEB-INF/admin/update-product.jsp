<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 26/03/2024
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <%@ page isELIgnored="false" %>
    <meta charset="UTF-8">
    <title>Quản lý cửa hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin-css/style.css?v=2">


    <!-- Boxiocns CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin-css/add-product.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body onload="myFunction()" style="margin:0;">
<div id="loader"></div>
<div style="display:none;" id="myDiv" class="animate-bottom">
    <div class="sidebar close">
        <div class="logo-details">
            <i>
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
                    <path d="M36.8 192H603.2c20.3 0 36.8-16.5 36.8-36.8c0-7.3-2.2-14.4-6.2-20.4L558.2 21.4C549.3 8 534.4 0 518.3 0H121.7c-16 0-31 8-39.9 21.4L6.2 134.7c-4 6.1-6.2 13.2-6.2 20.4C0 175.5 16.5 192 36.8 192zM64 224V384v80c0 26.5 21.5 48 48 48H336c26.5 0 48-21.5 48-48V384 224H320V384H128V224H64zm448 0V480c0 17.7 14.3 32 32 32s32-14.3 32-32V224H512z"/>
                </svg>
            </i>
            <span class="logo_name" style="font-size: 19px;">Quản trị viên</span>
            <i class="btn-close-home" style="padding-left: 13px;padding-top: 10px;   display: none;">
                <svg xmlns="http://www.w3.org/2000/svg" height="0.8em" viewBox="0 0 384 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg>
            </i>
        </div>
        <ul class="nav-links">
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path d="M406.5 399.6C387.4 352.9 341.5 320 288 320H224c-53.5 0-99.4 32.9-118.5 79.6C69.9 362.2 48 311.7 48 256C48 141.1 141.1 48 256 48s208 93.1 208 208c0 55.7-21.9 106.2-57.5 143.6zm-40.1 32.7C334.4 452.4 296.6 464 256 464s-78.4-11.6-110.5-31.7c7.3-36.7 39.7-64.3 78.5-64.3h64c38.8 0 71.2 27.6 78.5 64.3zM256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zm0-272a40 40 0 1 1 0-80 40 40 0 1 1 0 80zm-88-40a88 88 0 1 0 176 0 88 88 0 1 0 -176 0z"/>
                            </svg>
                        </i>
                        <span class="link_name">Tài khoản</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Tài khoản</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/profile">Thông tin tài khoản</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/profile/update-pass">Đổi mật khẩu</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path d="M121 32C91.6 32 66 52 58.9 80.5L1.9 308.4C.6 313.5 0 318.7 0 323.9V416c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V323.9c0-5.2-.6-10.4-1.9-15.5l-57-227.9C446 52 420.4 32 391 32H121zm0 64H391l48 192H387.8c-12.1 0-23.2 6.8-28.6 17.7l-14.3 28.6c-5.4 10.8-16.5 17.7-28.6 17.7H195.8c-12.1 0-23.2-6.8-28.6-17.7l-14.3-28.6c-5.4-10.8-16.5-17.7-28.6-17.7H73L121 96z"/>
                            </svg>
                        </i>
                        <span class="link_name">Sản phẩm</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Chức năng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/product/product-list">Danh sách sản phẩm</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/product/add-new-product">Thêm sản phẩm</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/product/manage-expired">Sản phẩm hết hạn</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                                <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/>
                            </svg>
                        </i>
                        <span class="link_name">Đơn hàng</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Đơn hàng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/order/order-list">Quản lý đơn hàng</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                <path d="M160 80c0-26.5 21.5-48 48-48h32c26.5 0 48 21.5 48 48V432c0 26.5-21.5 48-48 48H208c-26.5 0-48-21.5-48-48V80zM0 272c0-26.5 21.5-48 48-48H80c26.5 0 48 21.5 48 48V432c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V272zM368 96h32c26.5 0 48 21.5 48 48V432c0 26.5-21.5 48-48 48H368c-26.5 0-48-21.5-48-48V144c0-26.5 21.5-48 48-48z"/>
                            </svg>
                        </i>
                        <span class="link_name">Doanh thu</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Doanh thu</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/monthly-revenue"> Doanh thu cửa hàng</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
                                <path d="M144 160A80 80 0 1 0 144 0a80 80 0 1 0 0 160zm368 0A80 80 0 1 0 512 0a80 80 0 1 0 0 160zM0 298.7C0 310.4 9.6 320 21.3 320H234.7c.2 0 .4 0 .7 0c-26.6-23.5-43.3-57.8-43.3-96c0-7.6 .7-15 1.9-22.3c-13.6-6.3-28.7-9.7-44.6-9.7H106.7C47.8 192 0 239.8 0 298.7zM320 320c24 0 45.9-8.8 62.7-23.3c2.5-3.7 5.2-7.3 8-10.7c2.7-3.3 5.7-6.1 9-8.3C410 262.3 416 243.9 416 224c0-53-43-96-96-96s-96 43-96 96s43 96 96 96zm65.4 60.2c-10.3-5.9-18.1-16.2-20.8-28.2H261.3C187.7 352 128 411.7 128 485.3c0 14.7 11.9 26.7 26.7 26.7H455.2c-2.1-5.2-3.2-10.9-3.2-16.4v-3c-1.3-.7-2.7-1.5-4-2.3l-2.6 1.5c-16.8 9.7-40.5 8-54.7-9.7c-4.5-5.6-8.6-11.5-12.4-17.6l-.1-.2-.1-.2-2.4-4.1-.1-.2-.1-.2c-3.4-6.2-6.4-12.6-9-19.3c-8.2-21.2 2.2-42.6 19-52.3l2.7-1.5c0-.8 0-1.5 0-2.3s0-1.5 0-2.3l-2.7-1.5zM533.3 192H490.7c-15.9 0-31 3.5-44.6 9.7c1.3 7.2 1.9 14.7 1.9 22.3c0 17.4-3.5 33.9-9.7 49c2.5 .9 4.9 2 7.1 3.3l2.6 1.5c1.3-.8 2.6-1.6 4-2.3v-3c0-19.4 13.3-39.1 35.8-42.6c7.9-1.2 16-1.9 24.2-1.9s16.3 .6 24.2 1.9c22.5 3.5 35.8 23.2 35.8 42.6v3c1.3 .7 2.7 1.5 4 2.3l2.6-1.5c16.8-9.7 40.5-8 54.7 9.7c2.3 2.8 4.5 5.8 6.6 8.7c-2.1-57.1-49-102.7-106.6-102.7zm91.3 163.9c6.3-3.6 9.5-11.1 6.8-18c-2.1-5.5-4.6-10.8-7.4-15.9l-2.3-4c-3.1-5.1-6.5-9.9-10.2-14.5c-4.6-5.7-12.7-6.7-19-3L574.4 311c-8.9-7.6-19.1-13.6-30.4-17.6v-21c0-7.3-4.9-13.8-12.1-14.9c-6.5-1-13.1-1.5-19.9-1.5s-13.4 .5-19.9 1.5c-7.2 1.1-12.1 7.6-12.1 14.9v21c-11.2 4-21.5 10-30.4 17.6l-18.2-10.5c-6.3-3.6-14.4-2.6-19 3c-3.7 4.6-7.1 9.5-10.2 14.6l-2.3 3.9c-2.8 5.1-5.3 10.4-7.4 15.9c-2.6 6.8 .5 14.3 6.8 17.9l18.2 10.5c-1 5.7-1.6 11.6-1.6 17.6s.6 11.9 1.6 17.5l-18.2 10.5c-6.3 3.6-9.5 11.1-6.8 17.9c2.1 5.5 4.6 10.7 7.4 15.8l2.4 4.1c3 5.1 6.4 9.9 10.1 14.5c4.6 5.7 12.7 6.7 19 3L449.6 457c8.9 7.6 19.2 13.6 30.4 17.6v21c0 7.3 4.9 13.8 12.1 14.9c6.5 1 13.1 1.5 19.9 1.5s13.4-.5 19.9-1.5c7.2-1.1 12.1-7.6 12.1-14.9v-21c11.2-4 21.5-10 30.4-17.6l18.2 10.5c6.3 3.6 14.4 2.6 19-3c3.7-4.6 7.1-9.4 10.1-14.5l2.4-4.2c2.8-5.1 5.3-10.3 7.4-15.8c2.6-6.8-.5-14.3-6.8-17.9l-18.2-10.5c1-5.7 1.6-11.6 1.6-17.5s-.6-11.9-1.6-17.6l18.2-10.5zM472 384a40 40 0 1 1 80 0 40 40 0 1 1 -80 0z"/>
                            </svg>
                        </i>
                        <span class="link_name">Người dùng</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Người dùng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/user/user-list">Danh sách người dùng</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/user/add-user">Thêm người dùng</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 576 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M64 32C46.3 32 32 46.3 32 64V304v48 80c0 26.5 21.5 48 48 48H496c26.5 0 48-21.5 48-48V304 152.2c0-18.2-19.4-29.7-35.4-21.1L352 215.4V152.2c0-18.2-19.4-29.7-35.4-21.1L160 215.4V64c0-17.7-14.3-32-32-32H64z"/></svg>
                        </i>
                        <span class="link_name">Nhà cung cấp</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="#">Nhà cung cấp</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/provider/provider-list">Danh sách nhà cung cấp</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/provider/add-provider">Thêm nhà cung cấp</a></li>
                </ul>
            </li>

            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M384 48c8.8 0 16 7.2 16 16V448c0 8.8-7.2 16-16 16H96c-8.8 0-16-7.2-16-16V64c0-8.8 7.2-16 16-16H384zM96 0C60.7 0 32 28.7 32 64V448c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V64c0-35.3-28.7-64-64-64H96zM240 256a64 64 0 1 0 0-128 64 64 0 1 0 0 128zm-32 32c-44.2 0-80 35.8-80 80c0 8.8 7.2 16 16 16H336c8.8 0 16-7.2 16-16c0-44.2-35.8-80-80-80H208zM512 80c0-8.8-7.2-16-16-16s-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V80zM496 192c-8.8 0-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V208c0-8.8-7.2-16-16-16zm16 144c0-8.8-7.2-16-16-16s-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V336z"/></svg>
                        </i>
                        <span class="link_name">Liên hệ</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/contact-form">Danh sách liên hệ của khách hàng</a></li>
                </ul>
            </li>
            <li>
                <div class="iocn-link">
                    <a href="#">
                        <i>
                            <svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 384 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill="#e9ecf1" d="M320 464c8.8 0 16-7.2 16-16V160H256c-17.7 0-32-14.3-32-32V48H64c-8.8 0-16 7.2-16 16V448c0 8.8 7.2 16 16 16H320zM0 64C0 28.7 28.7 0 64 0H229.5c17 0 33.3 6.7 45.3 18.7l90.5 90.5c12 12 18.7 28.3 18.7 45.3V448c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V64z"/></svg>
                        </i>
                        <span class="link_name">Quản lý log</span>
                    </a>
                    <i class='bx bxs-chevron-down arrow'></i>
                </div>
                <ul class="sub-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/log/log-center">Theo dõi và điều chỉnh log</a></li>
                </ul>
            </li>

            <li>
                <div class="profile-details">
                    <div class="profile-content">
                        <c:choose>
                            <c:when test="${not empty admin.getImg()}">
                                <!-- Ảnh mới từ sau khi đổi ảnh -->
                                <img src="${admin.getImg()}" alt="profileImg">
                            </c:when>
                            <c:otherwise>
                                <!-- Ảnh mặc định khi mới đăng ký -->
                                <img src="${pageContext.request.contextPath}/static/images/accountPicture.png" alt="profileImg">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="name-job">
                        <div class="profile_name">${loginedUser.getUsername()}</div>
                        <div class="job">${role}</div>
                    </div>
                    <a href="${pageContext.request.contextPath}/page/logout">
                        <i style="transform: rotate(180deg); ">
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path d="M502.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-128-128c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 224 192 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l210.7 0-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l128-128zM160 96c17.7 0 32-14.3 32-32s-14.3-32-32-32L96 32C43 32 0 75 0 128L0 384c0 53 43 96 96 96l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32l0-256c0-17.7 14.3-32 32-32l64 0z"/>
                            </svg>
                        </i>
                    </a>
                </div>
            </li>
        </ul>
    </div>

    <section style="min-height: 1000px" class="home-section">
        <div class="home-content">
            <i class='bx bx-menu'></i>
            <span class="text">Cập nhật sản phẩm</span>
        </div>
        <div class="container">
            <!--       code thêm ở đây-->
            <div class="form-sp">
                <form id="FormThemSanPham" action="${pageContext.request.contextPath}/admin/product/update-controller" method="post" enctype="multipart/form-data">
                    <table style="border-collapse:collapse;
                border: none; ">


          <tr>
            <td><label for="id_sp">Id <span style="color: red">*</span></label></td>
            <td><input name="id_san_pham" style="width: 300px" id="id_sp" type="text" value="${product.getId()}" disabled></td>
            <td><input type="hidden" name="id_san_pham" value="${product.getId()}"></td>
          </tr>
          <td><br></td>

          <tr>
            <td><label for="ten_sp">Tên sản phẩm <span style="color: red">*</span></label></td>
            <td><input name="ten_san_pham" style="width: 300px" id="ten_sp" type="text"  value="${product.getNameOfProduct()}"></td>
          </tr>
          <td><br></td>
          <td><p class="error" id="ten_sp_error"></td>
          <c:if test="${not empty ten_sp_error}" >
            <td><p style="color: red">${ten_sp_error}</p></td>
          </c:if>

          <tr>
            <td><label for="mota_sp">Mô tả sản phẩm <span style="color: red">*</span></label></td>
            <td><textarea cols="44" rows="10" id="mota_sp" name="mo_ta_san_pham"  >${product.getDescription()}</textarea></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="mota_sp_error"></td>
          <c:if test="${not empty mo_ta_error}" >
            <td><p style="color: red">${mo_ta_error}</p></td>
          </c:if>
          <tr>
            <td><label >Trái cây theo mùa <span style="color: red">*</span></label></td>
            <td>
              <select style="width: 300px" id="seasonalFruitSelect" name="selectedSeasonalFruit">
                <option value="spring">Mùa xuân</option>
                <option value="summer">Mùa hạ</option>
                <option value="fall">Mùa thu</option>
                <option value="winter">Mùa đông</option>
              </select>
            </td>
          </tr>
          <td><br></td>
          <tr>
            <td><label >Nguồn nhập<span style="color: red">*</span></label></td>
            <td>
              <select style="width: 300px" id="sourceImport" name="selectedSourceImport">
                <option value="local">Trong nước</option>
                <option value="imported">Ở nước ngoài</option>
              </select>
            </td>
          </tr>
          <td><br></td>
          <tr>
            <td><label >Trái cây khô<span style="color: red">*</span></label></td>
            <td>
              <select style="width: 300px" id="driedFruit" name="selectedDriedFruit">
                <option value="dried">Có</option>
                <option value="">Không</option>
              </select>
            </td>
          </tr>
          <td><br></td>
          <tr>
            <td><label for="giatien_sp">Giá tiền <span style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="gia_tien_san_pham" id="giatien_sp" type="text" value="${product.getPrice()}"></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="giatien_sp_error"></td>
          <c:if test="${not empty gia_tien_error}" >
            <td><p style="color: red">${gia_tien_error}</p></td>
          </c:if>
          <tr>
            <td><label for="kl_sp">Khối lượng sản phẩm <span style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="khoi_luong_san_pham" id="kl_sp" type="text" value="${product.getWeight()}"></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="kl_sp_error"></td>
          <c:if test="${not empty khoi_luong_errorr}" >
            <td><p style="color: red">${khoi_luong_error}</p></td>
          </c:if>
          <tr>
            <td><label for="kgMacDinh_sp">Số kg mặc định của sản phẩm <span
                    style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="so_kg_mac_dinh" id="kgMacDinh_sp" type="text"  value="${product.getWeightDefault()}"></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="kgMacDinh_sp_error"></td>
          <c:if test="${not empty khoi_luong_mac_dinh_error}" >
            <td><p style="color: red">${khoi_luong_mac_dinh_error}</p></td>
          </c:if>

          <tr>
            <td><label for="expired_day">Ngày nhập hàng <span style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="ngay_nhap_hang" id="imported_day" type="date"  value="${product.getDateOfImporting()}"></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="imported_day_error"></td>
          <c:if test="${not empty ngay_nhap_hang_error}" >
            <td><p style="color: red">${ngay_nhap_hang_error}</p></td>
          </c:if>
          <tr>
            <td><label for="expired_day">Ngày hết hạn <span style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="ngay_het_han" id="expired_day" type="date"  value="${product.getExpriredDay()}"></td>

          </tr>
          <td><br></td>
          <td><p class="error" id="expired_day_error"></td>
          <c:if test="${not empty ngay_het_han_error}" >
            <td><p style="color: red">${ngay_het_han_error}</p></td>
          </c:if>

          <tr>
            <td><label for="upfileAnh">Nhà cung cấp <span style="color: red">*</span></label></td>
            <td>
              <select style="width: 300px" id="providerSelect" name="selectedProviderID">
                <c:forEach items="${listProvider}" var="provider">
                  <option value="${provider.getId()}">${provider.getProviderName()}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <td><br></td>
          <td><p class="error" id="provider_product_error"></td>
          <c:if test="${not empty nha_cung_cap_error}" >
            <td><p style="color: red">${nha_cung_cap_error}</p></td>
          </c:if>
          <tr>
            <td><label for="upfileAnh">Up file ảnh sản phẩm <span style="color: red">*</span></label></td>
            <td><input style="width: 300px" name="file" id="upfileAnh" type="file"></td>
          </tr>


          <tr>
            <td></td>
            <td>
              <div class="product-img">
                <img id="previewImage" src="${product.getImg()}">
              </div>
            </td>
          </tr>

        </table>
        <button type="submit">
          Lưu thông tin
          <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
            <path d="M288 109.3V352c0 17.7-14.3 32-32 32s-32-14.3-32-32V109.3l-73.4 73.4c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3l128-128c12.5-12.5 32.8-12.5 45.3 0l128 128c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L288 109.3zM64 352H192c0 35.3 28.7 64 64 64s64-28.7 64-64H448c35.3 0 64 28.7 64 64v32c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V416c0-35.3 28.7-64 64-64zM432 456a24 24 0 1 0 0-48 24 24 0 1 0 0 48z"/></svg>
        </button>
        <button type="reset">
          Làm mới
          <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
            <path d="M463.5 224H472c13.3 0 24-10.7 24-24V72c0-9.7-5.8-18.5-14.8-22.2s-19.3-1.7-26.2 5.2L413.4 96.6c-87.6-86.5-228.7-86.2-315.8 1c-87.5 87.5-87.5 229.3 0 316.8s229.3 87.5 316.8 0c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0c-62.5 62.5-163.8 62.5-226.3 0s-62.5-163.8 0-226.3c62.2-62.2 162.7-62.5 225.3-1L327 183c-6.9 6.9-8.9 17.2-5.2 26.2s12.5 14.8 22.2 14.8H463.5z"/></svg>
        </button>
      </form>
    </div>
  </div>
</section>

</div>
<script>
  let arrow = document.querySelectorAll(".arrow");
  let closeSideBarBtn = document.querySelector(".btn-close-home");

  for (var i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e) => {
      let arrowParent = e.target.parentElement.parentElement; // Trở về phần tử cha của mũi tên
      arrowParent.classList.toggle("showMenu");
    });
  }

  let sidebar = document.querySelector(".sidebar");
  let sidebarBtn = document.querySelector(".bx-menu");

  sidebarBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close");

    // Sau khi toggle sidebar, kiểm tra và điều chỉnh hiển thị nút đóng sidebar
    if (!sidebar.classList.contains("close")) {
      closeSideBarBtn.style.display = "inline-block"; // Hiển thị nút đóng
    } else {
      closeSideBarBtn.style.display = "none"; // Ẩn nút đóng
    }
  });

  closeSideBarBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close");
    closeSideBarBtn.style.display = "none"; // Luôn ẩn nút đóng khi click để đóng sidebar
  });

  var myVar;

  function myFunction() {
    myVar = setTimeout(showPage, 800);
  }

  function showPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("myDiv").style.display = "block";
  }


  // validate for input
  var tenSP = document.getElementById("ten_sp");
  var moTaSP = document.getElementById("mota_sp");
  var giaTienSP = document.getElementById("giatien_sp");
  var khoiLuongSP = document.getElementById("kl_sp");
  var kgMacDinhSP = document.getElementById("kgMacDinh_sp");
  var nhaCC = document.getElementById("provider_product");
  var ngayNhapHang = document.getElementById("imported_day");
  var ngayHetHan = document.getElementById("expired_day");
  var upfileAnh = document.getElementById("upfileAnh");

  function validateTenSP() {
    var text = tenSP.value;
    var kyTuHopLe = /^[\p{L}\s']+$/u;
    var error = document.getElementById("ten_sp_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập vào tên sản phầm";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Tên trái cây chỉ chứa ký tự chữ cái, khoảng trắng.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateNhaCC() {
    var text = nhaCC.value;
    var error = document.getElementById("provider_product_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng chọn nhà cung cấp";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateNgayHetHan() {
    var inputNgayHetHan = document.getElementById("expired_day");
    var ngayHetHan = new Date(inputNgayHetHan.value);
    var now = new Date();

    var error = document.getElementById("expired_day_error");

    // Kiểm tra xem ngày hết hạn đã chọn hay chưa
    if (isNaN(ngayHetHan.getTime())) {
      error.textContent = "Vui lòng chọn ngày hết hạn.";
      error.style.display = "block";
      return false;
    }

    // Kiểm tra xem ngày hết hạn có sau ngày hiện tại không
    if (ngayHetHan <= now) {
      error.textContent = "Ngày hết hạn phải sau ngày hiện tại.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }
  function validateNgayNhapHang() {
    var inputNgayNhapHang = document.getElementById("imported_day");
    var ngayNhapHang = new Date(inputNgayNhapHang.value);
    var now = new Date();

    var error = document.getElementById("imported_day_error");

    // Kiểm tra xem ngày nhập hàng đã chọn hay chưa
    if (isNaN(ngayNhapHang.getTime())) {
      error.textContent = "Vui lòng chọn ngày nhập hàng.";
      error.style.display = "block";
      return false;
    }
    if (ngayNhapHang > now) {
      error.textContent = "Ngày nhập hàng phải trước ngày hiện tại.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }



  function validateKhoiLuongSP() {
    var text = khoiLuongSP.value;
    var error = document.getElementById("kl_sp_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập vào khối lượng nhập hàng.";
      error.style.display = "block";
      return false;
    } else if (isNaN(text) || text <= 0) {
      error.textContent = "Khối lượng nhập hàng chỉ chứa chữ số, không âm.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateKgMacDinhSP() {
    var text = kgMacDinhSP.value;
    var error = document.getElementById("kgMacDinh_sp_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập vào khối lượng mặc định.";
      error.style.display = "block";
      return false;
    } else if (isNaN(text) || text <= 0) {
      error.textContent = "Khối lượng mặc định chỉ chứa chữ số, không âm.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateGiaTienSP() {
    var text = giaTienSP.value;
    var error = document.getElementById("giatien_sp_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập vào giá tiền sản phầm.";
      error.style.display = "block";
      return false;
    } else if (isNaN(text) || text <= 0) {
      error.textContent = "Giá tiền sản phẩm chỉ chứa chữ số, không âm.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateMoTaSP() {
    var text = moTaSP.value;
    var kyTuHopLe = /^(?=.*[^\s]).*$/;
    var error = document.getElementById("mota_sp_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập vào mô tả sản phầm.";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Mô tả sản phẩm chỉ chứa chữ cái, chữ số.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  // add event to check input
  tenSP.addEventListener("blur", validateTenSP);
  moTaSP.addEventListener("blur", validateMoTaSP);
  giaTienSP.addEventListener("blur", validateGiaTienSP);
  khoiLuongSP.addEventListener("blur", validateKhoiLuongSP);
  kgMacDinhSP.addEventListener("blur", validateKgMacDinhSP);
  nhaCC.addEventListener("blur", validateNhaCC);
  ngayNhapHang.addEventListener("blur",validateNgayNhapHang)
  ngayHetHan.addEventListener("blur", validateNgayHetHan);


  // stop user send post to server
  var submitBtn = document.getElementById("submit_product_btn");
  submitBtn.addEventListener("click", function (event) {
    var isTenSPValid = validateTenSP();
    var isMoTaSPValid = validateMoTaSP();
    var isGiaTienValid = validateGiaTienSP();
    var isKhoiLuongSPValid = validateKhoiLuongSP();
    var isKgMacDinhSPValid = validateKgMacDinhSP();
    var isNhaCCValid = validateNhaCC();
    var isNgayHetHanValid = validateNgayHetHan();
    var isFileValid = validateFileUpload();
    if (!isTenSPValid || !isMoTaSPValid || !isGiaTienValid || !isKhoiLuongSPValid
        || !isKgMacDinhSPValid || !isNhaCCValid || !isNgayHetHanValid || !isFileValid) {
      event.preventDefault();
    }
  })
</script>
</body>
<script src="https://kit.fontawesome.com/4c38acb8c6.js" crossorigin="anonymous"></script>
</html>
