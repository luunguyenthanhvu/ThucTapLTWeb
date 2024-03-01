package nhom55.hcmuaf.controller.page;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.beans.cart.UserCart;
import nhom55.hcmuaf.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(urlPatterns = {"/page/home"})
public class Home extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Home() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // Dẫn đến đường link trang chủ hiển thị ra 8 sản phẩm
    List<Products> products = ProductService.getInstance().getProduct();
    request.setAttribute("listProducts", products);

    HttpSession session = request.getSession();
    //create cart if not exist
    Cart cart;
    Users user = MyUtils.getLoginedUser(session);
    if (MyUtils.getLoginedUser(session) != null && UserCart.getUserCart(user.getId()) != null) {
      cart = UserCart.getUserCart(user.getId());
      MyUtils.storeCart(session, cart);
    } else if (MyUtils.getCart(session) == null) {
      cart = new Cart();
      MyUtils.storeCart(session, cart);
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

  }

}