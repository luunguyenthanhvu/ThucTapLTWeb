package nhom55.hcmuaf.controller.page.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "QuantityIncDec", value = "/page/cart/quantity-inc-dec")
public class QuantityIncDec extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("actionType");
    int id = Integer.parseInt(request.getParameter("productId"));

    HttpSession session = request.getSession();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    Cart cart = (Cart) session.getAttribute("cart");

    if (action != null && id >= 1) {
      if (action.equals("inc")) {
        String result = cart.add(id);
        if (result.equals("Success")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Success\" }");
        } else if (result.equals("Out of quantity")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Out of quantity\" }");
        } else if (result.equals("Product does not exist")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Product does not exist\" }");
        }
      } else if (action.equals("dec")) {
        String result = cart.remove(id);
        if (result.equals("Success")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Success\" }");
        } else if (result.equals("Removed from cart")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Removed from cart\" }");
        } else if (result.equals("Product does not exist")) {
          response.getWriter()
              .write("{ \"status\": \"success\", \"message\": \"Product does not exist\" }");
        }
      }
      // update cart
//      MyUtils.storeCart(session, cart);
    }
  }
}
