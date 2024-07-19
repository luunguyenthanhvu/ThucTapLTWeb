<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 18/11/2023
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>Đăng nhập tài khoản</title>
    <!-- custom-theme -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Elegant Login Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //custom-theme  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login-css/style.css">
    <!-- font-awesome icons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login-css/font-awesome.css">
    <!-- //font-awesome icons -->
    <link type="text/css" href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
</head>
<body>
<div class="login-form w3_form">
    <!--  Title-->
    <div class="login-title w3_title">

    </div>
    <div class="login w3_login">
        <h2 class="login-header w3_header">Đăng nhập</h2>
        <div class="w3l_grid">
            <form class="login-container" action="${pageContext.request.contextPath}/page/login" method="post">
                <input style="color: black" type="email" placeholder="Email" name="email" id="email_nd"
                       value="${email_user}">
                <span class="error" id="email-error"
                      style="display: none;color: red; font-size: 14px"></span>
                <c:if test="${not empty error_email}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_email}</p>
                </c:if>

                <input style="color: black" type="password" placeholder="Mật khẩu" name="password" id="password_nd"
                       value="${pass_user}">
                <span class="error" id="password-error"
                      style="display: none;color: red; font-size: 14px"></span>
                <c:if test="${not empty error_password}">
                    <p style="color: red; padding: 10px; text-align: center"> ${error_password}</p>
                </c:if>

                <input id="submit-btn" type="submit" value="Đăng nhập">
                <c:if test="${not empty result}">
                    <p style="color: red;padding: 10px; text-align: center"> ${result}</p>
                </c:if>
            </form>

            <div class="second-section w3_section">
                <h3 style="text-align: center; padding-top: 20px">Hoặc đăng nhập với</h3>
                <div class="social-links w3_social" style="padding-top: 20px">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/LoginGoogleHandler&response_type=code
           &client_id=174314995784-lgfhhc5fpekub7nmg5plrovb89j9n2r5.apps.googleusercontent.com&approval_prompt=force">
                        <img class="img-login"
                             style=" width: 30px;height: 30px;box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);border-radius: 100px;"
                             src="${pageContext.request.contextPath}/static/images/google-login.png" alt="">
                    </a>
                </div>
            </div>

            <div class="bottom-text w3_bottom_text">
                <p>Bạn mới biết đến Shop?<a href="${pageContext.request.contextPath}/page/login/register">Đăng ký</a></p>
                <h4><a href="${pageContext.request.contextPath}/page/login/forget-password">Quên mật khẩu?</a></h4>
            </div>

        </div>
    </div>

</div>
<div class="footer-w3l">

</div>
<script>
    // validate for input
    var email = document.getElementById("email_nd");
    var password = document.getElementById("password_nd");

    function validateEmail() {
        var text = email.value;
        var kyTuHopLe = /^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$/;
        var error = document.getElementById("email-error");
        if (text.length == 0 ||  text == null) {
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
        var text = document.getElementById("password_nd").value;
        var error = document.getElementById("password-error");

        if (text.length == 0 || text == null) {
            error.textContent = "Vui lòng nhập mật khẩu";
            error.style.display = "block";
            return false;
        } else {
            error.style.display = "none";
            return true;
        }
    }

    // add event to check input
    email.addEventListener("blur", validateEmail);
    password.addEventListener("blur", validatePassword);

    var submitBtn = document.getElementById("submit-btn");
    submitBtn.addEventListener("click", function (event) {
        var isEmail = validateEmail();
        var isPass = validatePassword();

        if (!isEmail || !isPass ) {
            event.preventDefault();
        }
    })
</script>
</body>
</html>
