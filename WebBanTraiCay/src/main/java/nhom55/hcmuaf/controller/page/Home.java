package nhom55.hcmuaf.controller.page;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.websocket.entities.CartsEntityWebSocket;

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
    CartsEntityWebSocket cart = new CartsEntityWebSocket();
    Users user = MyUtils.getLoginedUser(session);
    if (MyUtils.getLoginedUser(session) != null) {
      System.out.println("lay cart o home");
      System.out.println(cart);
    }
    MyUtils.storeCart(session, cart);

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

}
