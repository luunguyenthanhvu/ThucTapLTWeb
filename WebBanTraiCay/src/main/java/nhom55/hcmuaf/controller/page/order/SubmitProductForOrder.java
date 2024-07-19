package nhom55.hcmuaf.controller.page.order;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.util.MyUtils;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(name = "SubmitProductForOrder", value = "/page/order/submit-selected-products")
public class SubmitProductForOrder extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Đọc danh sách ID sản phẩm từ form
    String selectedProductIdsJson = request.getParameter("selectedProductIds");
    if (selectedProductIdsJson != null) {
      List<String> selectedProductIds = Arrays.asList(
          new ObjectMapper().readValue(selectedProductIdsJson, String[].class));

      // Lưu danh sách ID sản phẩm vào session
      HttpSession session = request.getSession();
      session.setAttribute("selectedProductIds", selectedProductIds);

      // chuyển hướng sang trang check out để tiến hành thanh toán
      response.sendRedirect(request.getContextPath() + "/page/order/check-out");
    } else if (MyUtils.getLoginedUser(request.getSession()) == null) {
      // chuyển hướng sang trang check out để tiến hành thanh toán
      response.sendRedirect(request.getContextPath() + "/page/login");
    }
  }
}
