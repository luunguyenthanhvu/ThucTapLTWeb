package nhom55.hcmuaf.controller.admin.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "AddMoreWeightProduct", value = "/admin/product/add-more-weight")
public class AddMoreWeightProduct extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Users users = MyUtils.getLoginedUser(session);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("productId"));
    double weight = Double.valueOf(request.getParameter("weight"));
    boolean result = ProductService.getInstance().addMoreWeight(id, weight);
    Products products = ProductService.getInstance().getById(id);
    if(result) {
      response.getWriter()
          .write("{ \"status\": \"success\", \"message\": \"Success\" , \"updatedWeight\" }");
    } else {
      response.getWriter()
          .write("{ \"status\": \"error\", \"message\": \"Product does not exist\" }");
    }
  }
}
