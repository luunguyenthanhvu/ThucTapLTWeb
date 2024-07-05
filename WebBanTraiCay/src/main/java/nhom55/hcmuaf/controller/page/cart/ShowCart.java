package nhom55.hcmuaf.controller.page.cart;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.beans.cart.CartProduct;

@WebServlet(name = "cart", value = "/page/cart")
public class ShowCart extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cart.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Cart cart = (Cart) session.getAttribute("cart");
    Collection<CartProduct> cartProducts =  cart.getCartProduct();

    String updateCartProduct = generateUpdateCartProductHTML(cartProducts);
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(updateCartProduct);
  }

  private static String generateUpdateCartProductHTML(Collection<CartProduct> cartProducts) {
    String result = "";
    for(CartProduct c : cartProducts) {
      int productId = c.getProducts().getId();
      NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
      result +=
          "<tr class=\"text-center\">\n"
              + "                                    <td class=\"product-remove\"><a\n"
              + "                                            href=\"javascript:void(0);\"\n"
              + "                                            onclick=" + "deleteProduct(" + productId +")" + "><span\n"
              + "                                            class=\"ion-ios-close\"></span></a></td>\n"
              + "                                    <td><input name=\"selectedProducts\"\n"
              + "                                               value=" + productId + "\n"
              + "                                               style=\"cursor: pointer; margin-top:  10px; width: 25px; height: 25px\"\n"
              + "                                               type=\"checkbox\"></td>\n"
              + "                                    <td class=\"image-prod\">\n"
              + "                                        <div class=\"img\">\n"
              + "                                            <img\n"
              + "                                                    style=\"width: 100px; height: 100px; object-fit: cover\"\n"
              + "                                                    class=\"img-fluid\"\n"
              + "                                                    src="  + "\n"
              + "                                                    alt=\"Colorlib Template\">\n"
              + "                                        </div>\n"
              + "                                    </td>\n"
              + "\n"
              + "                                    <td class=\"product-name\">\n"
              + "                                        <h3>" + c.getProducts().getNameOfProduct() + "</h3>\n"
              + "                                        <p>" + c.getProducts().getDescription() + "</p>\n"
              + "                                    </td>\n"
              + "\n"
              + "                                    <td class=\"price\">\n"
              + "                                        "+ currencyFormatter.format(c.getProducts().getPrice())+"\n"
              + "                                    </td>\n"
              + "\n"
              + "                                    <td class=\"quantity\">\n"
              + "                                        <div class=\"input-group mb-3\">\n"
              + "                                <span class=\"input-group-btn mr-2\">\n"
              + "                                    <a href=\"javascript:void(0);\"\n"
              + "                                       onclick=" + "decProduct(" + productId + ")" +"\n"
              + "                                       class=\"btn-plus-indre\"\n"
              + "                                       data-type=\"minus\" data-field=\"\">\n"
              + "                                        <i class=\"ion-ios-remove\"></i>\n"
              + "                                    </a>\n"
              + "                                </span>\n"
              + "                                            <input type=\"text\" name=\"quantity\"\n"
              + "                                                   class=\"form-control input-number\"\n"
              + "                                                   value="+ c.getQuantity() +">\n"
              + "                                            <span class=\"input-group-btn ml-2\">\n"
              + "                                    <a href=\"javascript:void(0);\"\n"
              + "                                       onclick=" + "incProduct(" + productId + ")" + "\n"
              + "                                       class=\"btn-plus-indre\"\n"
              + "                                       data-type=\"plus\" data-field=\"\">\n"
              + "                                        <i class=\"ion-ios-add\"></i>\n"
              + "                                    </a>\n"
              + "                                </span>\n"
              + "                                        </div>\n"
              + "\n"
              + "                                    </td>\n"
              + "                                    <td class=\"total\">\n"
              + "                                        " + currencyFormatter.format(c.getPrice()) + "\n"
              + "                                    </td>\n"
              + "                                </tr>";
    }
    return result;
  }
}
