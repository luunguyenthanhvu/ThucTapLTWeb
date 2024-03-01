package nhom55.hcmuaf.controller.page.login;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import nhom55.hcmuaf.beans.RegisterBean;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.services.RegisterService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "register", value = "/page/login/register")
public class Register extends HttpServlet {

    private RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/login/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String phoneNum = request.getParameter("phoneNum");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (checkValidate(request, response,username,phoneNum,address, email, password)) {
            String newPassword = MyUtils.encodePass(password);
            String makeHash = MyUtils.createHash();

            RegisterBean register = new RegisterBean();
            register.setUsername(username);
            register.setPhoneNumber(phoneNum);
            register.setAddress(address);
            register.setEmail(email);
            register.setPassword(newPassword);
            register.setHash(makeHash);

            String str = registerService.RegisterUser(register);

            if (str.equals("SUCCESS")) {
                request.setAttribute("result", "Đăng ký thành công. Vui lòng kiểm tra email.");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/login/register.jsp");
                dispatcher.forward(request, response);
            } else if (str.equals("FAIL")) {
                request.setAttribute("result", "Tài khoản đã tồn tại");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/login/register.jsp");
                dispatcher.forward(request, response);
            }
        }
        // khong check validate
        else {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/login/register.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * check validate for form input
     *
     * @param name
     * @param phone
     * @param address
     * @param email
     * @param password
     * @return
     */
    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response
            ,String  name,  String  phone ,String  address , String email, String password) {
        String checkName= UserValidator.validateTenNguoiDung(name);
        String checkPhone = UserValidator.validateSDT(phone);
        String checkAddress = UserValidator.validateDiaChi(address);
        String checkEmail = UserValidator.validateEmail(email);
        String checkPassword = UserValidator.validateMatKhau(password);

        // count for validate
        int count = 0;
        if (!checkName.isEmpty()) {
            count++;
            request.setAttribute("error_name", checkName);
        } else {
            request.setAttribute("name_user", name);
        }
        if (!checkPhone.isEmpty()) {
            count++;
            request.setAttribute("error_phone", checkPhone);
        } else {
            request.setAttribute("phone_user", phone);
        }
        if (!checkAddress.isEmpty()) {
            count++;
            request.setAttribute("error_address", checkAddress);
        } else {
            request.setAttribute("address_user", address);
        }

        if (!checkEmail.isEmpty()) {
            count++;
            request.setAttribute("error_email", checkEmail);
        } else {
            request.setAttribute("email_user", email);
        }

        if (!checkPassword.isEmpty()) {
            count++;
            request.setAttribute("error_password", checkPassword);
        } else {
            request.setAttribute("pass_user", password);
        }

        if (count > 0) {
            return false;
        }
        return true;
    }
}