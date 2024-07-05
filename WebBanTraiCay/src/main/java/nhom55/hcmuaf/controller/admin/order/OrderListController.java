package nhom55.hcmuaf.controller.admin.order;

import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListController", value = "/admin/order/order-list-controller")
public class OrderListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        String txtSearch = request.getParameter("txtSearch");
        BillDao orderDao = new BillDaoImpl();

        int quantity = orderDao.countResultSearchingBill(txtSearch);
        //        số lượng mặc định 1 trang
        int defaultQuantityProductOnAPage = 10;
//        index user bấm vào phân trang
        String indexPage = request.getParameter("index");

        int indexInitial = Integer.parseInt(indexPage);

        int indexEnd = quantity / defaultQuantityProductOnAPage;

        if (quantity % defaultQuantityProductOnAPage != 0) {
            indexEnd++;
        }
        List<Bills> listSearch = null;

        listSearch = orderDao.search(txtSearch, indexInitial,
                defaultQuantityProductOnAPage);
        request.setAttribute("admin", admin);
        request.setAttribute("pageId",indexInitial);
        request.setAttribute("listSearch", listSearch);
        request.setAttribute("indexEnd", indexEnd);
        request.setAttribute("txtSearch", txtSearch);
        request.getRequestDispatcher("/WEB-INF/admin/search-of-order-list.jsp").forward(request, response);
    }

}
