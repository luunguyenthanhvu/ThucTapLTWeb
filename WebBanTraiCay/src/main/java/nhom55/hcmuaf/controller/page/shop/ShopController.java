package nhom55.hcmuaf.controller.page.shop;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.services.ShopService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ShopController", value = "/page/shop/shop-controller")
public class ShopController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String txtSearch = request.getParameter("txtSearch");


    int quantity = ShopService.getInstance().countResultSearchingProduct(txtSearch);
//        số lượng mặc định 1 trang
    int defaultQuantityProductOnAPage = 20;
//        index user bấm vào phân trang
    String indexPage = request.getParameter("index");

    int indexInitial = Integer.parseInt(indexPage);

    int indexEnd = quantity / defaultQuantityProductOnAPage;

    if (quantity % defaultQuantityProductOnAPage != 0) {
      indexEnd++;
    }
    List<Products> listSearch = null;

       listSearch = ShopService.getInstance().search(txtSearch, indexInitial,
              defaultQuantityProductOnAPage);
       request.setAttribute("pageId",indexInitial);
      request.setAttribute("listSearch", listSearch);
      request.setAttribute("indexEnd", indexEnd);
      request.setAttribute("txtSearch", txtSearch);
      request.getRequestDispatcher("/WEB-INF/searchProductResult.jsp").forward(request, response);


  }
}