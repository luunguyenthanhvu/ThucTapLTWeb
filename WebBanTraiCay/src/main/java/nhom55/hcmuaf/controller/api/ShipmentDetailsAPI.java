package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ShipmentDetailsAPIServlet", value = "/api/shipments-api/**")
public class ShipmentDetailsAPI extends HttpServlet {

  private final String REQUEST_BODY = "request-body";
  private ProductService productService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    try {
      String context = request.getPathInfo();
      String requestDTO = (String) request.getAttribute(REQUEST_BODY);
      switch (context) {
        case "/add-new-shipments":
          List<Integer> selectedProducts = MyUtils.convertJsonToObject(requestDTO, List.class);

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new MyHandleException("Loi server", 500);
    }
  }
}
