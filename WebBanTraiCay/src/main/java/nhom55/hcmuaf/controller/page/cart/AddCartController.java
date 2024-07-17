package nhom55.hcmuaf.controller.page.cart;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "add", value = "/page/cart/add-cart")
public class AddCartController extends HttpServlet {

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
        int quantity = Integer.parseInt(request.getParameter("quantity"));

//        Products product = ProductService.getInstance().getById(id);
//        Cart cart = (Cart) request.getSession().getAttribute("cart");
//        String result = cart.add(id, quantity);
//
//        // update cart
//        MyUtils.storeCart(session, cart);

//        if (result.equals("Success")) {
//            response.getWriter()
//                    .write("{ \"status\": \"success\", \"message\": \"Success\" }");
//        } else if (result.equals("Out of quantity")) {
//            response.getWriter()
//                    .write("{ \"status\": \"success\", \"message\": \"Out of quantity\" }");
//        } else if (result.equals("Product does not exist")) {
//            response.getWriter()
//                    .write("{ \"status\": \"success\", \"message\": \"Product does not exist\" }");
//        }

    }
}
