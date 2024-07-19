<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 18/11/2023
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>Đăng ký tài khoản</title>
    <!-- custom-theme -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Elegant Login Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //custom-theme  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login-css/style.css">
    <!-- font-awesome icons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login-css/font-awesome.css">
    <!-- //font-awesome icons -->
    <link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
</head>
<body>
<div class="login-form w3_form">
    <!--  Title-->
    <div class="login-title w3_title" >
    </div>
    <div class="login w3_login">
        <h2 class="login-header w3_header">Đăng ký</h2>
        <div class="w3l_grid">
            <form class="login-container" action="${pageContext.request.contextPath}/page/login/register" method="post">
                <input style="color: black" id="nameUser" type="text" placeholder="Tên người dùng" name="username" value="${name_user}">
                <span class="error" id="error_name" style="display: none;color: red; font-size: 14px;"></span>
                <c:if test="${not empty error_name}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_name}</p>
                </c:if>
                <input style="color: black" id="phoneUser" type="text" placeholder="Số điện thoại" name="phoneNum" value="${phone_user}">
                <span class="error" id="error_phone" style="display: none;color: red; font-size: 14px;"></span>
                <c:if test="${not empty error_phone}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_phone}</p>
                </c:if>
                <input style="color: black" id="addressUser"  type="text" placeholder="Địa chỉ" name="address" value="${address_user}">
                <span class="error" id="error_address" style="display: none;color: red; font-size: 14px;"></span>
                <c:if test="${not empty error_address}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_address}</p>
                </c:if>
                <input style="color: black" type="email" placeholder="Email" name="email" id="email_nd" value="${email_user}">
                <span class="error" id="email-error" style="display: none;color: red; font-size: 14px;"></span>
                <c:if test="${not empty error_email}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_email}</p>
                </c:if>

                <input style="color: black" type="password" placeholder="Mật khẩu" name="password" id="password_nd" value="${pass_user}">
                <span class="error" id="password-error"
                      style="display: none;color: red; font-size: 14px"></span>
                <c:if test="${not empty error_password}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_password}</p>
                </c:if>
                <c:if test="${not empty result}" >
                    <p style="color: red;padding: 10px; text-align: center"> ${result}</p>
                </c:if>
                <input id="submit-btn" type="submit" value="Đăng ký">
            </form>
            <div class="second-section w3_section">
            </div>

            <div class="bottom-text w3_bottom_text">
                <p>Bạn đã có tài khoản?<a href="${pageContext.request.contextPath}/page/login">Đăng nhập</a></p>
            </div>
        </div>
    </div>

</div>

<div class="footer-w3l">

</div>

<script>
    // validate for input
    var name1 = document.getElementById("nameUser");
    var phone1 = document.getElementById("phoneUser");
    var address1 = document.getElementById("addressUser");
    var email1 = document.getElementById("email_nd");
    var password1 = document.getElementById("password_nd");


    function validateName() {
        var text = name1.value;
        var kyTuHopLe = /^[a-zA-ZÀ-ỹ ]+$/;
        var error = document.getElementById("error_name");
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
    function validatePhone(){
        var text = phone1.value;
        var kyTuHopLe = /^(?:\+|0)[0-9]{6,14}[0-9]$/;
        var error = document.getElementById("error_phone");
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
    function validateAddress() {
        var text = address1.value;
        var error = document.getElementById("error_address");
        if (text.length == 0 || text == null) {
            error.textContent = "Vui lòng nhập dữ liệu";
            error.style.display = "block";
            return false;
        } else {
            error.style.display = "none";
            return true;
        }
    }
    function validateEmail() {
        var text = email1.value;
        var kyTuHopLe = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        var error = document.getElementById("email-error");
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

    function validatePassword() {
        var text = password1.value;
        var error = document.getElementById("password-error");

        if (text.length === 0) {
            error.textContent = "Vui lòng nhập mật khẩu";
            error.style.display = "block";
            return false;
        } else if (text.length < 6) {
            error.textContent = "Mật khẩu phải chứa ít nhất 6 ký tự";
            error.style.display = "block";
            return false;
        } else {
            error.style.display = "none";
            return true;
        }
    }


    // add event to check input
    name1.addEventListener("blur",validateName);
    phone1.addEventListener("blur",validatePhone);
    address1.addEventListener("blur",validateAddress);
    email1.addEventListener("blur", validateEmail);
    password1.addEventListener("blur", validatePassword);
    var submitBtn = document.getElementById("submit-btn");
    submitBtn.addEventListener("click", function (event) {
        var isName = validateName();
        var isPhone = validatePhone();
        var isAddress  = validateAddress();
        var isEmail = validateEmail();
        var isPass = validatePassword();
        if (!isName || !isPhone || !isAddress || !isEmail
            || !isPass ) {
            event.preventDefault();
        }
    })

</script>

</body>
</html>
