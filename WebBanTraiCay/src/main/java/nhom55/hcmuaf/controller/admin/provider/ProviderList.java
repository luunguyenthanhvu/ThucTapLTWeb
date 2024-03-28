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
import java.security.Provider;
import java.util.List;

@WebServlet(name = "ProviderList", value = "/admin/provider/provider-list")
public class ProviderList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Điều kiện if đầu tiên kiểm tra biến notifyError có rỗng hay không được forward sang từ Servlet DeleteProviderController
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
       if(request.getAttribute("notifyError") == null) {
           String pageStr = request.getParameter("pageId");
           int pageNumber = 0;
           if(pageStr == null) {
               pageNumber =1;
           } else {
               pageNumber = Integer.valueOf(pageStr);
           }

           int quantityDefault =20;
           int totalRow = ProviderService.getInstance().countTotalRowProvidersInDatabase();
           int haveMaxPage = (totalRow/quantityDefault) +1;
           List<Providers> listProvider = ProviderService.getInstance().get20ProvidersForEachPage(pageNumber,quantityDefault);
           request.setAttribute("listProvider",listProvider);
           request.setAttribute("haveMaxPage",haveMaxPage);
           request.setAttribute("pageId",pageNumber);
           request.setAttribute("admin", admin);
           RequestDispatcher dispatcher = this.getServletContext()
                   .getRequestDispatcher("/WEB-INF/admin/provider-list.jsp");
           dispatcher.forward(request, response);
       } else {
           request.setAttribute("notifyError","lỗi");
           String pageStr = request.getParameter("pageId");
           int pageNumber = 0;
           if(pageStr == null) {
               pageNumber =1;
           } else {
               pageNumber = Integer.valueOf(pageStr);
           }

           int quantityDefault =20;
           int totalRow = ProviderService.getInstance().countTotalRowProvidersInDatabase();
           int haveMaxPage = (totalRow/quantityDefault) +1;
           List<Providers> listProvider = ProviderService.getInstance().get20ProvidersForEachPage(pageNumber,quantityDefault);
           request.setAttribute("listProvider",listProvider);
           request.setAttribute("haveMaxPage",haveMaxPage);
           request.setAttribute("pageId",pageNumber);
           request.setAttribute("admin", admin);
           RequestDispatcher dispatcher = this.getServletContext()
                   .getRequestDispatcher("/WEB-INF/admin/provider-list.jsp");
           dispatcher.forward(request, response);
       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
