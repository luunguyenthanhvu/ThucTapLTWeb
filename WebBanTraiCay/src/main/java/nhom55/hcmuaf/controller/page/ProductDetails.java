package nhom55.hcmuaf.controller.page;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.services.ProductService;

@WebServlet(name = "productDetails", value = "/page/product/product-detail")
public class ProductDetails extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String parameterValue = request.getParameter("id"); // Lấy giá trị từ tham số truy vấn

    if (parameterValue != null && !parameterValue.isEmpty()) { // Kiểm tra xem giá trị có null không
      int productId = Integer.parseInt(request.getParameter("id"));

      Products product = ProductService.getInstance()
          .showProductDetails(productId);  // Gọi phương thức để lấy chi tiết sản phẩm

      if (product != null) {
        request.setAttribute("showProduct", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product-single.jsp");
        dispatcher.forward(request, response);
      } else {
        // Trường hợp không tìm thấy sản phẩm có ID đã cho
        response.sendRedirect("/page/home");
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
