package nhom55.hcmuaf.controller.page.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import nhom55.hcmuaf.beans.cart.Cart;

@WebServlet(name = "getCartAmount", value = "/page/cart/get-cart-amount")
public class GetCartAmount extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    int cartCount = (cart != null) ? cart.getTotal() : 0;
    response.getWriter().write(String.valueOf(cartCount));
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}