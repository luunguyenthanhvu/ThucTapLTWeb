package nhom55.hcmuaf.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import nhom55.hcmuaf.beans.RegisterBean;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.util.MyUtils;

public class RegisterService extends AbsDAO {

  private static RegisterService instance;
  private UsersDao usersDao;

  public RegisterService() {
    usersDao = new UsersDaoImpl();
  }

  public static RegisterService getInstance() {
    if (instance == null) {
      instance = new RegisterService();
    }
    return instance;
  }

  /**
   * generate new user if user not exits
   *
   * @param register
   * @return String result
   */
  public String RegisterUser(RegisterBean register, HttpServletRequest request) {
    String result = "";
    String email = register.getEmail();
    String username = register.getUsername();
    String phoneNumber = register.getPhoneNumber();
    String address = register.getAddress();
    String password = register.getPassword();
    String hash = register.getHash();
    Users user = usersDao.getUserByEmail(email);
    if (user == null) {
      result = usersDao.addNewUser(username, password, hash, email, phoneNumber, address);

      if (result.equals("SUCCESS")) {
        verifyAccount(email, hash);

        // log into server
//        Users newUser = usersDao.getUserByEmail(email);
//        Log<Users> log = newUser;
//        System.out.println(log);
//        log.setNote("New User Register");
//        log.setLevel(LogLevels.INFO);
//        log.setPreValue("");
//        try {
//          log.setCurValue(MyUtils.convertToJson(newUser));
//        } catch (IOException ioException) {
//          ioException.printStackTrace();
//          System.out.println("can't convert");
//        }
//        log.setCreateAt(LocalDateTime.now());
//        log.setRequestInfo(new RequestInfo(request.getRemoteAddr(), "HCM", "VietNam"));
//        super.insert(log);
      }

    } else {
      return "FAIL";
    }
    return result;
  }

  /**
   * if user click in this link in mail, sever will set status = 1 (ACTIVE)
   *
   * @param email
   * @param hash
   */
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
