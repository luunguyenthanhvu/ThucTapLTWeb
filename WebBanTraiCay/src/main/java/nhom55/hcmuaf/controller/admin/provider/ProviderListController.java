package nhom55.hcmuaf.controller.admin.provider;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.services.ShopService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProviderListController", value = "/admin/provider/provider-list-controller")
public class ProviderListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String txtSearch = request.getParameter("txtSearch");
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);

        int quantity = ProviderService.getInstance().countResultSearchingProviders(txtSearch);
//        số lượng mặc định 1 trang
        int defaultQuantityProvidersOnAPage = 20;
//        index user bấm vào phân trang
        String indexPage = request.getParameter("index");

        int indexInitial = Integer.parseInt(indexPage);

        int indexEnd = quantity / defaultQuantityProvidersOnAPage;

        if (quantity % defaultQuantityProvidersOnAPage != 0) {
            indexEnd++;
        }
        List<Providers> listSearch = null;

        listSearch = ProviderService.getInstance().search(txtSearch, indexInitial,
                defaultQuantityProvidersOnAPage);
        request.setAttribute("pageId",indexInitial);
        request.setAttribute("listSearch", listSearch);
        request.setAttribute("indexEnd", indexEnd);
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/WEB-INF/admin/search-of-provider-list.jsp").forward(request, response);

    }
}
