package nhom55.hcmuaf.controller.admin.product.expired;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ShopService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageExpiredProductController", value = "/admin/product/expired-manage-controller")
public class ManageExpiredProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String txtSearch = request.getParameter("txtSearch");
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        int quantity = ShopService.getInstance().countResultSearchingProductForExpiredProduct(txtSearch);
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

        listSearch = ShopService.getInstance().searchExpiredProduct(txtSearch, indexInitial,
                defaultQuantityProductOnAPage);
        request.setAttribute("pageId",indexInitial);
        request.setAttribute("listSearch", listSearch);
        request.setAttribute("indexEnd", indexEnd);
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/WEB-INF/admin/search-of-time-expired-product.jsp").forward(request, response);
    }
}
