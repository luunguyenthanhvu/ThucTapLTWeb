package nhom55.hcmuaf.services;

import nhom55.hcmuaf.beans.RegisterBean;
import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.dao.UsersDaoImpl;
import nhom55.hcmuaf.sendmail.MailProperties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class RegisterService {

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
  public String RegisterUser(RegisterBean register) {
    String result = "";
    String username = register.getUsername();
    String phoneNumber = register.getPhoneNumber();
    String address = register.getAddress();
    String email = register.getEmail();
    String password = register.getPassword();
    String hash = register.getHash();

    result = usersDao.addNewUser(username, password, hash, email, phoneNumber, address);

    if (result.equals("SUCCESS")) {
      verifyAccount(email, hash);
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
