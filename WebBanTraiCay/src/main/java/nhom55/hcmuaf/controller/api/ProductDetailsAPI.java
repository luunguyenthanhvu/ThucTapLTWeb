package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.request.ProductDetailsRequestDTO;
import nhom55.hcmuaf.dto.response.MessageResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ProductDetailsAPIServlet", value = "/api/product-details/*")
public class ProductDetailsAPI extends HttpServlet {

  private final String REQUEST_BODY = "request-body";
  private ProductService productService;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();

      String context = request.getPathInfo();
      switch (context) {
        case "/update-status":
          System.out.println("vô ược r n");
          var dto = MyUtils.convertJsonToObject(
              request.getAttribute(REQUEST_BODY).toString(), ProductDetailsRequestDTO.class);
          productService = new ProductService();
          System.out.println(dto);
          // begin transaction
          productService.begin();
          var data = productService.findAllById(dto.getId())
              .orElseThrow(() -> new MyHandleException("Product not found", 500));

          data.setStatus(data.getStatus() == 1 ? 0 : 1);
          productService.updateProductStatus(data.getStatus(), data.getId());

          productService.save();
          out.println(MyUtils.convertToJson(
              MessageResponseDTO.builder().message("Cật nhật trạng thái thành công!")));
          out.flush();
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
    }
  }
}
