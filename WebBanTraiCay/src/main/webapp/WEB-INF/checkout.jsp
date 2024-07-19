<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20/12/2023
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="vi_VN"/>
    <title>Cửa hàng trái cây</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap"
          rel="stylesheet">

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web-css/payment.css">
</head>
<body class="goto-here">
<nav class="navbar-container navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
     id="ftco-navbar">
    <div class="container navbar-container">
        <div class="navbar-brand">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/home">Cửa Hàng
                Trái
                Cây</a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="nav-bar-link" id="ftco-nav">
            <ul class="navbar-nav">
                <li class="nav-item active"><a href="${pageContext.request.contextPath}/page/home"
                                               class="nav-link">Trang Chủ</a></li>
                <li class="nav-item"><a
                        href="${pageContext.request.contextPath}/page/shop/shop-forward"
                        class="nav-link">Cửa Hàng</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/page/about"
                                        class="nav-link">Về Chúng Tôi</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/page/contact"
                                        class="nav-link">Liên Hệ</a></li>
                <li class="nav-item cta cta-colored">
                    <a href="${pageContext.request.contextPath}/page/cart"
                       class="nav-link cart-info-container">
                        <span class="icon-shopping_cart"></span>
                        [<span class="cart-total-amount"></span>]
                    </a>
                </li>

            </ul>
        </div>

        <div class="navbar-account">
            <c:choose>
                <c:when test="${not empty loginedUser}">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown05"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <b>${loginedUser.getUsername()}</b>
                        </a>
                        <div class="dropdown-menu account-menu" aria-labelledby="dropdown04">
                            <a class="account dropdown-item"
                               href="${pageContext.request.contextPath}/page/user/user-profile">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                     viewBox="0 0 448 512">
                                    <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
                                </svg>
                                Thông tin
                            </a>
                            <a class="account dropdown-item"
                               href="${pageContext.request.contextPath}/page/logout">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                     viewBox="0 0 512 512">
                                    <path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
                                </svg>
                                Đăng Xuất
                            </a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="login-user">
                        <a class="account" href="${pageContext.request.contextPath}/page/login">
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                 viewBox="0 0 512 512">
                                <path d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/>
                            </svg>
                            Đăng Nhập
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
<div id="toast">
</div>
<!-- END nav -->

<div class="hero-wrap hero-bread" style="background-image: url('/static/images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <h1 class="mb-0 bread">THỦ TỤC THANH TOÁN</h1>
            </div>
        </div>
    </div>
</div>

<form method="post" action="" class="billing-form">

    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-7 ftco-animate">

                    <h3 class="mb-4 billing-heading">Chi tiết thanh toán</h3>
                    <div class="row align-items-end">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="lastname">Họ <span style="color: red">*</span></label>
                                <input name="ho_nguoi-dung" style="color: black !important;"
                                       id="lastname" type="text" class="form-control"
                                       placeholder="Họ" value="${lastName}">
                                <p style="color: red; display: none" id="lastname_error"></p>
                                <c:if test="${not empty lastNameError}">
                                    <p style="color: red">${lastNameError}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="firstname">Tên <span style="color: red">*</span></label>
                                <input name="ten_nguoi-dung" style="color: black !important;"
                                       id="firstname" type="text" class="form-control"
                                       placeholder="Tên" value="${firstName}">
                                <p style="color: red; display: none" id="firstname_error"></p>
                                <c:if test="${not empty firstNameError}">
                                    <p style="color: red">${firstNameError}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Tỉnh / Thành phố <span
                                        style="color: red">*</span></label>
                                <div class="w-100"></div>
                                <select name="thanh-pho" id="provinces" class="form-control"
                                        style="color: black !important;">

                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Quận / Huyện <span
                                        style="color: red">*</span></label>
                                <div class="w-100"></div>
                                <select name="quan-huyen" id="districts" class="form-control"
                                        style="color: black !important;">

                                </select>


                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="street-address">Địa chỉ đường phố <span
                                        style="color: red">*</span></label>
                                <input name="dia-chi_nguoi-dung" style="color: black !important;"
                                       id="street-address" type="text" class="form-control"
                                       placeholder="Tên đường và số nhà" value="${address}">
                                <p style="color: red; display: none" id="address_error"></p>
                                <c:if test="${not empty addressError}">
                                    <p style="color: red">${addressError}</p>
                                </c:if>
                            </div>
                        </div>

                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="phone">Số điện thoại <span
                                        style="color: red">*</span></label>
                                <input name="sdt_nguoi-dung" style="color: black !important;"
                                       id="phone" type="text" class="form-control"
                                       placeholder="Số điện thoại" value="${phone}">
                                <p style="color: red; display: none" id="phone_error"></p>
                                <c:if test="${not empty phoneError}">
                                    <p style="color: red">${phoneError}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email">Email <span style="color: red">*</span></label>
                                <input name="email_nguoi-dung" style="color: black !important;"
                                       id="email" type="text" class="form-control"
                                       placeholder="email" value="${email}">
                                <p style="color: red; display: none" id="email_error"></p>
                                <c:if test="${not empty emailError}">
                                    <p style="color: red">${emailError}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="note_user">Ghi chú </label>
                                <textarea name="note_nguoi-dung" style="color: black !important;"
                                          id="note_user" type="text" class="form-control"
                                          placeholder="Ghi chú" value="${noteUser}" rows="8"
                                          cols="10">
                                      </textarea>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">

                            </div>
                        </div>

                    </div>

                </div>
                <div class="col-xl-5">
                    <div class="row mt-5 pt-3">
                        <div class="col-md-12 d-flex mb-5">
                            <div style="width: 600px;" class="cart-detail cart-total p-3 p-md-4">
                                <h3 class="billing-heading mb-4">Hóa đơn</h3>
                                <p class="d-flex">
                                    <span>Giá tiền</span>
                                    <span class="tong_phu">
                                        <fmt:formatNumber pattern="#,##0 ₫"
                                                          value="${sessionScope.subTotalPrice}"/>
                                    </span>
                                </p>

                                <p class="d-flex">
                                    <span>Phí vận chuyển</span>
                                    <span id="delivery_fee"></span>
                                    <input type="hidden" name="delivery_fee"
                                           id="hidden_delivery_fee" value="">
                                </p>
                                <hr>
                                <p class="d-flex total-price">
                                    <span>Tổng</span>
                                    <span class="tong_cong">
                                        <fmt:formatNumber pattern="#,##0 ₫"
                                                          value="${totalPrice}"/>
                                    </span>
                                </p>
                                <input type="hidden" id="idVoucher" name="idVoucher">
                            </div>
                        </div>


                        <!--                        Payment Details-->
                        <div class="col-md-12">
                            <div class="cart-detail p-3 p-md-4">
                                <h3 class="billing-heading mb-4">Phương thức thanh toán</h3>
                                <div class="select__payment">
                                    <div class="payment__choices-cd selected"
                                         id="payment__by-creditCard">
                                        <label for="credit_card">
                                            <svg style="position:relative;top:-10px;"
                                                 xmlns="http://www.w3.org/2000/svg" height="2em"
                                                 viewBox="0 0 576 512">
                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                                <path
                                                        d="M470.1 231.3s7.6 37.2 9.3 45H446c3.3-8.9 16-43.5 16-43.5-.2.3 3.3-9.1 5.3-14.9l2.8 13.4zM576 80v352c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V80c0-26.5 21.5-48 48-48h480c26.5 0 48 21.5 48 48zM152.5 331.2L215.7 176h-42.5l-39.3 106-4.3-21.5-14-71.4c-2.3-9.9-9.4-12.7-18.2-13.1H32.7l-.7 3.1c15.8 4 29.9 9.8 42.2 17.1l35.8 135h42.5zm94.4.2L272.1 176h-40.2l-25.1 155.4h40.1zm139.9-50.8c.2-17.7-10.6-31.2-33.7-42.3-14.1-7.1-22.7-11.9-22.7-19.2.2-6.6 7.3-13.4 23.1-13.4 13.1-.3 22.7 2.8 29.9 5.9l3.6 1.7 5.5-33.6c-7.9-3.1-20.5-6.6-36-6.6-39.7 0-67.6 21.2-67.8 51.4-.3 22.3 20 34.7 35.2 42.2 15.5 7.6 20.8 12.6 20.8 19.3-.2 10.4-12.6 15.2-24.1 15.2-16 0-24.6-2.5-37.7-8.3l-5.3-2.5-5.6 34.9c9.4 4.3 26.8 8.1 44.8 8.3 42.2.1 69.7-20.8 70-53zM528 331.4L495.6 176h-31.1c-9.6 0-16.9 2.8-21 12.9l-59.7 142.5H426s6.9-19.2 8.4-23.3H486c1.2 5.5 4.8 23.3 4.8 23.3H528z"/>
                                            </svg>
                                            <span style="position:relative;top:-5px; right:10px">Thẻ tín dụng</span>
                                        </label>

                                    </div>


                                    <div class="payment__choices-pp" id="payment__by-paypal">
                                        <label for="paypal">
                                            <svg style="position:relative;top:-10px;"
                                                 xmlns="http://www.w3.org/2000/svg" height="2em"
                                                 viewBox="0 0 576 512">
                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                                <path
                                                        d="M186.3 258.2c0 12.2-9.7 21.5-22 21.5-9.2 0-16-5.2-16-15 0-12.2 9.5-22 21.7-22 9.3 0 16.3 5.7 16.3 15.5zM80.5 209.7h-4.7c-1.5 0-3 1-3.2 2.7l-4.3 26.7 8.2-.3c11 0 19.5-1.5 21.5-14.2 2.3-13.4-6.2-14.9-17.5-14.9zm284 0H360c-1.8 0-3 1-3.2 2.7l-4.2 26.7 8-.3c13 0 22-3 22-18-.1-10.6-9.6-11.1-18.1-11.1zM576 80v352c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V80c0-26.5 21.5-48 48-48h480c26.5 0 48 21.5 48 48zM128.3 215.4c0-21-16.2-28-34.7-28h-40c-2.5 0-5 2-5.2 4.7L32 294.2c-.3 2 1.2 4 3.2 4h19c2.7 0 5.2-2.9 5.5-5.7l4.5-26.6c1-7.2 13.2-4.7 18-4.7 28.6 0 46.1-17 46.1-45.8zm84.2 8.8h-19c-3.8 0-4 5.5-4.2 8.2-5.8-8.5-14.2-10-23.7-10-24.5 0-43.2 21.5-43.2 45.2 0 19.5 12.2 32.2 31.7 32.2 9 0 20.2-4.9 26.5-11.9-.5 1.5-1 4.7-1 6.2 0 2.3 1 4 3.2 4H200c2.7 0 5-2.9 5.5-5.7l10.2-64.3c.3-1.9-1.2-3.9-3.2-3.9zm40.5 97.9l63.7-92.6c.5-.5.5-1 .5-1.7 0-1.7-1.5-3.5-3.2-3.5h-19.2c-1.7 0-3.5 1-4.5 2.5l-26.5 39-11-37.5c-.8-2.2-3-4-5.5-4h-18.7c-1.7 0-3.2 1.8-3.2 3.5 0 1.2 19.5 56.8 21.2 62.1-2.7 3.8-20.5 28.6-20.5 31.6 0 1.8 1.5 3.2 3.2 3.2h19.2c1.8-.1 3.5-1.1 4.5-2.6zm159.3-106.7c0-21-16.2-28-34.7-28h-39.7c-2.7 0-5.2 2-5.5 4.7l-16.2 102c-.2 2 1.3 4 3.2 4h20.5c2 0 3.5-1.5 4-3.2l4.5-29c1-7.2 13.2-4.7 18-4.7 28.4 0 45.9-17 45.9-45.8zm84.2 8.8h-19c-3.8 0-4 5.5-4.3 8.2-5.5-8.5-14-10-23.7-10-24.5 0-43.2 21.5-43.2 45.2 0 19.5 12.2 32.2 31.7 32.2 9.3 0 20.5-4.9 26.5-11.9-.3 1.5-1 4.7-1 6.2 0 2.3 1 4 3.2 4H484c2.7 0 5-2.9 5.5-5.7l10.2-64.3c.3-1.9-1.2-3.9-3.2-3.9zm47.5-33.3c0-2-1.5-3.5-3.2-3.5h-18.5c-1.5 0-3 1.2-3.2 2.7l-16.2 104-.3.5c0 1.8 1.5 3.5 3.5 3.5h16.5c2.5 0 5-2.9 5.2-5.7L544 191.2v-.3zm-90 51.8c-12.2 0-21.7 9.7-21.7 22 0 9.7 7 15 16.2 15 12 0 21.7-9.2 21.7-21.5.1-9.8-6.9-15.5-16.2-15.5z"/>
                                            </svg>
                                            <span style="position:relative;top:-5px; right:10px">Paypal</span>
                                        </label>

                                    </div>

                                </div>


                                <div id="component_soThe" class="form-group">
                                    <div class="col-md-12">
                                        <div class="radio">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="2em"
                                                 viewBox="0 0 576 512">
                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                                <path d="M64 32C28.7 32 0 60.7 0 96v32H576V96c0-35.3-28.7-64-64-64H64zM576 224H0V416c0 35.3 28.7 64 64 64H512c35.3 0 64-28.7 64-64V224zM112 352h64c8.8 0 16 7.2 16 16s-7.2 16-16 16H112c-8.8 0-16-7.2-16-16s7.2-16 16-16zm112 16c0-8.8 7.2-16 16-16H368c8.8 0 16 7.2 16 16s-7.2 16-16 16H240c-8.8 0-16-7.2-16-16z"/>
                                            </svg>
                                            <input type="text" id="cardNumberInput"
                                                   placeholder="Số thẻ">
                                            <p style="color: red" id="cardNum_error"></p>
                                        </div>
                                    </div>
                                </div>

                                <div id="component_tenNganHang" class="form-group">
                                    <div class="col-md-12">
                                        <div class="radio">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="2em"
                                                 viewBox="0 0 448 512">
                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                                <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
                                            </svg>
                                            <input type="text" id="cardCVCInput"
                                                   placeholder="Thẻ CVC">
                                            <p style="color: red; display: none"
                                               id="cardCVC_error"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12">
                                        <div class="radio">
                                            <input class="check" type="checkbox">
                                            <span> Tôi đã đọc và đồng ý
                                                các điều khoản và điều kiện</span>
                                        </div>
                                    </div>
                                </div>

                                <p>
                                    <button type="submit" id="submit-btn"
                                            class="btn btn-primary py-3 px-4">
                                        Đặt hàng
                                    </button>
                                </p>

                                <button style="display: none" type="button" id="paypal-btn"
                                        class="btn btn-primary py-3 px-4">
                                    Thanh Toán bằng Paypal
                                </button>

                            </div>
                        </div>
                    </div> <!-- .col-md-8 -->
                </div>

            </div>
        </div>
    </section> <!-- .section -->
</form><!-- END -->
<div class="containerVoucher">
    <button type="button" class="open-btn-voucher" id="openBtnVoucher">Chọn mã giảm giá</button>
    <div class="popup" id="popup">
        <div class="popup-content">
            <span class="close-btn" id="closeBtn">&times;</span>
            <h2>Chọn mã giảm giá</h2>
            <input type="text" placeholder="Nhập mã giảm giá" id="couponCode">
            <button class="apply-btn" id="applyBtnVoucher">Áp dụng</button>
            <div class="coupons" id="couponsList"></div>
            <div class="totalAfterUsingVoucher">
                <p>Giảm: <span id="discountVoucherPrice"></span></p>
                <p>Còn: <span id="priceAfterUseVoucher">0.000₫</span></p>
            </div>
            <button type="button" class="confirm-btn-voucher" id="confirmBtnVoucher">Đồng ý</button>
        </div>
    </div>

</div>

<footer class="ftco-footer ftco-section">
    <div class="container">
        <div class="row">
            <div class="mouse">
                <a href="#" class="mouse-icon">
                    <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                </a>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Trái cây tươi ngon</h2>
                    <p>Trúc xinh trúc mọc đầu đình, ai quen mua hoa quả lại càng thêm xinh.</p>
                    <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                        <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a>
                        </li>
                        <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a>
                        </li>
                        <li class="ftco-animate"><a href="#"><span
                                class="icon-instagram"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4 ml-md-5">
                    <h2 class="ftco-heading-2">Menu</h2>
                    <ul class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/page/shop/shop-forward"
                               class="py-2 d-block">Cửa hàng chúng tôi</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/about"
                               class="py-2 d-block">Về chúng tôi</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/contact"
                               class="py-2 d-block">Liên hệ với chúng tôi</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Help</h2>
                    <div class="d-flex">
                        <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                            <li><a href="#" class="py-2 d-block">Thông tin vận chuyển</a></li>
                            <li><a href="#" class="py-2 d-block">Hoàn trả và đổi sản phẩm</a></li>
                            <li><a href="#" class="py-2 d-block">Điều khoản và điều kiện</a></li>
                            <li><a href="#" class="py-2 d-block">Chính sách bảo mật</a></li>
                        </ul>
                        <ul class="list-unstyled">
                            <li><a href="#" class="py-2 d-block">
                                Câu hỏi thường gặp</a></li>
                            <li><a href="#" class="py-2 d-block">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Bạn có câu hỏi với chúng tôi ?</h2>
                    <div class="block-23 mb-3">
                        <ul>
                            <li><span class="icon icon-map-marker"></span><span class="text">Trường Đại Học Nông Lâm Thành Phố Hồ Chí Minh, Khu phố 6, Phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh</span>
                            </li>
                            <li><a href="#"><span class="icon icon-phone"></span><span
                                    class="text">028-38966780</span></a></li>
                            <li><a href="#"><span class="icon icon-envelope"></span><span
                                    class="text">pdaotao@hcmuaf.edu.vn</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">

                <p>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Bản quyền &copy;<script>document.write(new Date().getFullYear());</script>
                    Mọi quyền được bảo lưu | Mẫu này được thực hiện <i
                        class="icon-heart color-danger"
                        aria-hidden="true"></i> bởi <a
                        href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
            </div>
        </div>
    </div>
</footer>


<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4"
                stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4"
                stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>
<script>
  // validate for form order
  var ho = document.getElementById("lastname");
  var ten = document.getElementById("firstname");
  var diaChi = document.getElementById("street-address");

  var sdt = document.getElementById("phone");
  var email = document.getElementById("email");
  var soThe = document.getElementById("cardNumberInput");
  var theCVC = document.getElementById("cardCVCInput");

  function validateHo() {
    var text = ho.value;
    var kyTuHopLe = /^[a-zA-ZÀ-ỹ ]+$/;
    var error = document.getElementById("lastname_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Họ chỉ chứa ký tự chữ cái, khoảng trắng.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateTen() {
    var text = ten.value;
    var kyTuHopLe = /^[a-zA-ZÀ-ỹ ]+$/;
    var error = document.getElementById("firstname_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Tên chỉ chứa ký tự chữ cái, khoảng trắng.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateAddress() {
    var text = diaChi.value;
    var error = document.getElementById("address_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateSDT() {
    var text = sdt.value;
    var kyTuHopLe = /^(?:\+|0)[0-9]{6,14}[0-9]$/;
    var error = document.getElementById("phone_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Số điện thoại chỉ chứa số từ 0 - 9";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateEmail() {
    var text = email.value;
    var kyTuHopLe = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    var error = document.getElementById("email_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Địa chỉ email không hợp lệ.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateSoThe() {
    var text = soThe.value;
    var kyTuHopLe = /^\d{16}$/;
    var error = document.getElementById("cardNum_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!kyTuHopLe.test(text)) {
      error.textContent = "Số thẻ ngân hàng không hợp lệ. Vui lòng nhập 16 chữ số.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateTheCVC() {
    var text = theCVC.value;
    var cvcPattern = /^\d{3}$/;
    var error = document.getElementById("cardCVC_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else if (!cvcPattern.test(text)) {
      error.textContent = "Mã CVC không hợp lệ. Vui lòng nhập 3 chữ số.";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  // add validate for form
  ho.addEventListener("blur", validateHo);
  ten.addEventListener("blur", validateTen);
  diaChi.addEventListener("blur", validateAddress);
  sdt.addEventListener("blur", validateSDT);
  email.addEventListener("blur", validateEmail);
  soThe.addEventListener("blur", validateSoThe);
  theCVC.addEventListener("blur", validateTheCVC);

  ho.addEventListener("input", validateHo);
  ten.addEventListener("input", validateTen);
  diaChi.addEventListener("input", validateAddress);
  sdt.addEventListener("input", validateSDT);
  email.addEventListener("input", validateEmail);
  soThe.addEventListener("input", validateSoThe);
  theCVC.addEventListener("input", validateTheCVC);

  var form = document.querySelector('.billing-form');
  var submitBtn = document.getElementById("submit-btn");

  submitBtn.addEventListener("click", function (event) {
    var isHoValid = validateHo();
    var isTenValid = validateTen();
    var isDiaChiValid = validateAddress();
    var isSdtValid = validateSDT();
    var isEmailValid = validateEmail();
    var isSoTheValid = validateSoThe();
    var isTheCVCValid = validateTheCVC();
    var checkedButton = document.querySelector(".check");
    if (!isHoValid || !isTenValid || !isDiaChiValid || !isSdtValid || !isEmailValid || !isSoTheValid
        || !isTheCVCValid || !checkedButton.checked) {
      event.preventDefault();
    } else {
      form.action = '/page/order/check-out';
      form.submit();
    }
  });

  var paypalBtn = document.getElementById('paypal-btn');
  paypalBtn.addEventListener('click', function (event) {
    var isHoValid = validateHo();
    var isTenValid = validateTen();
    var isDiaChiValid = validateAddress();
    var isSdtValid = validateSDT();
    var isEmailValid = validateEmail();
    var checkedButton = document.querySelector(".check");

    if (!isHoValid || !isTenValid || !isDiaChiValid || !isSdtValid || !isEmailValid
        || !checkedButton.checked) {
      event.preventDefault();
    } else {
      form.action = '/paypal/authorize-payment';
      form.submit();
    }
  });


</script>

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
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
<script>
  // action for payment
  var creditCardLabel = document.querySelector("label[for='credit_card']");
  var paypalLabel = document.querySelector("label[for='paypal']");
  var paymentChoiceCreditCard = document.getElementById("payment__by-creditCard");
  var paymentChoicePayPal = document.getElementById("payment__by-paypal");

  // Khi người dùng click vào label Credit Card
  creditCardLabel.addEventListener("click", function () {
    paymentChoiceCreditCard.classList.add("selected");
    paymentChoicePayPal.classList.remove("selected");
    document.getElementById("paypal-btn").style.display = "none";
    document.getElementById("submit-btn").style.display = "block";
    document.getElementById("component_soThe").style.display = "block";
    document.getElementById("component_tenNganHang").style.display = "block";
  });

  // Khi người dùng click vào label PayPal
  paypalLabel.addEventListener("click", function () {
    paymentChoiceCreditCard.classList.remove("selected");
    paymentChoicePayPal.classList.add("selected");
    document.getElementById("paypal-btn").style.display = "block";
    document.getElementById("submit-btn").style.display = "none";
    document.getElementById("component_soThe").style.display = "none";
    document.getElementById("component_tenNganHang").style.display = "none";
  });

  // validation for form

  function cardNumber() {
    var num = document.getElementById('input');
    var card_number = arr[0].value;
    var card_cvc = arr[1].value;
  }

  function resetForm() {
    document.getElementById('input')[0].reset
  }


</script>
<script>

</script>

<script> var context = "${pageContext.request.contextPath}";</script>
<%--Js xử lý lấy dữ liệu vận chuyển--%>
<script src="${pageContext.request.contextPath}/static/js/web-js/process-delivery-fee.js?v=12"></script>
<script src="${pageContext.request.contextPath}/static/js/web-js/index-page.js?v=8"></script>
</body>
<%--Css cho Popup Voucher--%>
<style>
  .containerVoucher {
    position: absolute;
    top: 1000px;
    left: 750px;
    padding: 50px;
  }

  .open-btn-voucher {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }

  .popup {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    justify-content: center;
    align-items: center;
  }

  .popup-content {
    background-color: white;
    padding: 20px;
    border-radius: 5px;
    width: 550px;
    height: 700px;
    position: relative;

  }

  .close-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
  }

  h2 {
    margin-top: 0;
  }

  .close-btn {
    /* CSS cho nút đóng */
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
  }

  .coupon {
    /* CSS cho mỗi mã giảm giá */
    display: grid;
    grid-template-columns: auto 1fr auto;
    align-items: center;
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    gap: 10px;
    max-width: 100%; /* Đảm bảo container không vượt quá chiều rộng */

  }

  .coupon svg {
    width: 50px;
    height: 50px;
    margin-right: 10px;
  }

  .coupon input[type="radio"] {
    /* CSS cho nút radio */
    margin-right: 40px;
    justify-self: end; /* Đưa nút radio vào cuối cột */
  }

  .coupon-content {
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    max-width: calc(100% - 70px); /* Điều chỉnh chiều rộng để vừa khít */
    overflow: hidden; /* Đảm bảo nội dung không vượt quá chiều rộng */
  }

  .coupon-content div {
    margin-bottom: 5px;
    white-space: normal;
    word-wrap: break-word; /* Cho phép xuống dòng nếu chữ quá dài */
  }

  .coupon-content div.title {
    font-weight: bold;
  }

  .coupon-content div.content {
    font-size: 0.8em; /* Giảm kích thước chữ của content */
  }

  .coupon-content div.expiry {
    font-size: 0.8em; /* Giảm kích thước chữ của hạn sử dụng */
    color: #605b5b;
  }

  .coupon label {
    /* CSS cho nhãn của mã giảm giá */
    cursor: pointer;
  }

  .coupon label:hover {
    /* Hiệu ứng khi di chuột vào nhãn */
    text-decoration: underline;
  }

  #couponsList {
    max-height: 300px; /* Đặt chiều cao tối đa */
    overflow-y: auto; /* Thêm cuộn dọc */
    padding: 10px;
  }

  .totalAfterUsingVoucher {
    display: grid;
    justify-content: center;
    margin: 20px 0;
  }

  .apply-btn, .confirm-btn-voucher {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
    margin: 10px 0;
  }
</style>
</html>
