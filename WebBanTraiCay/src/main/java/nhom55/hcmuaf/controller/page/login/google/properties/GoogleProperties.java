package nhom55.hcmuaf.controller.page.login.google.properties;

import java.io.IOException;
import java.util.Properties;
import nhom55.hcmuaf.sendmail.MailProperties;

public class GoogleProperties {
  private static final Properties prop = new Properties ();

  static {
    try {
      prop.load (GoogleProperties.class.getClassLoader ().getResourceAsStream ("google.properties"));
    } catch (IOException ioException) {
      ioException.printStackTrace ();
    }
  }
  public static Properties getPropGoogle() {
    Properties properties = new Properties();
    properties.put("GOOGLE_CLIENT_ID", getClientId());
    properties.put("GOOGLE_CLIENT_SECRET", getCilentSecret());
    properties.put("GOOGLE_REDIRECT_URI", getRedirectURI());
    properties.put("GOOGLE_LINK_GET_TOKEN", getLinkGetToken());
    properties.put("GOOGLE_LINK_GET_USER_INFO", getLinkGetUserInfo());
    properties.put("GOOGLE_GRANT_TYPE", getGrantType());
    return properties;
  }

  private static String getGrantType() {
    return prop.get("GOOGLE_GRANT_TYPE").toString();
  }

  private static String getLinkGetUserInfo() {
    return prop.get("GOOGLE_LINK_GET_USER_INFO").toString();
  }

  private static String getLinkGetToken() {
    return prop.get("GOOGLE_LINK_GET_TOKEN").toString();
  }

  private static String getRedirectURI() {
    return prop.get("GOOGLE_REDIRECT_URI").toString();
  }

  private static String getCilentSecret() {
    return prop.get("GOOGLE_CLIENT_SECRET").toString();
  }

  private static String getClientId() {
    return prop.get("GOOGLE_CLIENT_ID").toString();
  }
}
