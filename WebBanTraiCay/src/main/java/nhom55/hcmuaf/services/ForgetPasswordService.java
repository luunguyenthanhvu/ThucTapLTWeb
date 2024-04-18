package nhom55.hcmuaf.services;

import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.util.MyUtils;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Properties;

public class ForgetPasswordService {
    private static ForgetPasswordService instance;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*";
    private UsersDao usersDao = new UsersDaoImpl ();
    public ForgetPasswordService() {
        super();
    }
    public static ForgetPasswordService getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordService ();
        }
        return instance;
    }

    public String changePass(String email) {
        String result;
        // generate
        String newPass = generateNewPass ();
        String endCodePass = MyUtils.encodePass (newPass);
        result = usersDao.updateNewPassWord (email, endCodePass);
        sendNewPasswordToEmail (email, newPass);
        return result;
    }

    /**
     * send new email to
     * @param email
     * @param password
     */
    public static void sendNewPasswordToEmail(String email, String password) {
        Properties properties = MailProperties.getSMTPPro();
        Session session = Session.getInstance (properties,new javax.mail.Authenticator () {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication (MailProperties.getEmail (), MailProperties.getPassword ());
            }
        });
        try {
            Message message = new MimeMessage (session);
            message.addHeader("Content-type", "text/HTML; charset= UTF-8");
            message.setFrom (new InternetAddress (MailProperties.getEmail ()));
            message.addRecipient (Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject ("Đổi mật khẩu tài khoản");
            message.setText ("Mật khẩu mới của " + email + ": " + password ) ;
            Transport.send (message);
        } catch (Exception e) {
            System.out.println ("SendEmail File Error " + e);
        }
    }

    /**
     * generate newRandomPassWord
     * @return
     */
    public static String generateNewPass() {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}
