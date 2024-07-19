<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 26/03/2024
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <%@ page isELIgnored="false" %>
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
<!-- END nav -->

<div class="hero-wrap hero-bread"
     style="background-image: url('/static/images/bg1.jpg');filter: brightness(0.8);">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a
                        href="${pageContext.request.contextPath}/page/home">Trang chủ</a></span>
                    <span>Liên hệ</span>
                </p>
                <h1 class="mb-0 bread">Liên hệ</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section contact-section bg-light">
    <div class="container">
        <div class="row d-flex mb-5 contact-info">
            <div class="w-100"></div>
            <div class="col-md-3 d-flex">
                <div class="info bg-white p-4">
                    <p><span>Địa chỉ:</span>Trường Đại Học Nông Lâm Thành Phố Hồ Chí Minh, Khu phố
                        6, Phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh</p>
                </div>
            </div>
            <div class="col-md-3 d-flex">
                <div class="info bg-white p-4">
                    <p><span>SĐT:</span> <a href="tel://1234567920">+ 1235 2355 98</a></p>
                </div>
            </div>
            <div class="col-md-3 d-flex">
                <div class="info bg-white p-4">
                    <p><span>Email:</span> <a
                            href="mailto:info@yoursite.com">pdaotao@hcmuaf.edu.vn</a></p>
                </div>
            </div>
            <div class="col-md-3 d-flex">
                <div class="info bg-white p-4">
                    <p><span>Trang mạng</span> <a href="#">yoursite.com</a></p>
                </div>
            </div>
        </div>
        <div class="row block-9">
            <div class="col-md-6 order-md-last d-flex">
                <form action="${pageContext.request.contextPath}/page/contact"
                      class="bg-white p-5 contact-form" method="post">
                    <div class="form-group">
                        <input id="tenKhachHang" value="${tenNguoiDung}" type="text"
                               class="form-control" placeholder="Tên của bạn" name="ten">
                        <div style="color: red; display: none" id="name_error"></div>
                        <c:if test="${not empty tenError}">
                            <div style="color: red">${tenError}</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <input id="emailKhachHang" value="${emailNguoiDung}" type="text"
                               class="form-control" placeholder="Email của bạn" name="email">
                        <div style="color: red; display: none" id="email_error"></div>
                        <c:if test="${not empty emailError}">
                            <div style="color: red">${emailError}</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <input id="deTaiKhachHang" value="${deTaiNguoiDung}" type="text"
                               class="form-control" placeholder="Đề  tài" name="deTai">
                        <div style="color: red; display: none" id="deTai_error"></div>
                        <c:if test="${not empty deTaiError}">
                            <div style="color: red">${deTaiError}</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <textarea name="tinNhan" id="textKhachHang" cols="30" rows="7"
                                  class="form-control"
                                  placeholder="Tin nhắn">${tinNhanNguoiDung}</textarea>
                        <div style="color: red; display: none" id="text_error"></div>
                        <c:if test="${not empty tinNhanError}">
                            <div style="color: red">${tinNhanError}</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <input id="submit-btn" type="submit" value="Gửi tin nhắn"
                               class="btn btn-primary py-3 px-5">
                    </div>

                </form>
            </div>

            <div class="col-md-6 d-flex">
                <div>
                    <img style="width: 550px; height: 550px;object-fit: cover"
                         src="${pageContext.request.contextPath}/static/images/PhuongVy - Photo_ Thiên Hào.jpg"
                         alt="">
                </div>
            </div>
        </div>
    </div>
</section>

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

<%--Thông báo nếu khách hàng đã gửi liên hệ thành công--%>
<script>
  // Kiểm tra biến notify từ server
  var notify = '<%= request.getAttribute("notify") %>';

  // Nếu đặt hàng thành công, hiển thị thông báo và chuyển hướng trang
  if (notify !== 'null' && notify !== undefined && notify !== '') {
    alert(notify);
    window.location.href = "http://localhost:8080/page/contact";
  }
</script>
<script>
  var ten = document.querySelector('#tenKhachHang');
  console.log(ten);
  var email = document.querySelector('#emailKhachHang');
  var deTai = document.querySelector('#deTaiKhachHang');
  var text1 = document.querySelector('#textKhachHang');

  function validateTen() {
    var text = ten.value;
    console.log(text);
    var kyTuHopLe = /^[a-zA-ZÀ-ỹ ]+$/;
    var error = document.querySelector("#name_error");
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

  function validateEmail() {
    var text = email.value;
    var kyTuHopLe = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    var error = document.querySelector("#email_error");
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

  function validateDeTai() {
    var text = deTai.value;

    var error = document.querySelector("#deTai_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  function validateText() {
    var text = text1.value;

    var error = document.querySelector("#text_error");
    if (text.length == 0 || text == null) {
      error.textContent = "Vui lòng nhập dữ liệu";
      error.style.display = "block";
      return false;
    } else {
      error.style.display = "none";
      return true;
    }
  }

  ten.addEventListener("blur", validateTen)
  deTai.addEventListener("blur", validateDeTai)
  email.addEventListener("blur", validateEmail)
  text1.addEventListener("blur", validateText)
  var submitBtn = document.getElementById("submit-btn");
  submitBtn.addEventListener("click", function (event) {
    var isTen = validateTen();
    var isEmail = validateEmail();
    var isDeTai = validateDeTai();
    var isText = validateText();
    if (!isTen || !isEmail || !isDeTai || !isText) {
      event.preventDefault();
    }
  })

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
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="${pageContext.request.contextPath}/static/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
<script src="${pageContext.request.contextPath}/static/js/web-js/index-page.js?v=8"></script>

</body>
</html>
