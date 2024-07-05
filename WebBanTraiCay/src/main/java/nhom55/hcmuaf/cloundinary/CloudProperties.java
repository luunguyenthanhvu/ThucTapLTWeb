package nhom55.hcmuaf.cloundinary;

import java.io.IOException;
import java.util.Properties;

public class   CloudProperties {
  private static final Properties prop = new Properties();

  static {
    try {
      prop.load (CloudProperties.class.getClassLoader ().getResourceAsStream ("cloudinary.properties"));
    } catch (IOException ioException) {
      ioException.printStackTrace ();
    }
  }

  public static Properties setSMTPPro() {
    Properties properties = new Properties();
    properties.put("cloud_name", getCloudName());
    properties.put("api_key", getApiKey());
    properties.put("api_secret", getApiSecret());
    return properties;
  }

  private static String getApiSecret() {
    return prop.get("api_secret").toString();
  }

  private static String getApiKey() {
    return prop.get("api_key").toString();
  }

  private static String getCloudName() {
    return prop.get("cloud_name").toString();
  }

}
