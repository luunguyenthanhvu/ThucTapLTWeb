package nhom55.hcmuaf.websocket.endpoint;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebSocketAPI_v1", value = "/end-point/web-socket")
public class WebSocketAPI extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String websocketUrl = "ws://" + request.getServerName() + ":" + request.getServerPort()
        + request.getContextPath() + "/websocket/cart-socket";
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.println(websocketUrl);
    out.flush();
  }
}
