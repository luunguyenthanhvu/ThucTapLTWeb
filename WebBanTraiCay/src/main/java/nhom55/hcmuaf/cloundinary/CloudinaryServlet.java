package nhom55.hcmuaf.cloundinary;

import java.sql.Timestamp;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.JsonObject;

@WebServlet(name = "CloudinaryServlet", value = "/cloudinary/*")
public class CloudinaryServlet extends HttpServlet {
  private Properties prop = CloudProperties.setSMTPPro();
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "" : {
        response.sendRedirect(request.getServletPath() + "/error");
        return;
      }

      case "/get-signature" : {
        response.setContentType("application/json");
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", prop.getProperty("cloud_name"),
            "api_key", prop.getProperty("api_key"),
            "api_secret", prop.getProperty("api_secret"),
            "secure", true
        ));

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        long timestamp = ts.getTime() / 1000;
        String signature = cloudinary.apiSignRequest(ObjectUtils.asMap(
            "timestamp", timestamp
        ), prop.getProperty("api_secret"));

        JsonObject json = new JsonObject();
        json.addProperty("signature", signature);
        json.addProperty("timestamp", timestamp);
        response.getWriter().write(json.toString());
        response.getWriter().flush();
      }
      break;

      default:
        throw new IllegalStateException("Unexpected value: " + action);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
