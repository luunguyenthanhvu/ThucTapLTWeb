package nhom55.hcmuaf.sendmail;

import nhom55.hcmuaf.database.DBProperties;

import java.io.IOException;
import java.util.Properties;

public class MailProperties {
    private static final Properties prop = new Properties ();

    static {
        try {
            prop.load (MailProperties.class.getClassLoader ().getResourceAsStream ("mail.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace ();
        }
    }
    public static Properties getSMTPPro() {
        Properties properties = new Properties ();
        properties.put ("mail.smtp.host", getMailHost ());
        properties.put ("mail.smtp.port", getMailPort ());
        properties.put ("mail.smtp.auth", getMailAuth ());
        properties.put ("mail.smtp.starttls.enable", getMailStarttls ());
        return properties;
    }
    public static String getMailHost() {
        return prop.get ("mail.smtp.host").toString ();
    }
    public static String getMailPort() {
        return prop.get ("mail.smtp.port").toString ();
    }
    public static String getMailAuth() {
        return prop.get ("mail.smtp.auth").toString ();
    }
    public static String getMailStarttls() {
        return prop.get ("mail.smtp.starttls.enable").toString ();
    }
    public static String getEmail() {
        return prop.get ("mail.username").toString ();
    }
    public static String getPassword() {
        return prop.get ("mail.password").toString ();
    }

}
