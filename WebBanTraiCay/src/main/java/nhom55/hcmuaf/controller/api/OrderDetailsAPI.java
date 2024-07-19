package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "OrderDetailsAPI", value = "/api/order-details/*")
public class OrderDetailsAPI extends HttpServlet {

  private ProductService productService;

  @Override
  public void init() throws ServletException {
    super.init();
    // Initialize the ProductService here
    this.productService = new ProductService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String id = request.getPathInfo();
    if (id == null || id.length() <= 1) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID");
      return;
    }

    id = id.substring(1); // Remove leading '/'

    try (PrintWriter out = response.getWriter()) {
      productService.begin();
      out.println(MyUtils.convertToJson(productService.getShipmentDetails(Integer.valueOf(id))));
      productService.save();
    } catch (NumberFormatException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
    } catch (Exception e) {
      productService.rollback();
      e.printStackTrace(); // Log to file instead of printing stack trace
      throw new MyHandleException("Server error", 500);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Implement POST method if needed
  }
}
