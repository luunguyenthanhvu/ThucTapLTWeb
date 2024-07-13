package nhom55.hcmuaf.controller.admin.product;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ProductList", value = "/admin/product/product-list")
public class ProductList extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Users admin = MyUtils.getLoginedUser(session);
//        String pageStr = request.getParameter("pageId");
//        String order = request.getParameter("order");
//        String whereClause = request.getParameter("whereClause");
//        if((order==null ||  "".equals(order)) &&  (whereClause == null || "".equals(whereClause))) {
//            int pageNumber = 0;
//            if(pageStr == null) {
//                pageNumber =1;
//            } else {
//                pageNumber = Integer.valueOf(pageStr);
//            }
//
//            int quantityDefault =20;
//            int totalRow = ShopService.getInstance().countTotalRowProductInDatabase();
//            int haveMaxPage = (totalRow/quantityDefault) +1;
//            List<Products> listProduct = ShopService.getInstance().get20ProductsForEachPage(pageNumber,quantityDefault);
//            RequestDispatcher dispatcher = this.getServletContext()
//                    .getRequestDispatcher("/WEB-INF/admin/product-list.jsp");
//            request.setAttribute("admin", admin);
//            request.setAttribute("listProduct",listProduct);
//            request.setAttribute("haveMaxPage",haveMaxPage);
//            request.setAttribute("pageId",pageNumber);
//            dispatcher.forward(request, response);
//        } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/filter-product");
//            dispatcher.forward(request,response);
//        }
    HttpSession session = request.getSession();
    Users admin = MyUtils.getLoginedUser(session);
    request.setAttribute("admin", admin);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/shipment/product-list.jsp");
    dispatcher.forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
