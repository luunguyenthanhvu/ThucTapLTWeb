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
      case "": {
        response.sendRedirect(request.getServletPath() + "/error");
        return;
      }

      case "/get-signature": {
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

      case "/remove-image":
        System.out.println("Đang test delete");
        String public_id = request.getParameter("id");
        System.out.println(public_id);
        if (public_id == null || public_id.isEmpty()) {
          // Nếu public_id không được truyền vào, trả về lỗi
          JsonObject errorJson = new JsonObject();
          errorJson.addProperty("error", "public_id is missing");
          response.setContentType("application/json");
          response.getWriter().write(errorJson.toString());
          return;
        }

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", prop.getProperty("cloud_name"),
            "api_key", prop.getProperty("api_key"),
            "api_secret", prop.getProperty("api_secret"),
            "secure", true
        ));

        // Thực hiện xóa tệp từ Cloudinary
        try {
          cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());

          // Phản hồi thành công về client
          JsonObject successJson = new JsonObject();
          successJson.addProperty("status", "success");
          response.setContentType("application/json");
          response.getWriter().write(successJson.toString());
        } catch (Exception e) {
          // Xảy ra lỗi khi xóa tệp từ Cloudinary
          JsonObject errorJson = new JsonObject();
          errorJson.addProperty("error", "Error deleting file from Cloudinary: " + e.getMessage());
          response.setContentType("application/json");
          response.getWriter().write(errorJson.toString());
        }
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + action);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getPathInfo();

    switch (action) {
      case "":
        response.sendRedirect(request.getServletPath() + "/error");
        return;
      case "/revert":
        String public_id = request.getParameter("id");
        System.out.println(public_id);
        if (public_id == null || public_id.isEmpty()) {
          // Nếu public_id không được truyền vào, trả về lỗi
          JsonObject errorJson = new JsonObject();
          errorJson.addProperty("error", "public_id is missing");
          response.setContentType("application/json");
          response.getWriter().write(errorJson.toString());
          return;
        }

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", prop.getProperty("cloud_name"),
            "api_key", prop.getProperty("api_key"),
            "api_secret", prop.getProperty("api_secret"),
            "secure", true
        ));

        // Thực hiện xóa tệp từ Cloudinary
        try {
          cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());

          // Phản hồi thành công về client
          JsonObject successJson = new JsonObject();
          successJson.addProperty("status", "success");
          response.setContentType("application/json");
          response.getWriter().write(successJson.toString());
        } catch (Exception e) {
          // Xảy ra lỗi khi xóa tệp từ Cloudinary
          JsonObject errorJson = new JsonObject();
          errorJson.addProperty("error", "Error deleting file from Cloudinary: " + e.getMessage());
          response.setContentType("application/json");
          response.getWriter().write(errorJson.toString());
        }
        break;
      default:
        // Hành động không hợp lệ
        JsonObject errorJson = new JsonObject();
        errorJson.addProperty("error", "Invalid action");
        response.setContentType("application/json");
        response.getWriter().write(errorJson.toString());
        break;
    }
  }

  public static void main(String[] args) throws IOException {
    String public_id  = "dmheek8p4iivc9bfmhrq";

    Properties prop = CloudProperties.setSMTPPro();
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", prop.getProperty("cloud_name"),
        "api_key", prop.getProperty("api_key"),
        "api_secret", prop.getProperty("api_secret"),
        "secure", true
    ));
    cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());
  }
}
