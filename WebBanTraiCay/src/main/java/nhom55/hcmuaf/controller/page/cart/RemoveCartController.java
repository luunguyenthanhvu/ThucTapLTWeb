package nhom55.hcmuaf.controller.page.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "RemoveCartController", value = "/page/cart/remove-product-cart")
public class RemoveCartController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();

    int id = Integer.parseInt(request.getParameter("productId"));

    Cart cart = (Cart) request.getSession().getAttribute("cart");
    cart.deleteProduct(id);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter()
        .write("{ \"status\": \"success\", \"message\": \"Removed from cart\" }");

    // update cart
//    MyUtils.storeCart(session, cart);
  }
}
