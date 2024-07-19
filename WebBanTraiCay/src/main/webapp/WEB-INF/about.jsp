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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/web-css/style.css">
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
<div id="toast">
</div>
<!-- END nav -->

<div class="hero-wrap hero-bread"
     style="background-image: url(/static/images/bg1.jpg);filter: brightness(0.8);">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a
                        href="${pageContext.request.contextPath}/page/home">Trang chủ</a></span>
                    <span>Về chúng tôi</span></p>
                <h1 class="mb-0 bread">Về chúng tôi</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section ftco-no-pb ftco-no-pt bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-5 p-md-5 img img-2 d-flex justify-content-center align-items-center"
                 style="background-image: url(/static/images/about.jpg);">
                <a href="https://vimeo.com/45830194"
                   class="icon popup-vimeo d-flex justify-content-center align-items-center">
                    <span class="icon-play"></span>
                </a>
            </div>
            <div class="col-md-7 py-5 wrap-about pb-md-5 ftco-animate">
                <div class="heading-section-bold mb-4 mt-md-5">
                    <div class="ml-md-0">
                        <h2 class="mb-4">Chào mừng bạn đến với cửa hàng trái cây online, trang web
                            của chúng tôi</h2>
                    </div>
                </div>
                <div class="pb-md-5">
                    <p>Dù Việt Nam có một nền kinh tế nông nghiệp phát triển, nhưng việc tiếp cận và
                        mua,
                        sắm trái cây tươi sạch và chất lượng không phải lúc nào cũng dễ dàng đối với
                        người tiêu dùng.
                        Để đáp ứng nhu cầu ngày càng cao của khách hàng về sự thuận tiện và đảm bảo
                        chất lượng,
                        trang web của chúng tôi xuất hiện để phục vụ nhu cầu cho bạn.
                    </p>
                    <p>Bạn có thể dễ dàng chọn lựa sản phẩm mà họ muốn, đặt hàng trực tuyến và
                        nhận được những quả trái cây tươi sức sống được giao hàng tận nơi. Ngoài ra,
                        chúng tôi
                        thường xuyên cập nhật các ưu đãi, khuyến mãi và chính sách hỗ trợ
                        khách hàng, tạo ra một trải nghiệm mua sắm trực tuyến an tâm và tiện
                        lợi.</p>
                    <p><a href="${pageContext.request.contextPath}/page/shop/shop-forward"
                          class="btn btn-primary">Xem cửa hàng</a></p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section testimony-section">
    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <span class="subheading">Bằng chứng</span>
                <h2 class="mb-4"> Khách hàng hài lòng của chúng tôi nói</h2>
            </div>
        </div>
        <div class="row ftco-animate">
            <div class="col-md-12">
                <div class="carousel-testimony owl-carousel">
                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5"
                                 style="background-image: url(/static/images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">Tôi đã có trải nghiệm mua trái cây qua một
                                    trang web ở Việt Nam và rất hài lòng với
                                    chất lượng sản phẩm. Trang web cung cấp thông tin chi tiết về
                                    nguồn gốc và quy trình chăm sóc trái cây,
                                    giúp tôi chọn lựa được những sản phẩm tươi ngon và an toàn. Quá
                                    trình đặt hàng và thanh toán đơn giản, và tôi nhận được giao
                                    hàng đúng
                                    hẹn. Sự chuyên nghiệp và chất lượng của dịch vụ này khiến tôi
                                    muốn quay lại mua sắm nhiều hơn.</p>
                                <p class="name">Garreth Smith</p>
                                <span class="position">Giám đốc tiếp thị</span>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5"
                                 style="background-image: url(/static/images/person_2.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">
                                    Đã lâu tôi không tin tưởng mua trái cây trực tuyến, nhưng sau
                                    khi thử nghiệm một trang web bán trái cây ở Việt Nam,
                                    tôi đã hoàn toàn thay đổi quan điểm. Sự đa dạng của sản phẩm và
                                    mô tả chi tiết giúp tôi lựa chọn được những loại trái cây ngon
                                    nhất.
                                    Giao hàng nhanh chóng và sản phẩm đến tay tôi vẫn giữ được độ
                                    tươi ngon và chất ượng như họ cam kết. Tôi sẽ giới thiệu trang
                                    web này
                                    cho bạn bè và gia đình của mình .</p>
                                <p class="name">Garreth Smith</p>
                                <span class="position">Nhà thiết kế giao diện</span>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5"
                                 style="background-image: url(/static/images/person_3.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">
                                    Trải nghiệm mua sắm trái cây trực tuyến tại một trang web ở Việt
                                    Nam đã mang lại cho tôi sự tiện lợi và an tâm.
                                    Hệ thống đặt hàng dễ sử dụng, và tôi có thể theo dõi đơn hàng
                                    của mình mọi lúc. Sản phẩm được đóng gói cẩn thận,
                                    giữ nguyên hương vị tươi ngon. Họ cũng có chăm sóc khách hàng
                                    tận tình, luôn sẵn sàng giải đáp mọi thắc mắc của tôi.
                                    Mua sắm trái cây trực tuyến qua trang web này là một trải nghiệm
                                    đáng giá và đáng tin cậy .</p>
                                <p class="name">Garreth Smith</p>
                                <span class="position">Nhà thiết kế giao diện người dùng</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row no-gutters ftco-services">
            <div class="col-lg-3 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-1 active d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-shipped"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">MIỄN PHÍ VẬN CHUYỂN</h3>
                        <span>ĐẶT HÀNG TRÊN $100</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-2 d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-diet"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">LUÔN TƯƠI</h3>
                        <span>GÓI SẢN PHẨM TỐT</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-3 d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-award"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">CHẤT LƯỢNG CAO</h3>
                        <span>CHẤT LƯỢNG SẢN PHẨM</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-4 d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-customer-service"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">ỦNG HỘ</h3>
                        <span>HỖ TRỢ 24/7</span>
                    </div>
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
            <div class="col-md">
                <div class="ftco-footer-widget mb-4 ml-md-5">
                    <h2 class="ftco-heading-2">Menu</h2>
                    <ul class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/page/shop/shop-forward"
                               class="py-2 d-block">Cửa
                            hàng chúng tôi</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/about"
                               class="py-2 d-block">Về chúng
                            tôi</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/contact"
                               class="py-2 d-block">Liên hệ với
                            chúng tôi</a></li>
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
<script src="${pageContext.request.contextPath}/static/js/web-js/index-page.js?v=8"></script>

</body>
</html>
