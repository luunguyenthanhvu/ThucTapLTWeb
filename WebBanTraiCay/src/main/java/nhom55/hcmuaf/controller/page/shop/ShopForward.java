package nhom55.hcmuaf.controller.page.shop;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.response.ListProductShopResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;

@WebServlet(name = "ShopForward", value = "/page/shop/shop-forward/*")
public class ShopForward extends HttpServlet {

  private ProductService productService;
  private final Integer MAX_RECORD_PAGING_PRODUCT = 20;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String context = request.getPathInfo();
    if (context == null) {
      context = "";
    }

    // Get pageId from request, default to 1
    int pageId = 1;
    String pageIdStr = request.getParameter("pageId");
    if (pageIdStr != null) {
      try {
        pageId = Integer.parseInt(pageIdStr);
      } catch (NumberFormatException e) {
        // Handle invalid pageId parameter
      }
    }

    // Get category and productName from request parameters
    String category = request.getParameter("category");
    String productName = request.getParameter("productName");
    if (category == null) {
      category = "";
    }
    if (productName == null) {
      productName = "";
    }
    // Calculate startIndex for database query
    int startIndex = (pageId - 1) * MAX_RECORD_PAGING_PRODUCT;

    try {
      productService = new ProductService();
      productService.begin();

      List<ListProductShopResponseDTO> productList = productService.findAllBy(startIndex,
          MAX_RECORD_PAGING_PRODUCT, productName, category);
      int totalRecords = productService.countFilteredRecords(productName, category);

      // Calculate total pages
      int totalPages = (int) Math.ceil((double) totalRecords / MAX_RECORD_PAGING_PRODUCT);

      // Set attributes in request to pass to JSP
      request.setAttribute("productList", productList);
      request.setAttribute("pageId", pageId);
      request.setAttribute("totalPages", totalPages);
      request.setAttribute("category", category);
      request.setAttribute("productName", productName);

      productService.save();
    } catch (Exception e) {
      e.printStackTrace();
      productService.rollback();
      throw new MyHandleException("Server error", 500);
    } finally {
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shop.jsp");
      requestDispatcher.forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
