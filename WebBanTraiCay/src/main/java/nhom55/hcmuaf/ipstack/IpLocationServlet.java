package nhom55.hcmuaf.ipstack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@WebServlet(name = "IpLocationServlet", urlPatterns = {"/iplocation"})
public class IpLocationServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final String API_KEY = "";

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    // Lấy địa chỉ IP từ tham số trong URL
    String ipAddress = request.getHeader("X-Forwarded-For");
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
    }

    // Tạo URL để gửi yêu cầu đến ipstack
    String url =
        "http://api.ipstack.com/" + "4b58bbb4-8b60-4fce-8a8c-afdd50dc84a5.local" + "?access_key="
            + API_KEY;
    System.out.println(ipAddress);
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);

      try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
        String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(httpResponse.getEntity().getContent());
        JSONObject data = new JSONObject(jsonResponse);

        // Hiển thị thông tin vị trí trên trang web
        try (PrintWriter out = response.getWriter()) {
          out.println("<!DOCTYPE html>");
          out.println("<html>");
          out.println("<head>");
          out.println("<title>IP Location Information</title>");
          out.println("</head>");
          out.println("<body>");
          out.println("<h1>IP Location Information</h1>");
          out.println("<p>IP Address: " + data.getString("ip") + "</p>");
          out.println("<p>Country: " + data.getString("country_name") + "</p>");
          out.println("<p>Region: " + data.getString("region_name") + "</p>");
          out.println("<p>City: " + data.getString("city") + "</p>");
          out.println("<p>Latitude: " + data.getDouble("latitude") + "</p>");
          out.println("<p>Longitude: " + data.getDouble("longitude") + "</p>");
          out.println("</body>");
          out.println("</html>");
        }
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
//    RequestDispatcher dispatcher = this.getServletContext()
//        .getRequestDispatcher("/WEB-INF/test-getIP.jsp");
//    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }
}
