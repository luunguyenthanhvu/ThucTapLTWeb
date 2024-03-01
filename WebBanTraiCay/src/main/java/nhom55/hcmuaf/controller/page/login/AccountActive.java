package nhom55.hcmuaf.controller.page.login;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.services.AdminService;
import nhom55.hcmuaf.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountActive", value = "/page/login/account-active")
public class AccountActive extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");
        Users user = UserService.getInstance().getUserByEmail(email);
        if (user != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime timestamp = user.getCreationTime(); // trả về thời điểm tạo mã.
            // Kiểm tra xem khoảng thời gian có nằm trong 30 phút không
            Duration duration = Duration.between(timestamp, currentTime);
            long validityDurationInMinutes = 30;
            if (duration.toMinutes() <= validityDurationInMinutes) {
                if (AdminService.getInstance().updateUserStatus(email, hash) != null) {
                    request.setAttribute("result", "Xác thực tài khoản thành công!");
                }
            } else {
                request.setAttribute("result", "Đã quá thời gian xác thực.\n Chúng tôi đã gửi cho bạn 1 email xác thực khác!");
                verifyAccount(user.getEmail(), user.getHash());
                UserService.getInstance().updateTimeStampUser(user.getEmail());
            }
        }
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    public static void verifyAccount(String email, String hash) {
        Properties properties = MailProperties.getSMTPPro();
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailProperties.getEmail(), MailProperties.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset= UTF-8");
            message.setFrom(new InternetAddress(MailProperties.getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Xac thuc tai khoan. Thoi han 30 phut.");
            message.setText("Chon vao day : " + "http://localhost:8080//page/login/account-active?key1="
                    + email + "&key2=" + hash);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("SendEmail File Error " + e);
        }
    }
}